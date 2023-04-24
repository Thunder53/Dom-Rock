package com.domrock.dto.vendedor.cliente;

import com.domrock.model.Cliente;

public record ClienteResponseDTO(Long cod_cliente, String nome_cliente, String nome_gerencia, Long fk_usuario_id) {

    public ClienteResponseDTO(Cliente cliente){
        this(cliente.getCod_cliente(), cliente.getNome_cliente(), cliente.getNome_gerencia(), cliente.getFk_usuario_id());
    }
}
