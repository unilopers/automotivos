package com.example.exemplo.view;

public class FornecedorView {
    private Integer id;
    private String nome;

    public FornecedorView(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}