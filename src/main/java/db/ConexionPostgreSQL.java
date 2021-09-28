package db;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionPostgreSQL {
    private final PropiedadesBaseDeDatos propiedadesBasedeDatos = new PropiedadesBaseDeDatos();
    private Connection conexion = null;
    private String database;
    private String username;
    private String contraseña;

    public ConexionPostgreSQL() {
        try {
            cargarPropiedades();
        } catch (FileNotFoundException excepcionArchivoNoEncontrado) {
            Logger.getLogger(ConexionPostgreSQL.class.getName())
                    .log(Level.SEVERE, excepcionArchivoNoEncontrado.getMessage(), excepcionArchivoNoEncontrado);
        }
    }

    public Connection getConexion() throws SQLException {
        conectar();
        return conexion;
    }

    private void setDatabase(String database) {
        this.database = database;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    private void cargarPropiedades() throws FileNotFoundException {
        propiedadesBasedeDatos.cargarPropiedades();
        Properties propiedades = propiedadesBasedeDatos.getPropiedades();
        setDatabase(propiedades.getProperty("url"));
        setUsername(propiedades.getProperty("username"));
        setContraseña(propiedades.getProperty("password"));
    }

    private void conectar() throws SQLException {
        String driver = "jdbc:postgresql://";
        conexion = DriverManager.getConnection(String.format("%s%s",
                driver, database), username, contraseña);
    }
}
