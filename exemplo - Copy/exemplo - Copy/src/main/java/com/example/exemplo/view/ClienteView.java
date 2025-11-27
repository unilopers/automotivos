package com.example.exemplo.view;

public class ClienteView {
    private Integer id;
    private String nome;
    private String telefone;
    private String endereco;

    public ClienteView(Integer id, String nome, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }
}