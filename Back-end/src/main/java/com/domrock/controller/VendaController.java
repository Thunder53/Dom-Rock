package com.domrock.controller;

import com.domrock.dto.venda.AtualizarVendaRequestDTO;
import com.domrock.dto.venda.VendaResponseDTO;
import com.domrock.model.Venda;
import com.domrock.repository.VendaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Date;
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
        ;
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

    // @CrossOrigin(origins = "*", allowedHeaders = "*")
    // @GetMapping("/{mes}")
    // public List<Venda> listarVendasPorMes(@PathVariable String mes) {
    // // Cria um DateTimeFormatter para o formato "dd/MM/yyyy"
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // // Converte o mês em string para um objeto LocalDate
    // LocalDate data = LocalDate.parse("01/" + mes, formatter);
    // // Obtém a data de início do mês
    // LocalDate inicio = data.withDayOfMonth(1);
    // // Obtém a data de fim do mês
    // LocalDate fim = data.withDayOfMonth(data.lengthOfMonth());
    //
    // // Chama o método findByCriadaEmBetween do repositório para buscar as vendas
    // criadas no mês
    // return repository.findByCriadaEmBetween(
    // Date.from(inicio.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
    // Date.from(fim.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
    // );
    // }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarVenda(@PathVariable("id") Long id, @RequestBody AtualizarVendaRequestDTO requestDTO) {
        try {
            Venda vendaExistente = repository.findById(id).orElse(null);

            if (vendaExistente == null) {
                return ResponseEntity.notFound().build();
            }

            Date dataAtual = new Date();
            Date dataCriacao = vendaExistente.getCriada_em();
            long diffEmMilissegundos = dataAtual.getTime() - dataCriacao.getTime();
            long diffEmDias = TimeUnit.DAYS.convert(diffEmMilissegundos, TimeUnit.MILLISECONDS);

            if (diffEmDias > 7) {
                return ResponseEntity.badRequest().body("A quantidade estimada só pode ser atualizada dentro de 7 dias após a criação da venda.");
            }

            Float quantEstimada = requestDTO.getQuant_estimada();
            Date atualizada_em = new Date();

            Venda vendaAtualizada = new Venda(
                    vendaExistente.getId_venda(),
                    vendaExistente.getQuant_vendida(),
                    quantEstimada,
                    atualizada_em,
                    vendaExistente.getCriada_em(),
                    vendaExistente.getFk_usuario_id(),
                    vendaExistente.getFk_cliente_cod_cliente(),
                    vendaExistente.getFk_produto_cod_produto()
            );

            Venda vendaSalva = repository.save(vendaAtualizada);
            vendaSalva.setAtualizada_em(atualizada_em);
            repository.save(vendaSalva);

            VendaResponseDTO vendaResponseDTO = new VendaResponseDTO(vendaSalva);

            return ResponseEntity.ok(vendaResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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