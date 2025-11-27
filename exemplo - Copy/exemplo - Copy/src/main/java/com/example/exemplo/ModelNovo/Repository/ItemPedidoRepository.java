package com.example.exemplo.ModelNovo.Repository;

import com.example.exemplo.ModelNovo.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    java.util.List<ItemPedido> findByPedido_Id(Integer idPedido);
}