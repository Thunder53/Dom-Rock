package com.domrock;

import com.domrock.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UsuarioTest {

    @Test
    public void testCreateUsuarioFromDTO() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(null, "Ana", "exemplo@gmail.com", "teste123", "123.456.789-10",
                "12982578188", "administrador");

        Usuario usuario = new Usuario(dto);

        assertEquals("Ana", usuario.getNome());
        assertEquals("exemplo@gmail.com", usuario.getEmail());
        assertEquals("teste123", usuario.getSenha());
        assertEquals("12982578188", usuario.getContato());
        assertEquals("administrador", usuario.getAcesso());
    }
}
