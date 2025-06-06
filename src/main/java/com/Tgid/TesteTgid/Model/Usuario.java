package com.Tgid.TesteTgid.Model;

import jakarta.persistence.*;

//Usada pra dizer que é uma entidade
@Entity
//Aqui definimos o nome da tabela
@Table(name = "tb_users")
public class Usuario {

    //Essa anotação diz que é a chave primaria
    @Id
    //É um estilo de como o id vai ser de numeros seguidos(ex:1,2,3,4,5...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    //Construtor vazio como padrão
    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
