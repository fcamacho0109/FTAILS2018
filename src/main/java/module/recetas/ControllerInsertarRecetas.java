package module.recetas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import module.database.Database;

import java.sql.SQLException;
import java.util.Date;

public class ControllerInsertarRecetas {

    private String medic, pacient, medicamento, cant,desc;

    @FXML
    TextField text_medic;

    @FXML
    TextField text_pacient;

    @FXML
    TextField text_medicamento;

    @FXML
    TextField text_cantidad;

    @FXML
    Button add_button;

    @FXML
    TextArea textArea_descripcion;

    @FXML
    public void insertarReceta() throws SQLException, ClassNotFoundException {

        Database database = new Database();

        Date date = new Date();

        medic = text_medic.getText();
        pacient = text_pacient.getText();
        medicamento = text_medicamento.getText();
        cant = text_cantidad.getText();
        desc = textArea_descripcion.getText();

        int cantidad = Integer.parseInt(cant);

        if(!(medic.equals("")) && !(pacient.equals("")) && !(medicamento.equals("")) && !(cant.equals("")) && !(desc.equals(""))){
            database.insertarRecetas(medic,pacient,medicamento,desc,cantidad,date);
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
