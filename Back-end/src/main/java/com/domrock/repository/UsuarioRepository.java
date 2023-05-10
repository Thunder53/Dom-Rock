package com.domrock.repository;

import com.domrock.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByNome(String nome);


    Optional<Usuario> findFirstByEmail(String email);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
