package module.citas;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cita {
    private String pacienteNombre = "";
    private String mascotaNombre = "";
    private String medico = ""; //nombre + apellido + id del medico
    // fechas iniciales
    private String fechaInicial = "";
    private String horaInicial = "";
    private String minutoInicial = "";
    // fechas finales
    private String fechaFinal = "";
    private String horaFinal = "";
    private String minutoFinal = "";

    public Cita() {
    }

    public Cita(String pacienteNombre, String mascotaNombre, String medico,
                String fechaInicial, String horaInicial, String minutoInicial,
                String fechaFinal, String horaFinal, String minutoFinal) {
        this.pacienteNombre = pacienteNombre;
        this.mascotaNombre = mascotaNombre;
        this.medico = medico;
        this.fechaInicial = fechaInicial;
        this.horaInicial = horaInicial;
        this.minutoInicial = minutoInicial;
        this.fechaFinal = fechaFinal;
        this.horaFinal = horaFinal;
        this.minutoFinal = minutoFinal;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getMinutoInicial() {
        return minutoInicial;
    }

    public void setMinutoInicial(String minutoInicial) {
        this.minutoInicial = minutoInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getMinutoFinal() {
        return minutoFinal;
    }

    public void setMinutoFinal(String minutoFinal) {
        this.minutoFinal = minutoFinal;
    }

    public int guardarCita(Connection connection){
        try {
            //String idQuery = "SELECT MAX(idCita)+1 FROM Citas"; // debe tener otro prepared statement
            String query = "INSERT INTO Citas (idCita, paciente, mascota, medico, fechaInicial," +
                    "horaInicial, minutoInicial, fechaFinal, horaFinal, minutoFinal) " +
                    "VALUES (SELECT MAX(idCita)+1 FROM Citas,?,?,?,?,?,?,?,?,?)";

            PreparedStatement instruction = connection.prepareStatement(query); // no se si se empieza desde 1
            instruction.setString(2,getPacienteNombre());
            instruction.setString(3,getMascotaNombre());
            instruction.setString(4,getMedico());
            instruction.setString(5,getFechaInicial());
            instruction.setInt(6,Integer.valueOf(getHoraInicial()));
            instruction.setInt(7,Integer.valueOf(getMinutoInicial()));
            instruction.setString(8,getFechaFinal());
            instruction.setInt(9,Integer.valueOf(getHoraFinal()));
            instruction.setInt(10,Integer.valueOf(getMinutoFinal()));

            return instruction.executeUpdate();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection error");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
            return 0;
        }
    }
}
/* *
 * https://github.com/konkkeror/JavaFX_MySQL_Tutorial/tree/master/EjercicioAlumnos/src
 * */

/* *
 * http://lineadecodigo.com/java/conectar-mysql-java/
 * */
