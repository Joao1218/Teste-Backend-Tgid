package com.Tgid.TesteTgid.DTO;

//Usado pra deixar o fluxo de dados melhor e mais seguro sem expor todas as informações das classes
public class ItemVendaDTO {
    private Long produtoId;
    private int quantidade;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
