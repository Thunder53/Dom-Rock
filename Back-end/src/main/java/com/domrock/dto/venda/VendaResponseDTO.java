package com.domrock.dto.venda;

import com.domrock.model.Cliente;
import com.domrock.model.Venda;

import java.util.Date;

public record VendaResponseDTO(Float quant_vendida, Float quant_estimada, Date atualizada_em, Date criada_em) {
    public VendaResponseDTO(Venda venda){
        this(venda.getQuant_vendida(), venda.getQuant_vendida(), venda.getAtualizada_em(), venda.getCriada_em());
    }
}
