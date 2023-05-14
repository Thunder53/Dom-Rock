package com.domrock.controller;

import com.domrock.dto.venda.VendaResponseDTO;
import com.domrock.repository.VendaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("/filtro")
    public class FiltroController {

        @Autowired
        private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data")
    public List<Map<String,Object>> getFiltroData() {
        List<Map<String, Object>> filtroData = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em AS data_filtrada \n" +
                "FROM venda v \n" +
                "WHERE v.data_filtrada = v.criada_em \n" +
                "GROUP BY YEAR(v.criada_em), MONTH(v.criada_em) \n" +
                "ORDER BY ano DESC, mes DESC \n" +
                "LIMIT 10";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("data_filtrada", row.get("data_filtrada"));
            filtroData.add(venda);
        }
        return filtroData;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto")
    public List<Map<String,Object>> getFiltroProduto() {
        List<Map<String, Object>> filtroProduto = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, fk_produto_cod_produto AS produto_filtrado \n" +
                "FROM venda v \n"+
                "WHERE v.produto_filtrado = v.fk_produto_cod_produto"+
                "JOIN produto p ON v.fk_produto_cod_produto = p.id \n" +
                "ORDER BY produto_filtrado DESC \n" +
                "LIMIT 10";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("fk.produto_id", row.get("fk.produto_id"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("produto_filtrado", row.get("produto_filtrado"));
        }
        return filtroProduto;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/vendedor")
    public List<Map<String,Object>> getFiltroVendedor() {
        List<Map<String, Object>> filtroVendedor = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, u.nome, v.fk_usuario_id AS filtro_vendedor \n" +
            "FROM venda v \n" +
            "JOIN usuario u ON v.fk_usuario_id = u.id \n" +
            "GROUP BY u.nome, v.fk_usuario_id \n" +
            "ORDER BY filtro_vendedor DESC \n" +
            "LIMIT 10";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("fk_usuario_id", row.get("fk_usuario_id"));
            venda.put("nome_usuario", row.get("nome_usuario"));
            venda.put("filtro_vendedor", row.get("filtro_vendedor"));
    }
    return filtroVendedor;
}
    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }

}
