package com.domrock.dto.venda;

import java.util.Date;

public record VendaRequestDTO(Float quant_vendida, Float quant_estimada, Date atualizada_em, Date criada_em) {
}
