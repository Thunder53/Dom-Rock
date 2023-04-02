package com.domrock.controller;

import com.domrock.dto.vendedor.VendedorRequestDTO;
import com.domrock.dto.vendedor.cliente.ClienteRequestDTO;
import com.domrock.dto.vendedor.cliente.ClienteResponseDTO;
import com.domrock.model.Cliente;
import com.domrock.repository.ClienteRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ClienteResponseDTO> getAll(){
        List<ClienteResponseDTO> vendedorList = repository.findAll().stream().map(ClienteResponseDTO::new).toList();;
        return vendedorList;
    }

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
