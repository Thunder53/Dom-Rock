package com.domrock.dto.vendedor.cliente;


import com.domrock.model.Produto;

public record ProdutoResponseDTO(Long cod_produto, String nome_produto) {

    public ProdutoResponseDTO(Produto produto){
        this(produto.getCod_produto(), produto.getNome_produto());
    }
}
