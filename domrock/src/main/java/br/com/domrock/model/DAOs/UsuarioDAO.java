package br.com.domrock.model.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.domrock.model.Usuario;

public class UsuarioDAO {
	private final String url = "jdbc:postgresql://localhost/domrock";
    private final String user = "root";
    private final String password = "";
    private Connection con;
    
    public UsuarioDAO() {
        con = ConexaoDAO.getConnection();
    }

    private static final String createTableSQL = "CREATE TABLE usuario " +
        "(ID SERIAL PRIMARY KEY NOT NULL ," +
        " EMAIL VARCHAR(50)NOT NULL, " +
        " SENHA VARCHAR(50)NOT NULL)";

    public void criaTabela() throws SQLException {
        System.out.println(createTableSQL);
        try (Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
        	System.out.println("Erro: " + e);
        }
    }
    
    public void insereUsuario(Usuario u) {
    	 String sql = "INSERT INTO usuario (email, senha) VALUES (?, ?)";
         try {
             con = ConexaoDAO.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1, u.getEmail());
             stmt.setString(2, u.getSenha());
             stmt.execute();
             System.out.println("\nLogin realizado\n");
         } catch (SQLException ex) {
             System.out.println("Erro: " + ex);
         } finally {
             ConexaoDAO.closeConnection(con);
         }
    }
    
   public Usuario buscaUsuario(String User) {
    	 Usuario u = new Usuario();
         String sql = "SELECT * FROM usuario WHERE email = ?";
         try {
             con = ConexaoDAO.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1, u.getEmail());
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 rs.getString("email");
                 rs.getString("funcao");
                 }
             return u;
         } catch (SQLException ex) {
             System.out.println("Erro: " + ex);
             return null;
         } finally {
             ConexaoDAO.closeConnection(con);
         }
    }
    
    public static void main(String[] argv) throws SQLException {
        UsuarioDAO criaTabelaExemplo = new UsuarioDAO();
        criaTabelaExemplo.criaTabela();
    }

}
