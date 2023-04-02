package com.domrock.dto;

import com.domrock.model.Vendedor;

public record VendedorResponseDTO(Long id, String nome, String email, String senha, String cpf, String contato) {

    public VendedorResponseDTO(Vendedor vendedor){
        this(vendedor.getId(), vendedor.getNome(), vendedor.getEmail(), vendedor.getSenha(), vendedor.getCpf(), vendedor.getContato());
    }
}
