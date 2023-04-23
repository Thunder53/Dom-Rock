package com.domrock.repository;

import com.domrock.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select u.nome, u.id, u.acesso, c.cod_cliente, c.nome_cliente, c.nome_gerencia, c.fk_usuario_id from usuario u, cliente c where c.fk_usuario_id = u.id and u.acesso = 'vendedor'")
    public List<Object[]> buscarClientesComVendas();



    public class ClienteComVenda {
        private String nome;
        private Long id;
        private String acesso;
        private Long codCliente;
        private String nomeCliente;
        private String nomeGerencia;
        private Long fkUsuarioId;

        public ClienteComVenda() {}

        public ClienteComVenda(String nome, Long id, String acesso, Long codCliente, String nomeCliente, String nomeGerencia, Long fkUsuarioId) {
            this.nome = nome;
            this.id = id;
            this.acesso = acesso;
            this.codCliente = codCliente;
            this.nomeCliente = nomeCliente;
            this.nomeGerencia = nomeGerencia;
            this.fkUsuarioId = fkUsuarioId;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAcesso() {
            return acesso;
        }

        public void setAcesso(String acesso) {
            this.acesso = acesso;
        }

        public Long getCodCliente() {
            return codCliente;
        }

        public void setCodCliente(Long codCliente) {
            this.codCliente = codCliente;
        }

        public String getNomeCliente() {
            return nomeCliente;
        }

        public void setNomeCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
        }

        public String getNomeGerencia() {
            return nomeGerencia;
        }

        public void setNomeGerencia(String nomeGerencia) {
            this.nomeGerencia = nomeGerencia;
        }

        public Long getFkUsuarioId() {
            return fkUsuarioId;
        }

        public void setFkUsuarioId(Long fkUsuarioId) {
            this.fkUsuarioId = fkUsuarioId;
        }
    }


}
