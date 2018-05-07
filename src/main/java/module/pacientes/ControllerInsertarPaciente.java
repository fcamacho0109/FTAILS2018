package module.pacientes;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import module.database.Database;

import java.sql.SQLException;

public class ControllerInsertarPaciente{

    private String nombre, mascota, domicilio;

    @FXML
    TextField text_nombrePaciente;

    @FXML
    TextField text_domicilio;

    @FXML
    TextField text_mascota;

    @FXML
    Button add_button;

   @FXML
    public void insertarPaciente() throws SQLException, ClassNotFoundException {

        Database database = new Database();

        nombre = text_nombrePaciente.getText();
        mascota = text_mascota.getText();
        domicilio = text_domicilio.getText();


      if(!(nombre.equals("")) && !(mascota.equals("")) && !(domicilio.equals(""))){
          database.insertarPacientes(nombre,mascota,domicilio);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("SUCCESS!!!");
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
