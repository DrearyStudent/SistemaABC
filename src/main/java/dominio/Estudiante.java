package dominio;

public class Estudiante {
    private int idEstudiante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String primerNombre;
    private String segundoNombre;
    private boolean estado;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String apellidoPaterno, String apellidMaterno, String primerNombre, String segundoNombre, boolean estado) {
        this.idEstudiante = idEstudiante;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidMaterno;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.estado = estado;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
