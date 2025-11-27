package com.example.exemplo.ModelNovo.Repository;

import com.example.exemplo.ModelNovo.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
}