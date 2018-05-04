package module.ventas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import module.database.Database;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerVentas implements Initializable {
    private ArrayList<String> listaProductos;
    @FXML
    ComboBox<String> combobox_lista_productos;
    @FXML
    TextField textfield_producto;
    @FXML
    Button button_agregar;
    @FXML
    ScrollPane scrollpane_lista;
    @FXML
    public void vender(){
        // descontar existencia de productos y registrar en ventas
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        try {
            printProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printProducts () throws SQLException, ClassNotFoundException {

        Database data = new Database(); // crea instancia para conectar al server
        listaProductos = data.productos();
        combobox_lista_productos.getItems().addAll(listaProductos);
        System.out.println("Producto seleccionado: "+combobox_lista_productos.getValue());

    }

    //public void
}
