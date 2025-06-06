package com.Tgid.TesteTgid.Service;

import com.Tgid.TesteTgid.Model.Produto;
import com.Tgid.TesteTgid.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto cadastrarProduto(Produto produto) throws Exception {
        if(produto.getId() != null){
            throw new Exception("Produto já cadastrado");
        }
        return repository.save(produto);
    }

    public List<Produto> listarProdutos(){
        return repository.findAll();
    }

    public Optional<Produto> buscarProdutoPorId(Long id){
        return repository.findById(id);
    }

    public void deletarProduto(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Produto não encontrado");
        }
        repository.deleteById(id);
    }

}
