package br.com.domrock.model.vendedor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class VendedorDAO {
	
	 	private final String url = "jdbc:postgresql://localhost/domrock";
	    private final String user = "root";
	    private final String password = "";

	    private static final String createTableSQL = "CREATE TABLE vendedor " +
	        "(ID INT PRIMARY KEY ," +
	        " NOME VARCHAR(50), " +
	        " EMAIL VARCHAR(50), " +
	        " CONTATO VARCHAR(50), " +
	        " PASSWORD VARCHAR(50))";

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
	    
	    public static void main(String[] argv) throws SQLException {
	        VendedorDAO criaTabelaExemplo = new VendedorDAO();
	        criaTabelaExemplo.criaTabela();
	    }

}
