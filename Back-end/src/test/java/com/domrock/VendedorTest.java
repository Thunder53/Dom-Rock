package com.domrock;

import com.domrock.dto.vendedor.UsuarioRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.domrock.model.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VendedorTest {

    @Test
    public void testCreateVendedorFromDTO() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Vendedor1", "123.456.789-10", "12123456789");
        

        Usuario vendedor = new Usuario(dto);

        assertEquals("Vendedor1", vendedor.getNome());
        assertEquals("123.456.789-10", vendedor.getCpf());
        assertEquals("Produto B", vendedor.getContato());
    }
}
