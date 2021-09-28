package main;

import controlador.ControladorVistaEstudiante;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage ventanaPrincipal) throws Exception {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/Vista_Estudiante.fxml"));
        Parent vista;
        try {
            vista = cargador.load();
        } catch (IOException ioExcepcion) {
            Logger.getLogger(ControladorVistaEstudiante.class.getName())
                    .log(Level.SEVERE, ioExcepcion.getMessage(), ioExcepcion);
            return;
        }
        ControladorVistaEstudiante controladorVistaEstudiante = cargador.getController();
        ventanaPrincipal.setScene(new Scene(vista, 800, 400));
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setTitle("Estudiante");
        ventanaPrincipal.show();
    }
}
