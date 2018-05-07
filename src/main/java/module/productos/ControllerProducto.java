package module.productos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.database.Database;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * */
public class ControllerProducto implements Initializable{
    private ArrayList<Producto> productos;
    @FXML
    ScrollPane scrollpane_lista;

    private void pintarTabla(ObservableList<Producto> listaProductosVenta) {
        TableView<Producto> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        TableColumn nameCol = new TableColumn("Producto");
        nameCol.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("nombre"));
        nameCol.setMinWidth(140);

        TableColumn tipoCol = new TableColumn("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("tipo"));
        tipoCol.setMinWidth(40);

        TableColumn descripcionCol = new TableColumn("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        descripcionCol.setMinWidth(400);

        TableColumn precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
        precioCol.setMinWidth(60);

        TableColumn existenciaCol = new TableColumn("Existencia");
        existenciaCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("existencia"));
        existenciaCol.setMinWidth(60);




        table.getColumns().addAll(nameCol,tipoCol,descripcionCol,precioCol,existenciaCol);
        table.setItems(listaProductosVenta);


        scrollpane_lista.setContent(table);
    }

    public void addProducts() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        productos = database.productosListado();

        pintarTabla(FXCollections.observableArrayList(productos));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
