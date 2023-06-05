package com.domrock.model;
import lombok.Setter;


import com.domrock.dto.venda.VendaRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Table(name = "venda")
@Entity(name = "venda")
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
    private Date criada_em;
    @Column(name = "fk_usuario_id")
    private Long fk_usuario_id;
    private Long fk_cliente_cod_cliente;
    private Long fk_produto_cod_produto;

    public Venda(VendaRequestDTO data) {
        this.quant_vendida = data.quant_vendida();
        this.quant_estimada = data.quant_estimada();
        this.atualizada_em = data.atualizada_em();
        this.criada_em = data.criada_em();
        this.fk_usuario_id = data.fk_usuario_id();
        this.fk_cliente_cod_cliente = data.fk_cliente_cod_cliente();
        this.fk_produto_cod_produto = data.fk_produto_cod_produto();

    }
}
