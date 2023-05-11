package com.domrock.controller;

import com.domrock.dto.venda.VendaResponseDTO;
import com.domrock.dto.vendedor.cliente.ClienteResponseDTO;
import com.domrock.dto.vendedor.cliente.ProdutoResponseDTO;
import com.domrock.model.Cliente;
import com.domrock.model.Produto;
import com.domrock.repository.ClienteRepository;
import com.domrock.repository.ProdutoRepository;
import com.domrock.repository.VendaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto")
    public List<ProdutoResponseDTO> getAll(){
        List<ProdutoResponseDTO> produtoList = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
        return produtoList;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto-com-venda")
    public List<ProdutoRepository.ProdutocomVenda>buscarprodutocomvenda() {
        List<Object[]> resultado = repository.buscarprodutocomvenda();
        List<ProdutoRepository.ProdutocomVenda> produtos_venda = new ArrayList<>();

        for (Object[] obj : resultado) {
            ProdutoRepository.ProdutocomVenda produto_venda = new ProdutoRepository.ProdutocomVenda();
            produto_venda.setCod_produto(Long.valueOf(Long.toString((Long) obj[1])));
            produto_venda.setNome_produto((String) obj[3]);
            produto_venda.setQuant_vendida((Float.valueOf(Float.toString((Float) obj[0]))));
            produto_venda.setFk_produto_cod_produto((Long.valueOf(Long.toString((Long) obj[2]))));
            produtos_venda.add(produto_venda);

        }

        return produtos_venda;
    }


    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }

}
