package com.domrock.model;

import com.domrock.dto.vendedor.UsuarioRequestDTO;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String contato;
    private String acesso;


    public Usuario(UsuarioRequestDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.senha = data.senha();
        this.cpf = data.cpf();
        this.contato = data.contato();
        this.acesso = data.acesso();
    }
}

//    public boolean validarNome(){
//        Matcher matcher = Regex.NOME_REGEX.matcher(this.nome);
//        return matcher.matches();
//    }
//
//    public boolean validarEmail(){
//        Matcher matcher = Regex.EMAIL_REGEX.matcher(this.email);
//        return matcher.matches();
//    }
//
//    public boolean validarSenha(){
//        Matcher matcher = Regex.SENHA_REGEX.matcher(this.senha);
//        return matcher.matches();
//    }
//
//    public boolean validarCpf(){
//        Matcher matcher = Regex.CPF_REGEX.matcher(this.cpf);
//        return matcher.matches();
//    }
//
//    public boolean validarContato(){
//        Matcher matcher = Regex.CONTATO_REGE.matcher(this.contato);
//        return matcher.matches();
//    }
//}