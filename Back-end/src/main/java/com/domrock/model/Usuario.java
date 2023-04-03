package com.domrock.model;

import com.domrock.dto.usuario.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="usuario")
@Entity(name="usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoAcesso acesso;

    public Usuario(UsuarioRequestDTO data) {
        this.email = data.email();
        this.senha = data.senha();
        try {
            this.acesso = TipoAcesso.valueOf(data.acesso().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Tipo de acesso inválido");
        }
    }
    
    public enum TipoAcesso {
        VENDEDOR("Vendedor"),
        ADMINISTRADOR("Administrador");

        private final String valor;

        TipoAcesso(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }

}
