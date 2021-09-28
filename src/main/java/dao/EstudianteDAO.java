package dao;

import db.ConexionPostgreSQL;
import dominio.Estudiante;
import idao.IEstudianteDAO;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstudianteDAO implements IEstudianteDAO<Estudiante> {
    private final ConexionPostgreSQL conexionPostgreSQL = new ConexionPostgreSQL();
    private boolean resultado;

    @Override
    public boolean agregarEstudiante(Estudiante estudiante) {
        resultado = false;
        String consulta = "INSERT INTO estudiante(apellidopaterno, apellidomaterno, primernombre, segundonombre, estado) VALUES(?,?,?,?,?)";
        try (Connection conexion = conexionPostgreSQL.getConexion();
             PreparedStatement declaracionPreparada = conexion.prepareStatement(consulta)) {
            declaracionPreparada.setString(1, estudiante.getApellidoPaterno());
            declaracionPreparada.setString(2, estudiante.getApellidoMaterno());
            declaracionPreparada.setString(3, estudiante.getPrimerNombre());
            declaracionPreparada.setString(4, estudiante.getSegundoNombre());
            declaracionPreparada.setBoolean(5, estudiante.isEstado());
            int filasAfectadas = declaracionPreparada.executeUpdate();
            resultado = (filasAfectadas > 0);
        } catch (SQLException sqlExcepcion) {
            Logger.getLogger(EstudianteDAO.class.getName())
                    .log(Level.SEVERE, sqlExcepcion.getMessage(), sqlExcepcion);
        }
        return resultado;
    }

    @Override
    public boolean eliminarEstudiante(Estudiante estudiante) {
        resultado = false;
        String consulta = "UPDATE estudiante SET estado = false WHERE idEstudiante = ?";
        try (Connection conexion = conexionPostgreSQL.getConexion();
             PreparedStatement declaracionPreparada = conexion.prepareStatement(consulta)) {
            declaracionPreparada.setInt(1, estudiante.getIdEstudiante());
            int filasAfectadas = declaracionPreparada.executeUpdate();
            resultado = (filasAfectadas > 0);
        } catch (SQLException sqlExcepcion) {
            Logger.getLogger(EstudianteDAO.class.getName())
                    .log(Level.SEVERE, sqlExcepcion.getMessage(), sqlExcepcion);
        }
        return resultado;
    }

    @Override
    public void llenarTablaEstudiante(ObservableList<Estudiante> listaEstudiantes) {
        String consulta = "SELECT * FROM estudiante";
        try (Connection conexion = conexionPostgreSQL.getConexion();
             Statement instruccion = conexion.createStatement();
             ResultSet resultadoConsulta = instruccion.executeQuery(consulta)) {
            while(resultadoConsulta.next()){
                Estudiante estudiante = new Estudiante();
                llenarEstudiante(estudiante, resultadoConsulta);
                listaEstudiantes.add(estudiante);
            }
        } catch (SQLException sqlExcepcion) {
            Logger.getLogger(EstudianteDAO.class.getName())
                    .log(Level.SEVERE, sqlExcepcion.getMessage(), sqlExcepcion);
        }
    }

    private static void llenarEstudiante(Estudiante estudiante, ResultSet conjuntoResultados) throws SQLException {
        estudiante.setIdEstudiante(conjuntoResultados.getInt("idestudiante"));
        estudiante.setApellidoPaterno(conjuntoResultados.getString("apellidopaterno"));
        estudiante.setApellidoMaterno(conjuntoResultados.getString("apellidomaterno"));
        estudiante.setPrimerNombre(conjuntoResultados.getString("primernombre"));
        estudiante.setSegundoNombre(conjuntoResultados.getString("segundonombre"));
        estudiante.setEstado(conjuntoResultados.getBoolean("estado"));
    }
}
