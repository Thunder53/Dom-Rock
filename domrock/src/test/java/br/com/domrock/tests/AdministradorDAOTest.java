import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import br.com.domrock.model.DAOs.AdministradorDAO;

public class AdministradorDAOTest {

    private AdministradorDAO administradorDAO;

    @Before
    public void setUp() {
        administradorDAO = new AdministradorDAO();
    }

    @Test
    public void testCriaTabela() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/domrock", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM pg_tables WHERE tablename = 'administrador'");

        assertTrue(resultSet.next());
        assertEquals("administrador", resultSet.getString("tablename"));
    }
}
