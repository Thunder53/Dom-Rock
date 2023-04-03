package br.com.domrock.model.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.domrock.model.Cliente;


public class ClienteDAO {
	
	private final String url = "jdbc:postgresql://localhost/domrock";
    private final String user = "root";
    private final String password = "";
    private Connection con;
    
    public ClienteDAO() {
        con = ConexaoDAO.getConnection();
    }

    private static final String createTableSQL = "CREATE TABLE cliente " +
        "(cod_cliente int PRIMARY KEY ," +
        " nome_cliente VARCHAR(50), " +
        " nome_gerencia VARCHAR(50), " +
        " produto_a VARCHAR(50), " +
        " produto_b VARCHAR(50)," +
        " produto_c VARCHAR(50)";

    public void criaTabela() throws SQLException {
        System.out.println(createTableSQL);
        try (Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
        	System.out.println("Erro: " + e);
        }
    }
    
    public void insereCliente(Cliente c) {
    	 String sql = "INSERT INTO cliente (cod_cliente, nome_cliente, produto_a, produto_b, produto_c) VALUES (?, ?, ?, ?, ?)";
         try {
             con = ConexaoDAO.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setLong(1, c.getCod_cliente());
             stmt.setString(2, c.getNome_cliente());
             stmt.setString(3, c.getProduto_a());
             stmt.setString(4, c.getProduto_b());
             stmt.setString(5, c.getProduto_c());
             stmt.execute();
             System.out.println("\nCliente adicionado\n");
         } catch (SQLException ex) {
             System.out.println("Erro: " + ex);
         } finally {
             ConexaoDAO.closeConnection(con);
         }
    }
    
   public Cliente buscaCliente(String nome) {
    	 Cliente c = new Cliente();
         String sql = "SELECT * FROM cliente WHERE nome_cliente = ?";
         try {
             con = ConexaoDAO.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1, c.getNome_cliente());
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 rs.getString("cod_cliente");
                 rs.getString("nome_cliente");
                 rs.getString("nome_gerencia");
                 rs.getString("produto_a");
                 rs.getString("produto_b");
                 rs.getString("produto_c");
             }
             return c;
         } catch (SQLException ex) {
             System.out.println("Erro: " + ex);
             return null;
         } finally {
             ConexaoDAO.closeConnection(con);
         }
    }
    
    public static void main(String[] argv) throws SQLException {
        ClienteDAO criaTabelaExemplo = new ClienteDAO();
        criaTabelaExemplo.criaTabela();
    }

}
