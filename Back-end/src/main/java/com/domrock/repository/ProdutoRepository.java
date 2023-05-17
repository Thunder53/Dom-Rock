package com.domrock.repository;

import com.domrock.model.Cliente;
import com.domrock.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM produto p WHERE p.nome_produto = ?1")
    Produto findByNome(String nomeProduto);


    @Query("SELECT v FROM venda v WHERE v.fk_usuario_id = ?1")
    List<Produto> findByUsuario(Long id);

}
