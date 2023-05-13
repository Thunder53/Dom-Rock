package com.domrock.repository;

import com.domrock.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//   Produto findByNome(String nome);

}
