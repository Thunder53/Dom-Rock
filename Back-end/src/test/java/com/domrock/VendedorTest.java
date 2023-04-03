package com.domrock;

import com.domrock.dto.vendedor.VendedorRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.domrock.model.Vendedor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VendedorTest {

    @Test
    public void testCreateVendedorFromDTO() {
        VendedorRequestDTO dto = new VendedorRequestDTO(null, "Vendedor 1", null, "123.456.789-10", "12123456789");
        

        Vendedor vendedor = new Vendedor (dto);

        assertEquals("Vendedor1", vendedor.getNome());
        assertEquals("123.456.789-10", vendedor.getCpf());
        assertEquals("Produto B", vendedor.getContato());
    }
}
