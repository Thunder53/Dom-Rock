package com.domrock.repository;

import com.domrock.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
//    List<Venda> findByCriadaEmBetween(Date inicio, Date fim);
}
