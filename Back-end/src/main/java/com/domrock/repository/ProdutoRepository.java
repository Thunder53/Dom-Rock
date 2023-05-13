package com.domrock.repository;

import com.domrock.model.Produto;
import com.domrock.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//   Produto findByNome(String nome);

}
