package com.example.exemplo.ModelNovo.Repository;

import com.example.exemplo.ModelNovo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}