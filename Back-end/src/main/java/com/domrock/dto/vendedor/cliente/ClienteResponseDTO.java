package com.domrock.dto.vendedor.cliente;

import com.domrock.model.Cliente;
import com.domrock.model.Vendedor;

public record ClienteResponseDTO(Long cod_cliente, String nome_cliente, String nome_gerencia, String produto_a,
                                 String poduto_b, String produto_c) {

    public ClienteResponseDTO(Cliente cliente){
        this(cliente.getCod_cliente(), cliente.getNome_cliente(), cliente.getNome_gerencia(), cliente.getProduto_a(),
                cliente.getProduto_b(), cliente.getProduto_c());
    }
}
