package com.Tgid.TesteTgid.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    @JsonManagedReference
    private List<ItemVenda> itens;
    private Double total;

    public Venda() {
    }

    public Venda(Long id, LocalDateTime data, Usuario usuario, List<ItemVenda> itens, Double total) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
        this.itens = itens;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public void calcularTotal() {
        this.total = itens.stream()
                .mapToDouble(ItemVenda::getSubtotal)
                .sum();
    }
}
