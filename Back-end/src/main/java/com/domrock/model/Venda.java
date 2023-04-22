package com.domrock.model;
import com.domrock.dto.venda.VendaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="venda")
@Entity(name="venda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_venda")

public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_venda;
    private Float quant_vendida;
    private Float quant_estimada;
    private Date atualizada_em;
    private Long fk_cliente_cod_cliente;


    public Venda(VendaRequestDTO data){
        this.quant_vendida = data.quant_vendida();
        this.quant_estimada = data.quant_estimada();
        this.atualizada_em = data.atualizada_em();
    }

}
