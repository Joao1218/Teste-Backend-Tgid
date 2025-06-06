package com.Tgid.TesteTgid.Controllers;

import com.Tgid.TesteTgid.Model.Produto;
import com.Tgid.TesteTgid.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Deixando claro que é um controller
@RestController
//Definindo qual o caminho pra acessarmos ele
@RequestMapping("/produto")
public class ProdutoController {

    //Carrega o repositorio se precisarmos ficar gerando o construtor novamente
        @Autowired
        private ProdutoService produtoService;

        //Mostrando que o metodo que vamos usar é o POST
        //Usamos o request body porque precisamos que a informação venha no body da aplicação
        @PostMapping
        public ResponseEntity<Produto> cadastrarProdutos(@RequestBody Produto produto) throws Exception {
            Produto novoProduto = produtoService.cadastrarProduto(produto);
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
        }

        //Mostrando que o metodo que vamos usar é o GET
        @GetMapping
        public ResponseEntity<List<Produto>> listarProdutos(){
            List<Produto> produtos = produtoService.listarProdutos();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
        /*Mostrando que o metodo que vamos usar é o GET e qua a pessoa precisa escolher o ID
        por isso o PATHVARIABLE
         */
        @GetMapping("/{id}")
        public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
            Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
            return produto
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        /*Mostrando que o metodo que vamos usar é o DELETE e qua a pessoa precisa escolher o ID
       por isso o PATHVARIABLE
        */
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarPorId(@PathVariable Long id) throws Exception {
           produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        }


    }
