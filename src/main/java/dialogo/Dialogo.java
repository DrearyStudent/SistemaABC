package dialogo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Dialogo {

    public static void mostrarDialogoDemasiadosCaracteres(short characterLength) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText("Límite de caracteres excedido");
        alerta.setContentText(String.format("El límite de es de %d. caracteres", characterLength));
        alerta.showAndWait();
    }

    public static void mostrarDialogoCamposVacios() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText("Campos vacíos");
        alerta.setContentText("Debe llenar todos los campos");
        alerta.showAndWait();
    }

    public static boolean mostrarDialogoRegistroConfirmacion() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro que desea realizar el registro con esos datos?");
        Optional<ButtonType> result = alerta.showAndWait();
        return result.filter(buttonType -> (buttonType == ButtonType.OK)).isPresent();
    }

    public static void mostrarDialogoRegistroExitoso() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText("Registro exitoso");
        alerta.setContentText("¡Se ha realizado el registro exitosamente!");
        alerta.showAndWait();
    }

    public static void mostrarDialogoAlgoSalioMal() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Algo ha salido mal");
        alerta.setContentText("Lamentamos las molestias que esto pueda ocasionarle.");
        alerta.showAndWait();
    }

    public static boolean mostrarDialogoEliminacionConfirmacion() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Esta seguro que desea eliminar el registro seleccionado?");
        Optional<ButtonType> result = alerta.showAndWait();
        return (result.filter(buttonType -> (buttonType == ButtonType.OK)).isPresent());
    }

    public static void mostrarDialogoEliminacionExitosa() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText("Eliminación exitosa");
        alerta.setContentText("El registro seleccionado ahora esta inactivo");
        alerta.showAndWait();
    }

    public static void mostrarDialogoSeleccionarRegistro() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText("No se seleccionó ningún registro");
        alerta.setContentText("Debe seleccionar un registro para eliminar");
        alerta.showAndWait();
    }

}
