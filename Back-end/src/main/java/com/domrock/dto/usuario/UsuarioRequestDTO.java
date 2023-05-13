package com.domrock.dto.usuario;

public record UsuarioRequestDTO(Long id, String nome, String email, String senha, String cpf, String contato, String acesso) {
}
