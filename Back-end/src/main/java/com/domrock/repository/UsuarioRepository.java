package com.domrock.repository;

import com.domrock.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmailAndSenha(String email, String senha);
}
