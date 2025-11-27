package com.example.exemplo.ModelNovo.Repository;

import com.example.exemplo.ModelNovo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}