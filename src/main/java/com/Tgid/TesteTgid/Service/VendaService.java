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

//Anotação pra dizer que é um service
@Service
public class VendaService {

    //Carrega o repositorio se precisarmos ficar gerando o construtor novamente
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public Venda realizarCompra(Long usuarioId, List<ItemVendaDTO> itensCompra) throws Exception {
        //Fazendo verificação e tratando da excessão caso tenha
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        //Criando uma nova lista de itens com o valor zerado
        List<ItemVenda> itens = new ArrayList<>();
        double total = 0.0;

        //Percorrendo a lista e adicionando os itens e salvando o pedido
        for (ItemVendaDTO item : itensCompra) {
            //Fazendo verificação e tratando da excessão caso tenha
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

            //Fazendo verificação de estoque e tratando da excessão caso tenha
            if (produto.getQuantidade() < item.getQuantidade()) {
                throw new Exception("Estoque insuficiente para o produto: ");
            }

            //Fazendo subtração da quantidade comprada com a quantidade que tem no estoque
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoRepository.save(produto);

            //Criando os itens e adicionando a uma lista junto com o subtotal
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(item.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());
            itemVenda.setSubtotal(produto.getPreco() * item.getQuantidade());

            itens.add(itemVenda);
            total += itemVenda.getSubtotal();
        }

        //Adicionando a lista de produtos ao pedido e colocando quem comprou e o horario
        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setData(LocalDateTime.now());
        venda.setItens(itens);
        venda.setTotal(total);
        //Salvando os itens
        Venda vendaSalva = vendaRepository.save(venda);

        for (ItemVenda item : itens) {
            item.setVenda(vendaSalva);
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
        //Tratando de excessões
        if (!vendaRepository.existsById(id)){
            throw new Exception("Venda não encontrado");
        }
    }

    //Gerando relatorio/resumo de vendas e produtos
    public List<VendaResumoDTO> gerarResumoVendas() {
        //pegando as vendes cadastradas
        List<Venda> vendas = vendaRepository.findAll();

        //Criando a lista onde vamos colocar o resumo de vendas
        List<VendaResumoDTO> resumos = new ArrayList<>();

        //percorrendo a lista e incluindo as vendas que temos no banco
        for (Venda venda : vendas) {
            VendaResumoDTO resumo = new VendaResumoDTO();
            resumo.setIdVenda(venda.getId());
            resumo.setNomeUsuario(venda.getUsuario().getNome());
            resumo.setTotal(venda.getTotal());

            //Criando a lista onde vamos colocar o resumo de produtos
            List<ItemResumoDTO> itens = new ArrayList<>();
            //percorrendo a lista e incluindo os produtos e as quantidades que temos no banco
            for (ItemVenda item : venda.getItens()) {
                ItemResumoDTO itemResumo = new ItemResumoDTO();
                itemResumo.setNomeProduto(item.getProduto().getNome());
                itemResumo.setQuantidade(item.getQuantidade());
                itemResumo.setSubtotal(item.getSubtotal());
                itens.add(itemResumo);
            }

            //Adicionando e salvando as listas
            resumo.setItens(itens);
            resumos.add(resumo);
        }

        return resumos;
    }
}
