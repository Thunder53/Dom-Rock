package br.com.domrock.model.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.domrock.model.Vendedor;

public class VendedorDAO {
	
	 	private final String url = "jdbc:postgresql://localhost/domrock";
	    private final String user = "root";
	    private final String password = "";
	    private Connection con;
	    
	    public VendedorDAO() {
	        con = ConexaoDAO.getConnection();
	    }

	    private static final String createTableSQL = "CREATE TABLE vendedor " +
	        "(ID INT PRIMARY KEY ," +
	        " NOME VARCHAR(50), " +
	        " EMAIL VARCHAR(50), " +
	        " CONTATO VARCHAR(50), " +
	        " SENHA VARCHAR(50))";

	    public void criaTabela() throws SQLException {
	        System.out.println(createTableSQL);
	        try (Connection connection = DriverManager.getConnection(url, user, password);
	            Statement statement = connection.createStatement();) {
	            statement.execute(createTableSQL);
	        } catch (SQLException e) {
	        	System.out.println("Erro: " + e);
	        }
	    }
	    
	    public void insereVendedor(Vendedor v) {
	    	 String sql = "INSERT INTO vendedor (nome, email, contato, senha) VALUES (?, ?, ?, ?)";
	         try {
	             con = ConexaoDAO.getConnection();
	             PreparedStatement stmt = con.prepareStatement(sql);
	             stmt.setString(1, v.getNome());
	             stmt.setString(2, v.getEmail());
	             stmt.setString(3, v.getContato());
	             stmt.setString(4, v.getSenha());
	             stmt.execute();
	             System.out.println("\nVendedor adicionado\n");
	         } catch (SQLException ex) {
	             System.out.println("Erro: " + ex);
	         } finally {
	             ConexaoDAO.closeConnection(con);
	         }
	    }
	    
	   public Vendedor buscaVendedor(String nome) {
	    	 Vendedor v = new Vendedor();
	         String sql = "SELECT * FROM vendedor WHERE nome = ?";
	         try {
	             con = ConexaoDAO.getConnection();
	             PreparedStatement stmt = con.prepareStatement(sql);
	             stmt.setString(1, v.getNome());
	             ResultSet rs = stmt.executeQuery();
	             while (rs.next()) {
	                 v.setNome(rs.getString("nome"));
	                 v.setContato(rs.getString("contato"));
	                 v.setEmail(rs.getString("email"));
	             }
	             return v;
	         } catch (SQLException ex) {
	             System.out.println("Erro: " + ex);
	             return null;
	         } finally {
	             ConexaoDAO.closeConnection(con);
	         }
	    }
	    
	    public static void main(String[] argv) throws SQLException {
	        VendedorDAO criaTabelaExemplo = new VendedorDAO();
	        criaTabelaExemplo.criaTabela();
	    }

}
