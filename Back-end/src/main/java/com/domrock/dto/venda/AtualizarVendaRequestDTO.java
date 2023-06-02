package com.domrock.dto.venda;

import lombok.Data;

import java.util.Date;

@Data
public class AtualizarVendaRequestDTO {
    private Float quant_estimada;
    private Date atualizada_em;
}
