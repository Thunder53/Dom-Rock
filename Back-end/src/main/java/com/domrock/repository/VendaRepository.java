package com.domrock.repository;

import com.domrock.model.Cliente;
import com.domrock.model.Usuario;
import com.domrock.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v FROM venda v WHERE v.fk_usuario_id = ?1")
    List<Venda> findByUsuario(Long fk_usuario_id);


    @Query("SELECT v,u FROM venda v, usuario u WHERE v.fk_usuario_id = u.id and v.quant_vendida > v.quant_estimada")
    List<Venda> findVendedoresAcimaMeta();
    
    @Query("SELECT v FROM venda v WHERE v.fk_usuario_id = ?1")
    List<Venda> findByVendedor(Long id);

    @Query("SELECT v FROM venda v WHERE MONTH(v.criada_em) = :mes")
    List<Venda> findByCriadaEmMonth(int mes);

}
