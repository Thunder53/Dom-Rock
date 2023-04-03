package br.com.domrock.model.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.domrock.model.Administrador;


public class AdministradorDAO {
	
		private final String url = "jdbc:postgresql://localhost/domrock";
		private final String user = "root";
		private final String password = "Tobias*2004";
		private Connection con;
	    
	    public AdministradorDAO() {
	        con = ConexaoDAO.getConnection();
	    }
		
		private static final String createTableSQL = "CREATE TABLE administardor " +
		        "(ID INT PRIMARY KEY ," +
		        " NOME VARCHAR(50), " +
		        " EMAIL VARCHAR(50), " +
		        " CONTATO VARCHAR(50), " +
		        " PASSWORD VARCHAR(50)," +
		        " CPF VARCHAR(15))";
		
		 public void criaTabela() throws SQLException {
		        System.out.println(createTableSQL);
		        try (Connection connection = DriverManager.getConnection(url, user, password);
		            Statement statement = connection.createStatement();) {
		            statement.execute(createTableSQL);
		        } catch (SQLException e) {
		            printSQLException(e);
		        }
		    }

		    public static void printSQLException(SQLException ex) {
		        for (Throwable e: ex) {
		            if (e instanceof SQLException) {
		                e.printStackTrace(System.err);
		                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
		                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
		                System.err.println("Message: " + e.getMessage());
		                Throwable t = ex.getCause();
		                while (t != null) {
		                    System.out.println("Cause: " + t);
		                    t = t.getCause();
		                }
		            }
		        }
		    }
		    
		    public void insereAdministrador(Administrador a) {
		    	 String sql = "INSERT INTO administrador (nome, email, contato, senha, cpf) VALUES (?, ?, ?, ?, ?)";
		         try {
		             con = ConexaoDAO.getConnection();
		             PreparedStatement stmt = con.prepareStatement(sql);
		             stmt.setString(1, a.getNome());
		             stmt.setString(2, a.getEmail());
		             stmt.setString(3, a.getContato());
		             stmt.setString(4, a.getSenha());
		             stmt.setString(5, a.getCpf());
		             stmt.execute();
		             System.out.println("\nAdministrador adicionado\n");
		         } catch (SQLException ex) {
		             System.out.println("Erro: " + ex);
		         } finally {
		             ConexaoDAO.closeConnection(con);
		         }
		    }
		    
		   public Administrador buscaAdministrador(String nome) {
		    	 Administrador a = new Administrador();
		         String sql = "SELECT * FROM administrador WHERE nome = ?";
		         try {
		             con = ConexaoDAO.getConnection();
		             PreparedStatement stmt = con.prepareStatement(sql);
		             stmt.setString(1, a.getNome());
		             ResultSet rs = stmt.executeQuery();
		             while (rs.next()) {
		                 rs.getString("nome");
		                 rs.getString("contato");
		                 rs.getString("email");
		                 rs.getString("cpf");
		             }
		             return a;
		         } catch (SQLException ex) {
		             System.out.println("Erro: " + ex);
		             return null;
		         } finally {
		             ConexaoDAO.closeConnection(con);
		         }
		    }
		    
		    public static void main(String[] argv) throws SQLException {
		        AdministradorDAO criaTabelaExemplo = new AdministradorDAO();
		        criaTabelaExemplo.criaTabela();
		    }
}
