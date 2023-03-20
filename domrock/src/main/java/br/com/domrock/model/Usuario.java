package br.com.domrock.model;

public class Usuario {
	
	private String email;
	private String senha;
	private String função;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getFunção() {
		return função;
	}
	
	public void setFunção(String função) {
		this.função = função;
	}

}
