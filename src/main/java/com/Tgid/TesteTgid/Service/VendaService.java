package com.Tgid.TesteTgid.Service;

import com.Tgid.TesteTgid.DTO.ItemResumoDTO;
import com.Tgid.TesteTgid.DTO.ItemVendaDTO;
import com.Tgid.TesteTgid.DTO.VendaResumoDTO;
import com.Tgid.TesteTgid.Model.ItemVenda;
import com.Tgid.TesteTgid.Model.Produto;
import com.Tgid.TesteTgid.Model.Usuario;
import com.Tgid.TesteTgid.Model.Venda;
import com.Tgid.TesteTgid.Repositories.ItemVendaRepository;
import com.Tgid.TesteTgid.Repositories.ProdutoRepository;
import com.Tgid.TesteTgid.Repositories.UsuarioRepository;
import com.Tgid.TesteTgid.Repositories.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public Venda realizarCompra(Long usuarioId, List<ItemVendaDTO> itensCompra) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<ItemVenda> itens = new ArrayList<>();
        double total = 0.0;

        for (ItemVendaDTO item : itensCompra) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

            if (produto.getQuantidade() < item.getQuantidade()) {
                throw new Exception("Estoque insuficiente para o produto: ");
            }

            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoRepository.save(produto);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(item.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());
            itemVenda.setSubtotal(produto.getPreco() * item.getQuantidade());

            itens.add(itemVenda);
            total += itemVenda.getSubtotal();
        }

        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setData(LocalDateTime.now());
        venda.setItens(itens);
        venda.setTotal(total);

        Venda vendaSalva = vendaRepository.save(venda);

        for (ItemVenda item : itens) {
            item.setVenda(vendaSalva); // vincula a venda
            itemVendaRepository.save(item);
        }

        return vendaSalva;
    }

    public List<Venda> listarVendas(){
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarVendaPorId(Long id){
        return vendaRepository.findById(id);
    }

    public void deletarVenda(Long id) throws Exception {
        if (!vendaRepository.existsById(id)){
            throw new Exception("Venda não encontrado");
        }
    }
    public List<VendaResumoDTO> gerarResumoVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        List<VendaResumoDTO> resumos = new ArrayList<>();

        for (Venda venda : vendas) {
            VendaResumoDTO resumo = new VendaResumoDTO();
            resumo.setIdVenda(venda.getId());
            resumo.setNomeUsuario(venda.getUsuario().getNome());
            resumo.setTotal(venda.getTotal());

            List<ItemResumoDTO> itens = new ArrayList<>();
            for (ItemVenda item : venda.getItens()) {
                ItemResumoDTO itemResumo = new ItemResumoDTO();
                itemResumo.setNomeProduto(item.getProduto().getNome());
                itemResumo.setQuantidade(item.getQuantidade());
                itemResumo.setSubtotal(item.getSubtotal());
                itens.add(itemResumo);
            }

            resumo.setItens(itens);
            resumos.add(resumo);
        }

        return resumos;
    }
}
