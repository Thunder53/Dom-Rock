package com.domrock.repository;

import com.domrock.model.Cliente;
import com.domrock.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query("SELECT c FROM cliente c WHERE c.fk_usuario_id = ?1")
    List<Cliente> findByVendedor(Long id);

    @Query("SELECT p FROM cliente p WHERE p.nome_cliente = ?1")
    Cliente findByNome(String nomeCliente);
}
