package com.domrock;

import com.domrock.dto.usuario.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.domrock.model.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UsuarioTest {

    @Test
    public void testCreateUsuarioFromDTO() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("exemplo@gmail.com", "1234567", "administrador");

        Usuario usuario = new Usuario(dto);

        assertEquals("exemplo@gmail.com", usuario.getEmail());
        assertEquals("1234567", usuario.getSenha());
        assertEquals("administrador", usuario.getAcesso());
    }
}
