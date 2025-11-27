package com.example.exemplo.ModelNovo.Repository;

import com.example.exemplo.ModelNovo.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {
    Optional<NotaFiscal> findByPedido_Id(Integer idPedido);
}