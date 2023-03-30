package br.com.domrock.model;

public class Cliente {
	
	private int cod_cliente;
	private String nome_cliente;
	private String nome_gerencia;
	private String produto_a;
	private String produto_b;
	private String produto_c;
	
	public Cliente() {}
	
	public Cliente(int cod_cliente, String nome_cliente, String nome_gerencia, String produto_a, String produto_b, String produto_c) {
		this.cod_cliente = cod_cliente;
		this.nome_cliente = nome_cliente;
		this.nome_gerencia = nome_gerencia;
		this.produto_a = produto_a;
		this.produto_b = produto_b;
		this.produto_c = produto_c;
	}
	
	
	public int getCod_cliente() {
		return cod_cliente;
	}
	
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	
	public String getNome_cliente() {
		return nome_cliente;
	}
	
	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	
	public String getNome_gerencia() {
		return nome_gerencia;
	}
	
	public void setNome_gerencia(String nome_gerencia) {
		this.nome_gerencia = nome_gerencia;
	}
	
	public String getProduto_a() {
		return produto_a;
	}
	
	public void setProduto_a(String produto_a) {
		this.produto_a = produto_a;
	}
	
	public String getProduto_b() {
		return produto_b;
	}
	
	public void setProduto_b(String produto_b) {
		this.produto_b = produto_b;
	}
	
	public String getProduto_c() {
		return produto_c;
	}
	
	public void setProduto_c(String produto_c) {
		this.produto_c = produto_c;
	}
	
	

}
