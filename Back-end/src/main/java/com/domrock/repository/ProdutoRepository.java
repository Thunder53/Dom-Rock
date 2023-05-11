package com.domrock.repository;

import com.domrock.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("Select v.quant_vendida, v.fk_produto_cod_produto, p.cod_produto, p.nome_produto \n" +
            "From venda v, produto p where v.fk_produto_cod_produto = p.cod_produto \n" +
            "ORDER BY v.quant_vendida ASC")
    public List<Object[]> buscarprodutocomvenda();



    public class ProdutocomVenda {
        private Long cod_produto;
        private String nome_produto;
        private Float quant_vendida;
        private Long fk_produto_cod_produto;

        public ProdutocomVenda() {
        }

        public ProdutocomVenda(Long cod_produto, String nome_produto, Float quant_vendida, Long fk_produto_cod_produto) {
            this.cod_produto = cod_produto;
            this.nome_produto = nome_produto;
            this.quant_vendida = quant_vendida;
            this.fk_produto_cod_produto = fk_produto_cod_produto;
        }


        public Long getCod_produto() {
            return cod_produto;
        }

        public void setCod_produto(Long cod_produto) {
            this.cod_produto = cod_produto;
        }

        public String getNome_produto() {
            return nome_produto;
        }

        public void setNome_produto(String nome_produto) {
            this.nome_produto = nome_produto;
        }

        public Float getQuant_vendida() {
            return quant_vendida;
        }

        public void setQuant_vendida(Float quant_vendida) {
            this.quant_vendida = quant_vendida;
        }

        public Long getFk_produto_cod_produto() {
            return fk_produto_cod_produto;
        }

        public void setFk_produto_cod_produto(Long fkProdutoCodProduto) {
            this.fk_produto_cod_produto = fkProdutoCodProduto;
        }

//        public void setfk_produto_cod_produto(Long aLong) {
//            fk_produto_cod_produto = aLong;
//        }
//
//        public void setcNome_produto(String s) {
//            nome_produto = s;
//        }
//
//
//        public void setquant_vendida(Float aFloat) {
//            quant_vendida = aFloat;
//        }
//
//
//        public void setcCod_produto(Long aLong) {
//            cod_produto = aLong;
//        }
//    }
}
}