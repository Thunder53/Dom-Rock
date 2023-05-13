package com.domrock.controller;

import com.domrock.dto.produto.ProdutoRequestDTO;
import com.domrock.dto.produto.ProdutoResponseDTO;
import com.domrock.model.Produto;
import com.domrock.model.Usuario;
import com.domrock.repository.ProdutoRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto")
    public List<ProdutoResponseDTO> getAll(){
        List<ProdutoResponseDTO> produtoList = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
        return produtoList;
    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto-com-cliente")
    public List<Map<String, Object>> getProdutoComCliente() {
        List<Map<String, Object>> produtos_cliente = new ArrayList<>();
        String sql = "select p.nome_produto, p.cod_produto, c.nome_cliente, c.cod_cliente, v.criada_em, \n" +
                "v.fk_cliente_cod_cliente, v.fk_produto_cod_produto\n" +
                "from produto p, cliente c, venda v \n" +
                "where v.fk_cliente_cod_cliente = c.cod_cliente and v.fk_produto_cod_produto = p.cod_produto";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> produto_cliente = new HashMap<>();
            produto_cliente.put("nome_produto", row.get("nome_produto"));
            produto_cliente.put("cod_produto", row.get("cod_produto"));
            produto_cliente.put("nome_cliente", row.get("nome_cliente"));
            produto_cliente.put("cod_cliente", row.get("cod_cliente"));
            produto_cliente.put("criada_em", row.get("criada_em"));
            produto_cliente.put("fk_cliente_cod_cliente", row.get("fk_cliente_cod_cliente"));
            produto_cliente.put("fk_produto_cod_produto", row.get("fk_produto_cod_produto"));
            produtos_cliente.add(produto_cliente);
        }
        return produtos_cliente;
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/produto-por-nome")
//    public ResponseEntity<Long> buscarIdPorNome(@RequestParam String nome) {
//        Produto produto = repository.findByNome(nome);
//        if (produto != null) {
//            return ResponseEntity.ok(produto.getCod_produto());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }

}
