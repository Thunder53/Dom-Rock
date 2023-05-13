package com.domrock.dto.cliente;

import java.util.List;

public record ClienteRequestDTO(String nome_cliente, String nome_gerencia, Long fk_usuario_id) {
}
