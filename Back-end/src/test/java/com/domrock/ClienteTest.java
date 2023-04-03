package com.domrock;

import com.domrock.dto.vendedor.cliente.ClienteRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.domrock.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClienteTest {

    @Test
    public void testCreateClienteFromDTO() {
        ClienteRequestDTO dto = new ClienteRequestDTO("Cliente 1", "Gerente 1", "Produto A", "Produto B", "Produto C");
        

        Cliente cliente = new Cliente(dto);

        assertEquals("Cliente 1", cliente.getNome_cliente());
        assertEquals("Gerente 1", cliente.getNome_gerencia());
        assertEquals("Produto A", cliente.getProduto_a());
        assertEquals("Produto B", cliente.getProduto_b());
        assertEquals("Produto C", cliente.getProduto_c());
    }
}
