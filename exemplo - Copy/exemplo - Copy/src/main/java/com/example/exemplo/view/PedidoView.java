package com.example.exemplo.view;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoView {
    private Integer id;
    private Integer idCliente;
    private Integer idFuncionario;
    private String status;
    private LocalDateTime dataPedido;
    private Double valorTotal;
    private List<ItemPedidoView> itens;

    public PedidoView(Integer id, Integer idCliente, Integer idFuncionario, String status, LocalDateTime dataPedido, Double valorTotal, List<ItemPedidoView> itens) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.status = status;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public List<ItemPedidoView> getItens() {
        return itens;
    }
}