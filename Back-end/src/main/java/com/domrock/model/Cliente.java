package com.domrock.model;

import com.domrock.dto.vendedor.cliente.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
    @OneToMany()
    @JoinColumn(name="fk_cliente_cod_cliente")
    private List<Venda> vendaList;
    public Cliente(ClienteRequestDTO data) {
        this.nome_cliente = data.nome_cliente();
        this.nome_gerencia = data.nome_gerencia();
        vendaList = new ArrayList<Venda>();
    }

}