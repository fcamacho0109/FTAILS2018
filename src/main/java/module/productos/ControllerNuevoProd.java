package module.productos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import module.database.Database;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerNuevoProd implements Initializable{
    private String nombre, descripcion, precio, tipo;
    @FXML
    TextField textfield_prodNombre;
    @FXML
    TextField textfield_prodDescripcion;
    @FXML
    TextField textfield_prodPrecio;
    @FXML
    ComboBox<String> combobox_prodTipo;
    @FXML
    Button button_agregar;

    @FXML
    private void addProduct () throws SQLException, ClassNotFoundException {
        nombre = textfield_prodNombre.getText();
        descripcion = textfield_prodDescripcion.getText();
        precio = textfield_prodPrecio.getText();
        tipo = combobox_prodTipo.getValue();

        Database database = new Database();

        try {
            database.registrarProducto(tipo,
                    nombre,
                    descripcion,
                    Float.parseFloat(precio));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Producto insertado");
            alert.setHeaderText(null);
            alert.setContentText("Producto agregado a inventario");

            alert.showAndWait();
            textfield_prodDescripcion.setText("");
            textfield_prodNombre.setText("");
            textfield_prodPrecio.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());

            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "1",
                        "2",
                        "3"
                );
        combobox_prodTipo.setItems(options);
    }
}
