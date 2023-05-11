package com.domrock.controller;

import com.domrock.dto.vendedor.cliente.ClienteRequestDTO;
import com.domrock.dto.vendedor.cliente.ClienteResponseDTO;
import com.domrock.model.Cliente;
import com.domrock.repository.ClienteRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/cliente")
    public List<ClienteResponseDTO> getAll(){
        List<ClienteResponseDTO> clientList = repository.findAll().stream().map(ClienteResponseDTO::new).toList();;
        return clientList;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/clientes-com-vendas")
    public List<ClienteRepository.ClienteComVenda> buscarClientesComVendas() {
        List<Object[]> resultado = repository.buscarClientesComVendas();
        List<ClienteRepository.ClienteComVenda> clientes_usuario = new ArrayList<>();

        for (Object[] obj : resultado) {
            ClienteRepository.ClienteComVenda cliente_usuario = new ClienteRepository.ClienteComVenda();
            cliente_usuario.setNome((String) obj[0]);
            cliente_usuario.setId(Long.valueOf(Long.toString((Long) obj[1])));
            cliente_usuario.setAcesso((String) obj[2]);
            cliente_usuario.setCodCliente((Long.valueOf(Long.toString((Long) obj[3]))));
            cliente_usuario.setNomeCliente((String) obj[4]);
            cliente_usuario.setNomeGerencia((String) obj[5]);
            cliente_usuario.setFkUsuarioId((Long.valueOf(Long.toString((Long) obj[6]))));
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
