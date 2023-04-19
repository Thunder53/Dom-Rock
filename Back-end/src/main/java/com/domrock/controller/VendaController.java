package com.domrock.controller;

import com.domrock.dto.venda.VendaResponseDTO;
import com.domrock.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<VendaResponseDTO> getAll(){
        List<VendaResponseDTO> vendaList = repository.findAll().stream().map(VendaResponseDTO::new).toList();;
        return vendaList;
    }

}
