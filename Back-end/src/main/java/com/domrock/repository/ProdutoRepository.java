package com.domrock.repository;

import com.domrock.model.Produto;
import com.domrock.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Collection<Object> findAllByAcesso(String produto);

    Usuario findByNome(String nome);
}
