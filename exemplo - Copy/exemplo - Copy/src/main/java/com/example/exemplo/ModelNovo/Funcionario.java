package com.example.exemplo.ModelNovo;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDfuncionario")
    private Integer id;

    @Column(name = "nomeFuncionario")
    private String nomeFuncionario;

    @Column(name = "cargoFuncionario")
    private String cargoFuncionario;

    @Column(name = "salarioFuncionario")
    private Double salarioFuncionario;

    public Funcionario() {
    }

    public Funcionario(Integer id, String nomeFuncionario, String cargoFuncionario, Double salarioFuncionario) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.cargoFuncionario = cargoFuncionario;
        this.salarioFuncionario = salarioFuncionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public Double getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(Double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }
}