package com.Tgid.TesteTgid.Controllers;

import com.Tgid.TesteTgid.Model.Produto;
import com.Tgid.TesteTgid.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

        @Autowired
        private ProdutoService produtoService;

        @PostMapping
        public ResponseEntity<Produto> cadastrarProdutos(@RequestBody Produto produto) throws Exception {
            Produto novoProduto = produtoService.cadastrarProduto(produto);
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<Produto>> listarProdutos(){
            List<Produto> produtos = produtoService.listarProdutos();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
            Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
            return produto
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarPorId(@PathVariable Long id) throws Exception {
           produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        }


    }
