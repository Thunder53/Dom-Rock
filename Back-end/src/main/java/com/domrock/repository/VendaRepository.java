package com.domrock.repository;

import com.domrock.model.Cliente;
import com.domrock.model.Produto;
import com.domrock.model.Usuario;
import com.domrock.model.Venda;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query("SELECT v FROM venda v WHERE v.fk_usuario_id = ?1")
    List<Venda> findByUsuario(Long fk_usuario_id);

    @Query("SELECT v FROM venda v WHERE v.fk_produto_cod_produto = ?1")
    List<Venda> findByProduto(Long id);

    @Query("SELECT v FROM venda v WHERE v.fk_usuario_id = ?1")
    List<Venda> findByVendedor(Long id);

    @Query("SELECT v FROM venda v WHERE MONTH(v.criada_em) = :mes")
    List<Venda> findByCriadaEmMonth(int mes);

    @Transactional
    @Modifying
    @Query(value = "UPDATE venda v SET v.quant_vendida = :quant_vendida WHERE v.id_venda = :id_venda")
    int updateQuantVendida(@Param("id_venda") Long id_venda, @Param("quant_vendida") Float quant_vendida);
}

