package br.com.domrock.model;

public class Administrador extends Usuario{
		private String nome;
		private String contato;
		private String cpf;
		
		
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
		
		public String getCpf() {
			return cpf;
		}
		
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
	
}
