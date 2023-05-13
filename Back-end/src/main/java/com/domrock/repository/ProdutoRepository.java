package com.domrock.repository;

import com.domrock.model.Produto;
import com.domrock.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//    Produto findByNome(String nome_produto);
}
