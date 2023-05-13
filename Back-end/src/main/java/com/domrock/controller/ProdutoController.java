package com.domrock.controller;

import com.domrock.dto.produto.ProdutoRequestDTO;
import com.domrock.dto.produto.ProdutoResponseDTO;
import com.domrock.dto.vendedor.UsuarioRequestDTO;
import com.domrock.dto.vendedor.UsuarioResponseDTO;
import com.domrock.model.Produto;
import com.domrock.model.Usuario;
import com.domrock.repository.ProdutoRepository;
import com.domrock.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping
//    public List<ProdutoResponseDTO> getProdutos(){
//        List<ProdutoResponseDTO> produtoList;
//        produtoList = repository.findAllByAcesso("produto")
//                .stream()
//                .map(ProdutoResponseDTO::new)
//                .toList();
//        return produtoList;
//    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduto(@RequestBody ProdutoRequestDTO data){
        Produto produtoData = new Produto(data);
        repository.save(produtoData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produto-por-nome")
    public ResponseEntity<Long> buscarIdPorNome(@RequestParam String nome) {
        Usuario usuario = repository.findByNome(nome);
        if (usuario != null) {
            return ResponseEntity.ok(usuario.getId());
        } else {
            return ResponseEntity.notFound().build();
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
