package com.Tgid.TesteTgid.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

//Usada pra dizer que é uma entidade
@Entity
//Aqui definimos o nome da tabela
@Table(name = "tb_itemVenda" )
public class ItemVenda {

    //Essa anotação diz que é a chave primaria
    @Id
    //É um estilo de como o id vai ser de numeros seguidos(ex:1,2,3,4,5...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Muitos objetos desta entidade estão relacionados a um único objeto de outra entidade.
    @ManyToOne
    //Define a chave estrangeira
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    private Double precoUnitario;

    private Double subtotal;

    //Muitos objetos desta entidade estão relacionados a um único objeto de outra entidade.
    @ManyToOne
    //Define a chave estrangeira
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private Venda venda;

    public ItemVenda() {
    }

    public ItemVenda(Long id, Produto produto, Integer quantidade, Double precoUnitario, Double subtotal, Venda venda) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
        this.venda = venda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = quantidade*precoUnitario;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
