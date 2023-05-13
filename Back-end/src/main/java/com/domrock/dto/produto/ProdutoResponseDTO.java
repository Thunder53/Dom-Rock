package com.domrock.dto.produto;

import com.domrock.model.Produto;

public record ProdutoResponseDTO(String nome_produto) {

    public ProdutoResponseDTO(Produto produto){
        this(produto.getNome_produto());
    }

}
