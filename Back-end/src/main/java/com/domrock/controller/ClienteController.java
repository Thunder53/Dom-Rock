package com.domrock.controller;

import com.domrock.dto.vendedor.cliente.ClienteResponseDTO;
import com.domrock.model.Cliente;
import com.domrock.repository.ClienteRepository;
import com.domrock.repository.VendaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private  JdbcTemplate jdbcTemplate;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/cliente")
    public List<ClienteResponseDTO> getAll(){
        List<ClienteResponseDTO> clientList = repository.findAll().stream().map(ClienteResponseDTO::new).toList();;
        return clientList;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/clientes-com-vendas")
      public List<Map<String, Object>> getClienteComVendas() {
        List<Map<String, Object>> clientes_usuario = new ArrayList<>();
        String sql = "select u.nome, u.id, u.acesso, c.cod_cliente, c.nome_cliente, c.nome_gerencia, " +
                "c.fk_usuario_id from usuario u, cliente c where c.fk_usuario_id = u.id " +
                "and u.acesso = 'vendedor'";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Map<String, Object> cliente_usuario = new HashMap<>();
            cliente_usuario.put("nome", row.get("nome"));
            cliente_usuario.put("id", row.get("id"));
            cliente_usuario.put("Acesso", row.get("Acesso"));
            cliente_usuario.put("Cod_Cliente", row.get("Cod_Cliente"));
            cliente_usuario.put("Nome_Cliente", row.get("Nome_Cliente"));
            cliente_usuario.put("Nome_Gerencia", row.get("Nome_Gerencia"));
            cliente_usuario.put("Fk_Usuario_Id", row.get("Fk_Usuario_Id"));
            clientes_usuario.add(cliente_usuario);
        }
        return clientes_usuario;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/cliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = repository.save(cliente);
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/clientes-by-vendedor/{id}")
    public List<ClienteResponseDTO> findByVendedor(@PathVariable Long id){
        List<Cliente> clientes = repository.findByVendedor(id);
        return clientes.stream().map(ClienteResponseDTO::new).toList();
    }


    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }
}
