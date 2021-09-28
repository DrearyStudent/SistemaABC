import db.ConexionPostgreSQL;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class TestConexionPostgreSQL {
    public static void main(String args[] ) throws SQLException {
        ConexionPostgreSQL conexionPostgreSQL = new ConexionPostgreSQL();
        if (conexionPostgreSQL.getConexion() != null) {
            System.out.println("Funciona");
        }
    }
}
