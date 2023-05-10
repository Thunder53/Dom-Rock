package com.domrock.model;
import com.domrock.dto.vendedor.cliente.ProdutoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="produto")
@Entity(name="produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cod_produto")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_produto;
    private String nome_produto;

    public Produto(ProdutoRequestDTO data) {
        this.cod_produto = data.cod_produto();
        this.nome_produto = data.nome_produto();
    }



}
