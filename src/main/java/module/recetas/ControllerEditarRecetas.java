package module.recetas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import module.database.Database;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEditarRecetas implements Initializable {

    private ArrayList<String> listRecetas;

    private ArrayList<String> listDatosRecetas;

    @FXML
    ComboBox<String> comboBox_Medico;

    @FXML
    TextField text_medicamento;

    @FXML
    TextField text_cantidad;

    @FXML
    TextArea text_descripcion;


    @FXML
    Button save_button;

    public void listRecetas() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listRecetas = database.recetas();
        comboBox_Medico.getItems().addAll(listRecetas);
    }
    @FXML
    private void printListaDatosRecetas() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listDatosRecetas = database.recetasDatos(comboBox_Medico.getValue());
        text_medicamento.setText(listDatosRecetas.get(0));
        text_cantidad.setText(listDatosRecetas.get(1));
        text_descripcion.setText(listDatosRecetas.get(2));
    }

    @FXML
    private void editarRecetas() throws SQLException, ClassNotFoundException {
        String prod, cant, desc,nombreMedico;
        int cantidad;

        prod = text_medicamento.getText();
        cant = text_cantidad.getText();
        desc = text_descripcion.getText();
        nombreMedico = comboBox_Medico.getValue();

        cantidad = Integer.parseInt(cant);

        if(!(prod.equals("")) && !(cant.equals("")) && !(desc.equals(""))){
            Database database = new Database();
            database.updateRecetas(prod,cantidad,desc,nombreMedico);

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
            listRecetas();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}