package controlador;

import dao.EstudianteDAO;
import dominio.Estudiante;
import idao.IEstudianteDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static dialogo.Dialogo.*;

public class ControladorVistaEstudiante implements Initializable {

    @FXML private TextField segundoNombreTextField;
    @FXML private TextField primerNombreTextField;
    @FXML private TextField maternoTextField;
    @FXML private TextField paternoTextField;
    @FXML private TableView<Estudiante> estudianteTableView;
    @FXML private TableColumn<Estudiante, Integer> columnaIdEstudiante;
    @FXML private TableColumn<Estudiante, String> columnaPaterno;
    @FXML private TableColumn<Estudiante, String> columnaMaterno;
    @FXML private TableColumn<Estudiante, String> columnaPrimerNombre;
    @FXML private TableColumn<Estudiante, String> columnaSegundoNombre;
    @FXML private TableColumn<Estudiante, Integer> columnaEstado;
    @FXML private final EstudianteDAO estudianteDAO;
    @FXML private Button botonLimpiar;
    @FXML private ObservableList<Estudiante> listaEstudiantes;

    public ControladorVistaEstudiante() {
        this.estudianteDAO = new EstudianteDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        botonLimpiar.setDisable(true);
        listaEstudiantes = FXCollections.observableArrayList();
        estudianteDAO.llenarTablaEstudiante(listaEstudiantes);
        estudianteTableView.setItems(listaEstudiantes);
        enlazarColumnasConAtributos();
        validarTextFields();
        validarLargoTextFields(49);

    }

    public void clicRegistrar(ActionEvent actionEvent) {
        if (!estanCamposVacios()) {
            String apellidoPaterno = this.paternoTextField.getText().trim();
            String apellidoMaterno = this.maternoTextField.getText().trim();
            String primerNombre = this.primerNombreTextField.getText().trim();
            String segundoNombre = this.segundoNombreTextField.getText().trim();
            guardarEstudiante(apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre);
        } else {
            mostrarDialogoCamposVacios();
        }
    }

    private void guardarEstudiante(String paterno, String materno, String primerNombre, String segundoNombre) {
        Estudiante estudiante = new Estudiante();
        estudiante.setApellidoPaterno(paterno);
        estudiante.setApellidoMaterno(materno);
        estudiante.setPrimerNombre(primerNombre);
        estudiante.setSegundoNombre(segundoNombre);
        estudiante.setEstado(true);
        IEstudianteDAO iEstudianteDAO = new EstudianteDAO();
        if (mostrarDialogoRegistroConfirmacion()) {
            if (iEstudianteDAO.agregarEstudiante(estudiante)) {
                mostrarDialogoRegistroExitoso();
                refrescarTableView();
                //limpiarTextField();
                botonLimpiar.setDisable(false);
            } else {
                mostrarDialogoAlgoSalioMal();
                botonLimpiar.setDisable(false);
            }
        }
    }

    public void clicEliminar(ActionEvent actionEvent) {
        if (obtenerValorDeCelda() != 0) {
            int idEstudiante = obtenerValorDeCelda();
            if (mostrarDialogoEliminacionConfirmacion()){
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(idEstudiante);
                IEstudianteDAO iEstudianteDAO = new EstudianteDAO();
                if (iEstudianteDAO.eliminarEstudiante(estudiante)) {
                    mostrarDialogoEliminacionExitosa();
                    refrescarTableView();
                } else {
                    mostrarDialogoAlgoSalioMal();
                }
            }
        } else {
            mostrarDialogoSeleccionarRegistro();
        }
    }

    public void clicLimpiar(ActionEvent actionEvent) {
        limpiarTextField();
        botonLimpiar.setDisable(true);
    }

    public void limpiarTextField() {
        paternoTextField.setText("");
        maternoTextField.setText("");
        primerNombreTextField.setText("");
        segundoNombreTextField.setText("");
    }

    public final Integer obtenerValorDeCelda() {
        int idEstudiante = 0;
        if (estudianteTableView.getSelectionModel().getSelectedItem() != null) {
            TablePosition posicion = estudianteTableView.getSelectionModel()
                    .getSelectedCells().get(0);
            int fila = posicion.getRow();
            Estudiante elemento = estudianteTableView.getItems().get(fila);
            TableColumn columna = columnaIdEstudiante;
            idEstudiante = (Integer) columna.getCellObservableValue(elemento).getValue();
        }
        return idEstudiante;
    }

    public final void validarTextFields() {
        paternoTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable
                    , String valorViejo, String valorNuevo) {
                if (!valorNuevo.matches("\\sa-zA-Z*")) {
                    paternoTextField.setText(valorNuevo.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
        maternoTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable
                    , String valorViejo, String valorNuevo) {
                if (!valorNuevo.matches("\\sa-zA-Z*")) {
                    maternoTextField.setText(valorNuevo.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
        primerNombreTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable
                    , String valorViejo, String valorNuevo) {
                if (!valorNuevo.matches("\\sa-zA-Z*")) {
                    primerNombreTextField.setText(valorNuevo.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
        segundoNombreTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable
                    , String valorViejo, String valorNuevo) {
                if (!valorNuevo.matches("\\sa-zA-Z*")) {
                    segundoNombreTextField.setText(valorNuevo.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
    }

    public void validarLargoTextFields(final int tamañoMaximo) {
        paternoTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (paternoTextField.getText().length() > tamañoMaximo) {
                        paternoTextField.setText(paternoTextField.getText()
                                .substring(0, tamañoMaximo));
                        mostrarDialogoDemasiadosCaracteres((short) 49);
                    }
                }
            }
        });
        maternoTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (maternoTextField.getText().length() > tamañoMaximo) {
                        maternoTextField.setText(maternoTextField.getText()
                                .substring(0, tamañoMaximo));
                        mostrarDialogoDemasiadosCaracteres((short) 49);
                    }
                }
            }
        });
        primerNombreTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (primerNombreTextField.getText().length() > tamañoMaximo) {
                        primerNombreTextField.setText(primerNombreTextField.getText()
                                .substring(0, tamañoMaximo));
                        mostrarDialogoDemasiadosCaracteres((short) 49);
                    }
                }
            }
        });
        segundoNombreTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (segundoNombreTextField.getText().length() > tamañoMaximo) {
                        segundoNombreTextField.setText(segundoNombreTextField.getText()
                                .substring(0, tamañoMaximo));
                        mostrarDialogoDemasiadosCaracteres((short) 49);
                    }
                }
            }
        });
    }

    private boolean estanCamposVacios() {
        return (paternoTextField.getText().trim().isEmpty() ||
                maternoTextField.getText().trim().isEmpty() ||
                primerNombreTextField.getText().trim().isEmpty() ||
                segundoNombreTextField.getText().trim().isEmpty());
    }

    private void enlazarColumnasConAtributos() {
        columnaIdEstudiante.setCellValueFactory(new PropertyValueFactory<Estudiante, Integer>("idEstudiante"));
        columnaPaterno.setCellValueFactory(new PropertyValueFactory<Estudiante, String> ("apellidoPaterno"));
        columnaMaterno.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("apellidoMaterno"));
        columnaPrimerNombre.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("primerNombre"));
        columnaSegundoNombre.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("segundoNombre"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<Estudiante, Integer> ("estado"));
    }

    private void refrescarTableView() {
        listaEstudiantes.clear();
        listaEstudiantes = FXCollections.observableArrayList();
        estudianteDAO.llenarTablaEstudiante(listaEstudiantes);
        estudianteTableView.setItems(listaEstudiantes);
        enlazarColumnasConAtributos();
    }
}
