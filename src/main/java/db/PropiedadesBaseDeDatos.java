package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropiedadesBaseDeDatos {
    private final Properties propiedades = new Properties();

    public PropiedadesBaseDeDatos() {
        propiedades.setProperty("url", "url:port/database");
        propiedades.setProperty("username", "username");
        propiedades.setProperty("password", "password");
    }

    public Properties getPropiedades() {
        return propiedades;
    }

    public void setConfiguracionBaseDatos(String url, String username, String password) {
        propiedades.setProperty("url", url);
        propiedades.setProperty("username", username);
        propiedades.setProperty("password", password);
    }

    public void guardarPropiedades() throws FileNotFoundException {
        OutputStream propertiesFile = new FileOutputStream("db.properties");
        try {
            propiedades.store(propertiesFile, null);
        } catch (IOException exception) {
            Logger.getLogger(PropiedadesBaseDeDatos.class.getName())
                    .log(Level.SEVERE, exception.getMessage(), exception);
        }
    }

    public void cargarPropiedades() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("db.properties");
        try {
            propiedades.load(inputStream);
            setConfiguracionBaseDatos(propiedades.getProperty("url"),
                    propiedades.getProperty("username"), propiedades.getProperty("password"));
        } catch (IOException exception) {
            Logger.getLogger(PropiedadesBaseDeDatos.class.getName())
                    .log(Level.SEVERE, exception.getMessage(), exception);
            guardarPropiedades();
        }
    }
}
