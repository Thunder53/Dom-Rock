package com.domrock.repository;

import com.domrock.model.Produto;
import com.domrock.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

