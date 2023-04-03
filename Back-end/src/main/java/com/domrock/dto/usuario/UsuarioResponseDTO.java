package com.domrock.dto.usuario;

import com.domrock.model.Usuario;

public record UsuarioResponseDTO(Long id, String email, String senha, String acesso) {

    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getAcesso().getValor());
    }
}
