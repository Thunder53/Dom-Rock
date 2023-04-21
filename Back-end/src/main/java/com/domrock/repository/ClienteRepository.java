package com.domrock.repository;

import com.domrock.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT v.quant_vendida, v.fk_cliente_cod_cliente, c.cod_cliente, c.nome_gerencia FROM venda v JOIN cliente c on v.fk_cliente_cod_cliente = c.cod_cliente")
    static List<Object[]> buscarClientesComVendas() {
        return null;
    }
}
