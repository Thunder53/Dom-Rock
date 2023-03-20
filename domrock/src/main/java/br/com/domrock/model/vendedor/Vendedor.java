package br.com.domrock.model.vendedor;

import br.com.domrock.model.Usuario;

public class Vendedor extends Usuario {
	
	private String nome;
	private String contato;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}

}
