package com.example.exemplo.ModelNovo;

import jakarta.persistence.*;

@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @Column(name = "SKUproduto", length = 20)
    private String sku;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDfornecedor")
    private Fornecedor fornecedor;

    @Column(name = "nomeProduto")
    private String nomeProduto;

    @Column(name = "codigoBarra")
    private String codigoBarra;

    @Column(name = "precoProduto")
    private Double precoProduto;

    public Produto() {
    }

    public Produto(String sku, Fornecedor fornecedor, String nomeProduto, String codigoBarra, Double precoProduto) {
        this.sku = sku;
        this.fornecedor = fornecedor;
        this.nomeProduto = nomeProduto;
        this.codigoBarra = codigoBarra;
        this.precoProduto = precoProduto;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
}