package com.example.exemplo.view;

public class ItemPedidoView {
    private String sku;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;

    public ItemPedidoView(String sku, Integer quantidade, Double precoUnitario, Double subtotal) {
        this.sku = sku;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }

    public String getSku() {
        return sku;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }
}