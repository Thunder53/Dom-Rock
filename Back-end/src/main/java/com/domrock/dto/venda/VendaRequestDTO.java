package com.domrock.dto.venda;

import java.util.Date;

public record VendaRequestDTO(Long id_venda, Float quant_vendida, Float quant_estimada, Date atualizada_em, Date criada_em, Long fk_usuario_id, Long fk_cliente_cod_cliente, Long fk_produto_cod_produto) {
}
