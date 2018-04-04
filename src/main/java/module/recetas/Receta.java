package module.recetas;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Receta {
    /*Fields:
    * Receta_Medico
    * Receta_Paciente
    * Receta_Descripcion
    * Receta_Fecha*/
    private String recetaMedico = "";
    private String recetaPaciente = "";
    private String recetaDescripcion = "";
    // fechas receta
    private String recetaFecha = "";
    private String horaReceta = "";
    private String minutoReceta = "";

    public Receta() {
    }

    public Receta(String recetaMedico, String recetaPaciente, String recetaDescripcion,
                String recetaFecha, String horaReceta, String minutoReceta) {
        this.recetaMedico = recetaMedico;
        this.recetaPaciente = recetaPaciente;
        this.recetaDescripcion = recetaDescripcion;
        this.recetaFecha = recetaFecha;
        this.horaReceta = horaReceta;
        this.minutoReceta = minutoReceta;

    }

    public String getRecetaMedico() {
        return recetaMedico;
    }

    public void setRecetaMedico(String recetaMedico) {
        this.recetaMedico = recetaMedico;
    }

    public String getRecetaPaciente() {
        return recetaPaciente;
    }

    public void setRecetaPaciente(String recetaPaciente) {
        this.recetaPaciente = recetaPaciente;
    }

    public String getRecetaDescripcion() {
        return recetaDescripcion;
    }

    public void setRecetaDescripcion(String recetaDescripcion) {
        this.recetaDescripcion = recetaDescripcion;
    }

    public String getFechaReceta() {
        return recetaFecha;
    }

    public void setFechaReceta(String fechaInicial) {
        this.recetaFecha = recetaFecha;
    }

    public String getHoraReceta() {
        return horaReceta;
    }

    public void setHoraReceta(String horaReceta) {
        this.horaReceta = horaReceta;
    }

    public String getMinutoReceta() {
        return minutoReceta;
    }

    public void setMinutoReceta(String minutoReceta) {
        this.minutoReceta = minutoReceta;
    }


    public int guardarReceta(Connection connection){
        try {
            //String idQuery = "SELECT MAX(idCita)+1 FROM Citas"; // debe tener otro prepared statement
            String query = "INSERT INTO Recetas (idReceta, paciente, mascota, medico, fechaInicial," +
                    "horaInicial, minutoInicial, fechaFinal, horaFinal, minutoFinal) " +
                    "VALUES (SELECT MAX(idReceta)+1 FROM Recetas,?,?,?,?,?,?,?,?,?)";

            PreparedStatement instruction = connection.prepareStatement(query); // no se si se empieza desde 1
            instruction.setString(2,getRecetaMedico());
            instruction.setString(3,getRecetaPaciente());
            instruction.setString(4,getRecetaDescripcion());
            instruction.setString(5,getFechaReceta());
            instruction.setInt(6,Integer.valueOf(getHoraReceta()));
            instruction.setInt(7,Integer.valueOf(getMinutoReceta()));
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
