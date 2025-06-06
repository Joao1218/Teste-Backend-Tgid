package com.Tgid.TesteTgid.DTO;

import java.util.List;

//Usado pra deixar o fluxo de dados melhor e mais seguro sem expor todas as informações das classes
public class VendaResumoDTO {
    private Long idVenda;
    private String nomeUsuario;
    private double total;
    private List<ItemResumoDTO> itens;

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemResumoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemResumoDTO> itens) {
        this.itens = itens;
    }
}
