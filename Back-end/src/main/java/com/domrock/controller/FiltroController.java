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
    @GetMapping("/data/JANEIRO")
    public List<Map<String,Object>> getFiltroDataJaneiro() {
        List<Map<String, Object>> filtroDataJaneiro = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as JANEIRO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 1";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("JANEIRO", row.get("JANEIRO"));
            filtroDataJaneiro.add(venda);
        }
        return filtroDataJaneiro;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/FEVEREIRO")
    public List<Map<String,Object>> getFiltroDataFevereiro() {
        List<Map<String, Object>> filtroDataFevereiro = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as JANEIRO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 2";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("FEVEREIRO", row.get("FEVEREIRO"));
            filtroDataFevereiro.add(venda);
        }
        return filtroDataFevereiro;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/MARCO")
    public List<Map<String,Object>> getFiltroDataMarco() {
        List<Map<String, Object>> filtroDataMarco = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as MARCO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 3";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("MARCO", row.get("MARCO"));
            filtroDataMarco.add(venda);
        }
        return filtroDataMarco;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/ABRIL")
    public List<Map<String,Object>> getFiltroDataAbril() {
        List<Map<String, Object>> filtroDataAbril = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as ABRIL FROM venda v WHERE DATE_PART ('month', v.criada_em) = 4";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("ABRIL", row.get("ABRIL"));
            filtroDataAbril.add(venda);
        }
        return filtroDataAbril;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/MAIO")
    public List<Map<String,Object>> getFiltroDataMaio() {
        List<Map<String, Object>> filtroDataMaio = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as MAIO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 5";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("MAIO", row.get("MAIO"));
            filtroDataMaio.add(venda);
        }
        return filtroDataMaio;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/JUNHO")
    public List<Map<String,Object>> getFiltroDataJunho() {
        List<Map<String, Object>> filtroDataJunho = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as JUNHO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 6";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("JUNHO", row.get("JUNHO"));
            filtroDataJunho.add(venda);
        }
        return filtroDataJunho;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/JULHO")
    public List<Map<String,Object>> getFiltroDataJulho() {
        List<Map<String, Object>> filtroDataJulho = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as JULHO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 7";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("JULHO", row.get("JULHO"));
            filtroDataJulho.add(venda);
        }
        return filtroDataJulho;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/AGOSTO")
    public List<Map<String,Object>> getFiltroDataAgosto() {
        List<Map<String, Object>> filtroDataAgosto = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as AGOSTO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 8";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("AGOSTO", row.get("AGOSTO"));
            filtroDataAgosto.add(venda);
        }
        return filtroDataAgosto;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/SETEMBRO")
    public List<Map<String,Object>> getFiltroDataSetembro() {
        List<Map<String, Object>> filtroDataSetembro = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as SETEMBRO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 9";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("SETEMBRO", row.get("SETEMBRO"));
            filtroDataSetembro.add(venda);
        }
        return filtroDataSetembro;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/OUTUBRO")
    public List<Map<String,Object>> getFiltroDataOutubro() {
        List<Map<String, Object>> filtroDataOutubro = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as OUTUBRO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 10";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("OUTUBRO", row.get("OUTUBRO"));
            filtroDataOutubro.add(venda);
        }
        return filtroDataOutubro;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/NOVEMBRO")
    public List<Map<String,Object>> getFiltroDataNovembro() {
        List<Map<String, Object>> filtroDataNovembro = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as NOVEMBRO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 11";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("NOVEMBRO", row.get("NOVEMBRO"));
            filtroDataNovembro.add(venda);
        }
        return filtroDataNovembro;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/data/DEZEMBRO")
    public List<Map<String,Object>> getFiltroDataDezembro() {
        List<Map<String, Object>> filtroDataDezembro = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.criada_em as DEZEMBRO FROM venda v WHERE DATE_PART ('month', v.criada_em) = 12";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("criada_em", row.get("criada_em"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("NOVEMBRO", row.get("NOVEMBRO"));
            filtroDataDezembro.add(venda);
        }
        return filtroDataDezembro;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto")
    public List<Map<String,Object>> getFiltroProduto() {
        List<Map<String, Object>> filtroProduto = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, v.fk_produto_cod_produto, p.nome_produto, p.cod_produto\n" +
                "FROM venda v, produto p\n" +
                "WHERE v.fk_produto_cod_produto = p.cod_produto \n" +
                "LIMIT 10";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("fk_produto_cod_produto", row.get("fk_produto_cod_produto"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("nome_produto", row.get("nome_produto"));
            venda.put("cod_produto", row.get("cod_produto"));
            filtroProduto.add(venda);
        }
        return filtroProduto;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/vendedor")
    public List<Map<String,Object>> getFiltroVendedor() {
        List<Map<String, Object>> filtroVendedor = new ArrayList<>();
        String sql = "SELECT v.quant_estimada, v.quant_vendida, u.nome, v.fk_usuario_id FROM venda v, usuario u WHERE  v.fk_usuario_id = u.id LIMIT 10";


        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> venda = new HashMap<>();
            venda.put("fk_usuario_id", row.get("fk_usuario_id"));
            venda.put("quant_estimada", row.get("quant_estimada"));
            venda.put("quant_vendida", row.get("quant_vendida"));
            venda.put("nome_usuario", row.get("nome_usuario"));
            venda.put("filtro_vendedor", row.get("filtro_vendedor"));
            filtroVendedor.add(venda);
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
