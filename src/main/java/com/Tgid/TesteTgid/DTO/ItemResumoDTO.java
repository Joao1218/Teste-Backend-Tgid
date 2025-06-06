package com.Tgid.TesteTgid.DTO;

//Usado pra deixar o fluxo de dados melhor e mais seguro sem expor todas as informações das classes
public class ItemResumoDTO {
    private String nomeProduto;
    private int quantidade;
    private double subtotal;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
