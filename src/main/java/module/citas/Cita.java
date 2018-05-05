package module.citas;
/**
 * */
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * */
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

    public Cita(final String pacienteNom, final String mascotaNom,
                final String doctor, final String fechaIni,
                final String horaIni, final String minutoIni,
                final String fechaFi, final String horaFi,
                final String minutoFi) {
        this.pacienteNombre = pacienteNom;
        this.mascotaNombre = mascotaNom;
        this.medico = doctor;
        this.fechaInicial = fechaIni;
        this.horaInicial = horaIni;
        this.minutoInicial = minutoIni;
        this.fechaFinal = fechaFi;
        this.horaFinal = horaFi;
        this.minutoFinal = minutoFi;
    }
    /**
     * */
    public final String getPacienteNombre() {
        return pacienteNombre;
    }
    /**
     * */
    public final void setPacienteNombre(final String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }
    /**
     * */
    public final String getMascotaNombre() {
        return mascotaNombre;
    }
    /**
     * */
    public final void setMascotaNombre(final String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }
    /**
     * */
    public final String getMedico() {
        return medico;
    }
    /**
     * */
    public final void setMedico(final String medico) {
        this.medico = medico;
    }
    /**
     * */
    public final String getFechaInicial() {
        return fechaInicial;
    }
    /**
     * */
    public final void setFechaInicial(final String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    /**
     * */
    public final String getHoraInicial() {
        return horaInicial;
    }
    /**
     * */
    public final void setHoraInicial(final String horaInicial) {
        this.horaInicial = horaInicial;
    }
    /**
     * */
    public final String getMinutoInicial() {
        return minutoInicial;
    }
    /**
     * */
    public final void setMinutoInicial(final String minutoInicial) {
        this.minutoInicial = minutoInicial;
    }
    /**
     * */
    public final String getFechaFinal() {
        return fechaFinal;
    }
    /**
     * */
    public final void setFechaFinal(final String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    /**
     * */
    public final String getHoraFinal() {
        return horaFinal;
    }
    /**
     * */
    public final void setHoraFinal(final String horaFinal) {
        this.horaFinal = horaFinal;
    }
    /**
     * */
    public final String getMinutoFinal() {
        return minutoFinal;
    }
    /**
     * */
    public final void setMinutoFinal(final String minutoFinal) {
        this.minutoFinal = minutoFinal;
    }
    /**
     * */
    public final int guardarCita(final Connection connection) {
        try {
            //String idQuery = "SELECT MAX(idCita)+1 FROM Citas";
            // debe tener otro prepared statement
            String query = "INSERT INTO Citas "
                    + "(idCita, paciente, mascota, medico, fechaInicial,"
                    + "horaInicial, minutoInicial, fechaFinal, horaFinal,"
                    + "minutoFinal) "
                    + "VALUES"
                    + " (SELECT MAX(idCita)+1 FROM Citas,?,?,?,?,?,?,?,?,?)";

            PreparedStatement instruction = connection.prepareStatement(query);
            // no se si se empieza desde 1
            instruction.setString(2, getPacienteNombre());
            instruction.setString(3, getMascotaNombre());
            instruction.setString(4, getMedico());
            instruction.setString(5, getFechaInicial());
            instruction.setInt(6, Integer.valueOf(getHoraInicial()));
            instruction.setInt(7, Integer.valueOf(getMinutoInicial()));
            instruction.setString(8, getFechaFinal());
            instruction.setInt(9, Integer.valueOf(getHoraFinal()));
            instruction.setInt(10, Integer.valueOf(getMinutoFinal()));

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
