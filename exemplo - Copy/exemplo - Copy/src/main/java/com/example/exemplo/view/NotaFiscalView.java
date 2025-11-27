package com.example.exemplo.view;

import java.time.LocalDateTime;

public class NotaFiscalView {
    private Integer id;
    private Integer idPedido;
    private String numeroNota;
    private LocalDateTime dataEmissao;
    private Double valorImpostos;
    private String chaveAcesso;

    public NotaFiscalView(Integer id, Integer idPedido, String numeroNota, LocalDateTime dataEmissao, Double valorImpostos, String chaveAcesso) {
        this.id = id;
        this.idPedido = idPedido;
        this.numeroNota = numeroNota;
        this.dataEmissao = dataEmissao;
        this.valorImpostos = valorImpostos;
        this.chaveAcesso = chaveAcesso;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public Double getValorImpostos() {
        return valorImpostos;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }
}