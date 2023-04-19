package com.domrock.controller;

import com.domrock.dto.vendedor.UsuarioRequestDTO;
import com.domrock.dto.vendedor.UsuarioResponseDTO;
import com.domrock.model.Usuario;
import com.domrock.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UsuarioResponseDTO> getAll(){
        List<UsuarioResponseDTO> usuarioList = repository.findAll().stream().map(UsuarioResponseDTO::new).toList();;
        return usuarioList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveUsuario(@RequestBody UsuarioRequestDTO data){
        Usuario usuarioData = new Usuario(data);
        repository.save(usuarioData);
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
