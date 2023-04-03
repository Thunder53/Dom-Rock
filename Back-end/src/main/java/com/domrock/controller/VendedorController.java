package com.domrock.controller;

import com.domrock.dto.vendedor.VendedorRequestDTO;
import com.domrock.dto.vendedor.VendedorResponseDTO;
import com.domrock.model.Vendedor;
import com.domrock.repository.VendedorRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<VendedorResponseDTO> getAll(){
        List<VendedorResponseDTO> vendedorList = repository.findAll().stream().map(VendedorResponseDTO::new).toList();;
        return vendedorList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveVendedor(@RequestBody VendedorRequestDTO data){
        Vendedor vendedorData = new Vendedor(data);
        repository.save(vendedorData);
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
