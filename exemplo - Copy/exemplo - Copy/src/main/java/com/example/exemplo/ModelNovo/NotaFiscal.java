package com.example.exemplo.ModelNovo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NotaFiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDnota")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "IDpedido", unique = true)
    private Pedido pedido;

    @Column(name = "NumeroNota")
    private String numeroNota;

    @Column(name = "DataEmissao")
    private LocalDateTime dataEmissao;

    @Column(name = "ValorImpostos")
    private Double valorImpostos;

    @Column(name = "ChaveAcesso")
    private String chaveAcesso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Double getValorImpostos() {
        return valorImpostos;
    }

    public void setValorImpostos(Double valorImpostos) {
        this.valorImpostos = valorImpostos;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }
}