package com.domrock.repository;

import com.domrock.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select u.nome, u.id, u.acesso, c.cod_cliente, c.nome_cliente, c.nome_gerencia, c.fk_usuario_id from usuario u, cliente c where c.fk_usuario_id = u.id and u.acesso = 'vendedor'")
    public List<Object[]> buscarClientesComVendas();

}
