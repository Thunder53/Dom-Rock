package com.domrock.controller;

import com.domrock.dto.venda.VendaResponseDTO;
import com.domrock.model.Venda;
import com.domrock.repository.VendaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<VendaResponseDTO> getAll() {
        List<VendaResponseDTO> vendaList = repository.findAll().stream().map(VendaResponseDTO::new).toList();
        return vendaList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        try {
            Venda novaVenda = repository.save(venda);
            return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/topVendedores")
    public List<Map<String, Object>> getTopVendedores() {
        List<Map<String, Object>> topVendedores = new ArrayList<>();
        String sql = "SELECT u.nome, v.fk_usuario_id, SUM(v.quant_vendida) AS total_vendido \n" +
                "FROM venda v \n" +
                "JOIN usuario u ON v.fk_usuario_id = u.id \n" +
                "GROUP BY u.nome, v.fk_usuario_id \n" +
                "ORDER BY total_vendido DESC \n" +
                "LIMIT 10";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> vendedor = new HashMap<>();
            vendedor.put("fk_usuario_id", row.get("fk_usuario_id"));
            vendedor.put("nome_usuario", row.get("nome"));
            vendedor.put("total_vendido", row.get("total_vendido"));
            topVendedores.add(vendedor);
        }
        return topVendedores;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/verificar-quantidades/{fk_usuario_id}")
    public boolean verificarQuantidades(@PathVariable Long fk_usuario_id) {
        List<Venda> vendas = repository.findByUsuario(fk_usuario_id);
        int quantVendidaMaior = 0;
        int quantVendidaMenor = 0;

        for (Venda venda : vendas) {
            if (venda.getQuant_vendida() > venda.getQuant_estimada()) {
                quantVendidaMaior++;
            } else if (venda.getQuant_vendida() < venda.getQuant_estimada()) {
                quantVendidaMenor++;
            }
        }

        return quantVendidaMaior > quantVendidaMenor;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/filtro-produto/{fk_produto_cod_produto}")
    public List<VendaResponseDTO> filtroProduto(@PathVariable Long fk_produto_cod_produto) {
        List<Venda> vendas = repository.findByProduto(fk_produto_cod_produto);
        return vendas.stream().map(VendaResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/filtro-vendedor/{fk_usuario_id}")
    public List<VendaResponseDTO> filtroVendedor(@PathVariable Long fk_usuario_id) {
        List<Venda> vendas = repository.findByVendedor(fk_usuario_id);
        return vendas.stream().map(VendaResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/filtro-data/{mes}")
    public List<VendaResponseDTO> buscarVendasPorMes(@PathVariable int mes) {
        List<Venda> vendas = repository.findByCriadaEmMonth(mes);
        return vendas.stream().map(VendaResponseDTO::new).toList();
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Transactional
    @Modifying
    @PutMapping("/atualizar_venda/{id_venda}/{quant_vendida}")
    public ResponseEntity<Object> atualizarVenda(@PathVariable Long id_venda, @PathVariable Float quant_vendida) {
        Venda venda = repository.findById(id_venda).orElse(null);

        if (venda != null) {
            if (venda.getAtualizada_em() == null) {
                Date dataAtual = new Date();
                Date dataCriacao = venda.getCriada_em();
                long diff = dataAtual.getTime() - dataCriacao.getTime();
                long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                if (diffDays <= 7) {
                    venda.setQuant_vendida(quant_vendida);
                    venda.setAtualizada_em(new Date());
                    repository.save(venda);
                    return ResponseEntity.ok().body("{\"message\": \"Venda atualizada com sucesso!\"}");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Fora do prazo\"}");
                }
            } else {
                return ResponseEntity.ok().body("{\"message\": \"Venda já atualizada\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Venda não encontrada\"}");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Transactional
    @Modifying
    @PutMapping("/cadastrar_quantidade/{id_venda}/{quant_vendida}")
    public ResponseEntity<Object> cadastrarQuantidade(@PathVariable Long id_venda, @PathVariable Float quant_vendida) {
        Venda venda = repository.findById(id_venda).orElse(null);

        if (venda != null) {
            if (venda.getQuant_vendida() == null) {
                venda.setQuant_vendida(quant_vendida);
                repository.save(venda);
                return ResponseEntity.ok().body("{\"message\": \"Quantidade vendida cadastrada com sucesso!\"}");
            } else {
                return ResponseEntity.ok().body("{\"message\": \"Quantidade vendida já cadastrada\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Venda não encontrada\"}");
    }



    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }

}
