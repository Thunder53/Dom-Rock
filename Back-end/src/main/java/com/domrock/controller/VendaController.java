package com.domrock.controller;

import com.domrock.dto.venda.VendaResponseDTO;
import com.domrock.repository.VendaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<VendaResponseDTO> getAll(){
        List<VendaResponseDTO> vendaList = repository.findAll().stream().map(VendaResponseDTO::new).toList();;
        return vendaList;
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }

}
