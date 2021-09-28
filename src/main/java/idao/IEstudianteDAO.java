package idao;

import dominio.Estudiante;
import javafx.collections.ObservableList;

public interface IEstudianteDAO<Objeto> {
    boolean agregarEstudiante(Objeto Estudiante);
    boolean eliminarEstudiante(Objeto Estudiante);
    void llenarTablaEstudiante(ObservableList<Estudiante> listaEstudiantes);
}
