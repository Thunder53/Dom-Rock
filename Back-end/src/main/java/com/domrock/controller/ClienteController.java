package com.domrock.controller;

import com.domrock.dto.vendedor.cliente.ClienteRequestDTO;
import com.domrock.dto.vendedor.cliente.ClienteResponseDTO;
import com.domrock.model.Cliente;
import com.domrock.repository.ClienteRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
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
            cliente_usuario.setId((String) obj[1]);
            cliente_usuario.setAcesso((String) obj[2]);
            cliente_usuario.setCodCliente((String) obj[3]);
            cliente_usuario.setNomeCliente((String) obj[4]);
            cliente_usuario.setNomeGerencia((String) obj[5]);
            cliente_usuario.setFkUsuarioId((String) obj[6]);
            clientes_usuario.add(cliente_usuario);
        }

        return clientes_usuario;
    }

    @RequestMapping("/cliente")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveCliente(@RequestBody ClienteRequestDTO data){
        Cliente clienteData = new Cliente(data);
        repository.save(clienteData);
        return;
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }
}
