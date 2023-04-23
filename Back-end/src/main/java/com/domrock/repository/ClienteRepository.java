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
        private String id;
        private String acesso;
        private String codCliente;
        private String nomeCliente;
        private String nomeGerencia;
        private String fkUsuarioId;

        public ClienteComVenda() {}

        public ClienteComVenda(String nome, String id, String acesso, String codCliente, String nomeCliente, String nomeGerencia, String fkUsuarioId) {
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAcesso() {
            return acesso;
        }

        public void setAcesso(String acesso) {
            this.acesso = acesso;
        }

        public String getCodCliente() {
            return codCliente;
        }

        public void setCodCliente(String codCliente) {
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

        public String getFkUsuarioId() {
            return fkUsuarioId;
        }

        public void setFkUsuarioId(String fkUsuarioId) {
            this.fkUsuarioId = fkUsuarioId;
        }
    }


}
