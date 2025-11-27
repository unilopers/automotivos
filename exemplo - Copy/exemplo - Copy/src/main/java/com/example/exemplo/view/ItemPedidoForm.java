package com.example.exemplo.view;

public class ItemPedidoForm {
    private String sku;
    private String skuProduto;
    private Integer quantidade;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSkuProduto() {
        return skuProduto;
    }

    public void setSkuProduto(String skuProduto) {
        this.skuProduto = skuProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}