package com.domrock.controller;

import com.domrock.dto.vendedor.UsuarioRequestDTO;
import com.domrock.dto.vendedor.UsuarioResponseDTO;
import com.domrock.model.Usuario;
import com.domrock.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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

    /*
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/usuario-por-nome")
    public ResponseEntity<Long> buscarIdPorNome(@RequestParam String nome) {
        Usuario usuario = repository.findByNome(nome);
        if (usuario != null) {
            return ResponseEntity.ok(usuario.getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping(value = "getByNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> getByNome(@RequestParam(name = "nome") String nome) {

        List<Usuario> usuario = repository.getByNome(nome);

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }

//    @PostMapping("/usuarios")
//    public ResponseEntity<?> criarUsuario(@Valid @RequestBody UsuarioRequestDTO data, BindingResult result) {
//        ErrorResponse errorResponse = usuario.validarCampos(data);
//        if (errorResponse != null) {
//            return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
//        }
//        Usuario usuarioData = new Usuario(data);
//        repository.save(usuarioData);
//        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioData));
//    }

    @CrossOrigin(origins = "http://localhost:5500")
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void preflightResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
    }
}
