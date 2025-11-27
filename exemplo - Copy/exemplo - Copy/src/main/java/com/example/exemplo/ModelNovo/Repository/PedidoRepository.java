package com.example.exemplo.ModelNovo.Repository;

import com.example.exemplo.ModelNovo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}