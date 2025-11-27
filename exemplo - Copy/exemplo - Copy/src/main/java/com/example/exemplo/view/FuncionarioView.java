package com.example.exemplo.view;

public class FuncionarioView {
    private Integer id;
    private String nome;
    private String cargo;

    public FuncionarioView(Integer id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }
}