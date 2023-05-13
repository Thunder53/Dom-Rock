package com.domrock.model;

<<<<<<< HEAD
import com.domrock.dto.produto.ProdutoRequestDTO;
=======
>>>>>>> 4c6e17073f2493510f08526da82c46aa74e90c03
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

