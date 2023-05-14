package com.domrock.repository;

import com.domrock.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByNome(String nome);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    List<Usuario> findAllByAcesso(String acesso);

//    @Query(value = "select u from usuario u where u.nome like %?1%")
//    Usuario findByNome(String nome);

    @Query(value = "select u from usuario u where u.nome like %?1%")
    List<Usuario> getByNome(String nome);

}
