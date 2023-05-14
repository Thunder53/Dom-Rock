package com.domrock.repository;

import com.domrock.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM produto p WHERE p.nome_produto = ?1")
    Produto findByNome(String nomeProduto);

}
