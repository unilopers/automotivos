package com.example.exemplo.ModelNovo;

import jakarta.persistence.*;

@Entity
@Table(name = "Fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDfornecedor")
    private Integer id;

    @Column(name = "nomeFornecedor")
    private String nomeFornecedor;

    public Fornecedor() {
    }

    public Fornecedor(Integer id, String nomeFornecedor) {
        this.id = id;
        this.nomeFornecedor = nomeFornecedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
}