package com.example.exemplo.view;

import java.util.List;

public class PedidoForm {
    private Integer idCliente;
    private Integer idFuncionario;
    private List<ItemPedidoForm> itens;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public List<ItemPedidoForm> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoForm> itens) {
        this.itens = itens;
    }
}