package com.domrock.dto.venda;

import com.domrock.model.Venda;

import java.util.Date;

public record VendaResponseDTO(Long id_venda, Float quant_vendida, Float quant_estimada, Date atualizada_em, Date criada_em, Long fk_usuario_id, Long fk_cliente_cod_cliente, Long fk_produto_cod_produto) {
    public VendaResponseDTO(Venda venda){
        this(venda.getId_venda(), venda.getQuant_vendida(), venda.getQuant_estimada(), venda.getAtualizada_em(), venda.getCriada_em(), venda.getFk_usuario_id(), venda.getFk_cliente_cod_cliente(), venda.getFk_produto_cod_produto());
    }
}