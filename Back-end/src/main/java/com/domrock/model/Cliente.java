package com.domrock.model;

import com.domrock.dto.vendedor.VendedorRequestDTO;
import com.domrock.dto.vendedor.cliente.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="cliente")
@Entity(name="cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cod_cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_cliente;
    private String nome_cliente;
    private String nome_gerencia;
    private String produto_a;
    private String produto_b;
    private String produto_c;

    public Cliente(ClienteRequestDTO data) {
        this.nome_cliente = data.nome_cliente();
        this.nome_gerencia = data.nome_gerencia();
        this.produto_a = data.produto_a();
        this.produto_b = data.produto_b();
        this.produto_c = data.produto_c();
    }
}