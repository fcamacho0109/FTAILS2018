package module.pacientes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import module.database.Database;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEditarPaciente implements Initializable {

    private ArrayList<String> listPacientes;

    private ArrayList<String> listDatosPacientes;

    @FXML
    ComboBox<String> comboBox_paciente;

    @FXML
    TextField text_mascota;

    @FXML
    TextField text_domicilio;

    @FXML
    Button button_save;

    public void listPacientes() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listPacientes = database.pacientes();
        comboBox_paciente.getItems().addAll(listPacientes);
    }
    @FXML
    private void printListDatosPacientes() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listDatosPacientes = database.pacientesDatos(comboBox_paciente.getValue());
        text_mascota.setText(listDatosPacientes.get(1));
        text_domicilio.setText(listDatosPacientes.get(2));
    }

    @FXML
    private void editarPaciente() throws SQLException, ClassNotFoundException {
        String masc, dom, nom;

        masc = text_mascota.getText();
        dom = text_domicilio.getText();
        nom = comboBox_paciente.getValue();

        if(!(masc.equals("")) && !(dom.equals("")) && !(nom.equals(""))){
            Database database = new Database();
            database.updatePacientes(masc,dom,nom);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS!");
            alert.setHeaderText(null);
            alert.setContentText("EDICIÓN EXITOSA, COMO TU");

            alert.showAndWait();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Favor de introducir datos");

            alert.showAndWait();
        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listPacientes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
