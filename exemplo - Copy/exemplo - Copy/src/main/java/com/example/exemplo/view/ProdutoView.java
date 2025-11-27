package com.example.exemplo.view;

public class ProdutoView {
    private String sku;
    private String nome;
    private Double preco;
    private String nomeFornecedor;

    public ProdutoView(String sku, String nome, Double preco, String nomeFornecedor) {
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
}