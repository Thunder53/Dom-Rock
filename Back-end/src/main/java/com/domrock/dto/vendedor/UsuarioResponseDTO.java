package com.domrock.dto.vendedor;

import com.domrock.model.Usuario;

public record UsuarioResponseDTO(String id, String nome, String email, String senha, String cpf, String contato, String acesso) {

    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getContato(), usuario.getAcesso());
    }
}
