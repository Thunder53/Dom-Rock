package com.domrock.controller;

import com.domrock.dto.produto.ProdutoRequestDTO;
import com.domrock.dto.produto.ProdutoResponseDTO;
import com.domrock.model.Produto;
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
@RequestMapping("/produto")
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
    @GetMapping("/topProdutos")
    public List<Map<String, Object>> getTopProdutos() {
        List<Map<String, Object>> topProdutos = new ArrayList<>();
        String sql = "SELECT p.nome_produto, " +
                "SUM(v.quant_vendida) AS total_vendido " +
                "FROM produto p " +
                "JOIN venda v ON p.cod_produto = v.fk_produto_cod_produto " +
                "GROUP BY nome_produto, v.fk_produto_cod_produto " +
                "ORDER BY total_vendido DESC " +
                "LIMIT 10";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> produto = new HashMap<>();
            produto.put("nome_produto", row.get("nome_produto"));
            produto.put("total_vendido", row.get("total_vendido"));
            topProdutos.add(produto);
        }
        return topProdutos;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/produto")
    public void saveProduto(@RequestBody ProdutoRequestDTO data){
        Produto produtodata = new Produto(data);
        repository.save(produtodata);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto-com-cliente/{id}")
    public List<Map<String, Object>> findByUsuario(@PathVariable Long id) {
        List<Map<String, Object>> produtos_cliente = new ArrayList<>();
        String sql = "select p.nome_produto, p.cod_produto, c.nome_cliente, c.cod_cliente, v.criada_em, v.quant_vendida, v.quant_estimada,\n" +
                "v.fk_cliente_cod_cliente, v.id_venda, v.fk_produto_cod_produto, v.fk_usuario_id, u.id\n" +
                "from produto p, cliente c, venda v, usuario u \n" +
                "where v.fk_cliente_cod_cliente = c.cod_cliente and v.fk_produto_cod_produto = p.cod_produto and v.fk_usuario_id = u.id and u.id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id);
        for (Map<String, Object> row : rows) {
            Map<String, Object> produto_cliente = new HashMap<>();
            produto_cliente.put("nome_produto", row.get("nome_produto"));
            produto_cliente.put("cod_produto", row.get("cod_produto"));
            produto_cliente.put("nome_cliente", row.get("nome_cliente"));
            produto_cliente.put("cod_cliente", row.get("cod_cliente"));
            produto_cliente.put("criada_em", row.get("criada_em"));
            produto_cliente.put("quant_estimada", row.get("quant_estimada"));
            produto_cliente.put("quant_vendida", row.get("quant_vendida"));
            produto_cliente.put("fk_cliente_cod_cliente", row.get("fk_cliente_cod_cliente"));
            produto_cliente.put("fk_produto_cod_produto", row.get("fk_produto_cod_produto"));
            produto_cliente.put("id_venda", row.get("id_venda"));
            produtos_cliente.add(produto_cliente);
        }
        return produtos_cliente;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/id-por-nome")
    public ResponseEntity<Long> buscarIdPorNome(@RequestParam String nome_produto) {
        Produto produto = repository.findByNome(nome_produto);
        if (produto != null) {
            return ResponseEntity.ok(produto.getCod_produto());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }

}
