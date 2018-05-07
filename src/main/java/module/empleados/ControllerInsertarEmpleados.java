package module.empleados;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import module.database.Database;

import java.sql.SQLException;

public class ControllerInsertarEmpleados {

    private String nombre, telefono, domicilio, rol;

    @FXML
    TextField text_nombre;

    @FXML
    TextField text_telefono;

    @FXML
    TextField text_domicilio;

    @FXML
    TextField text_rol;

    @FXML
    Button add_button;

    @FXML
    public void insertarUsers() throws SQLException, ClassNotFoundException {

        Database database = new Database();

        nombre = text_nombre.getText();
        telefono = text_telefono.getText();
        domicilio = text_domicilio.getText();
        rol = text_rol.getText();


        if(!(nombre.equals("")) && !(telefono.equals("")) && !(domicilio.equals("")) && !(rol.equals(""))){
            database.insertarUsuarios(nombre,telefono,domicilio,rol);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS!!! RODRIGO ME LA PELA POR QUINTA OCASIÓN");
            alert.setHeaderText(null);
            alert.setContentText("INSERCIÓN EXITOSA, COMO TU");

            alert.showAndWait();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Favor de ingresar datos");

            alert.showAndWait();
        }

    }



}
