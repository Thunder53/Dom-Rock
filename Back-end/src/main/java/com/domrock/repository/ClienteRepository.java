package com.domrock.repository;

import com.domrock.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {



    @Query("SELECT c FROM cliente c WHERE c.fk_usuario_id = ?1")
    List<Cliente> findByVendedor(Long id);

}
