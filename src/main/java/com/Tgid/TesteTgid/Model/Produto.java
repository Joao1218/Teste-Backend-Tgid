package com.Tgid.TesteTgid.Model;


import jakarta.persistence.*;
//Usada pra dizer que é uma entidade
@Entity
//Aqui definimos o nome da tabela
@Table(name = "tb_product")
public class Produto {

    //Essa anotação diz que é a chave primaria
    @Id
    //É um estilo de como o id vai ser de numeros seguidos(ex:1,2,3,4,5...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public Produto() {
    }

    public Produto(Long id, String nome, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
