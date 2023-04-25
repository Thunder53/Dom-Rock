package com.domrock;

import com.domrock.dto.vendedor.UsuarioRequestDTO;
import com.domrock.model.Usuario;
import org.junit.jupiter.api.Assertions;

class UsuarioTest {

    public void testCreateUsuarioFromDTO() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(null, "Ana Maria", "exemplo@gmail.com", "Teste123*", "662.270.970-04", "12982578188", "administrador");

        Usuario usuario = new Usuario(dto);

        Assertions.assertEquals("Ana Maria", usuario.getNome());
        Assertions.assertEquals("exemplo@gmail.com", usuario.getEmail());
        Assertions.assertEquals("Teste123*", usuario.getSenha());
        Assertions.assertEquals("662.270.970-04", usuario.getCpf());
        Assertions.assertEquals("12982578188", usuario.getContato());
        Assertions.assertEquals("administrador", usuario.getAcesso());
    }
}