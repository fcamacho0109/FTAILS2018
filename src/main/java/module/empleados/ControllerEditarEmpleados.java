package module.empleados;

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

public class ControllerEditarEmpleados implements Initializable {

    private ArrayList<String> listUsuarios;

    private ArrayList<String> listDatosUsuarios;

    @FXML
    ComboBox<String> comboBox_usuario;

    @FXML
    TextField text_telefono;

    @FXML
    TextField text_domicilio;

    @FXML
    Button save_button;

    public void listUsuarios() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listUsuarios = database.usuarios();
        comboBox_usuario.getItems().addAll(listUsuarios);
    }
    @FXML
    private void printListDatosUsuarios() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listDatosUsuarios = database.usuariosDatos(comboBox_usuario.getValue());
        text_telefono.setText(listDatosUsuarios.get(1));
        text_domicilio.setText(listDatosUsuarios.get(2));
    }

    @FXML
    private void editarUsuarios() throws SQLException, ClassNotFoundException {
        String tel, dom, nom;

        tel = text_telefono.getText();
        dom = text_domicilio.getText();
        nom = comboBox_usuario.getValue();

        if(!(tel.equals("")) && !(dom.equals("")) && !(nom.equals(""))){
            Database database = new Database();
            database.updateUser(tel,dom,nom);

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
            listUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}