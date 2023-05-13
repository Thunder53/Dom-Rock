package com.domrock.model;

import com.domrock.dto.cliente.ClienteRequestDTO;
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
    private Long fk_usuario_id;

    public Cliente(ClienteRequestDTO data) {
        this.nome_cliente = data.nome_cliente();
        this.nome_gerencia = data.nome_gerencia();
        this.fk_usuario_id = getFk_usuario_id();
    }

}