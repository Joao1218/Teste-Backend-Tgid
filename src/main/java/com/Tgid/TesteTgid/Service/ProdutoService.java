package com.Tgid.TesteTgid.Service;

import com.Tgid.TesteTgid.Model.Produto;
import com.Tgid.TesteTgid.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Anotação pra dizer que é um service
@Service
public class ProdutoService {

    //Carrega o repositorio se precisarmos ficar gerando o construtor novamente
    @Autowired
    private ProdutoRepository repository;

    public Produto cadastrarProduto(Produto produto) throws Exception {
        //Tratamento de excessões
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
        //Tratamento de excessões
        if(!repository.existsById(id)){
            throw new Exception("Produto não encontrado");
        }
        repository.deleteById(id);
    }

}
