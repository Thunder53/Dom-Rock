package com.domrock.repository;

import com.domrock.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query("SELECT v FROM venda v WHERE v.fk_usuario_id = ?1")
    List<Venda> findByVendedor(Long id);
}
