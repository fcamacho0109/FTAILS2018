package module.compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.database.Database;
import module.ventas.Venta;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerCompraLista implements Initializable{
    private ArrayList<Compra> listaCompra;
    @FXML
    ScrollPane scrollpane_lista;

    public void addCompras() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listaCompra = database.listaCompra();
        pintarTabla(FXCollections.observableArrayList(listaCompra));
    }

    private void pintarTabla (ObservableList<Compra> listaProductosVenta) {
        TableView<Compra> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        TableColumn subCol = new TableColumn("Precio");
        subCol.setCellValueFactory(new PropertyValueFactory<Compra, Float>("precio"));
        subCol.setMinWidth(80);

        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<Venta, Date>("fecha"));
        fechaCol.setMinWidth(60);

        TableColumn descripcionCol = new TableColumn("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Venta, String>("descripcion"));
        descripcionCol.setMinWidth(400);


        table.getColumns().addAll(subCol,fechaCol,descripcionCol);
        table.setItems(listaProductosVenta);


        scrollpane_lista.setContent(table);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addCompras();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
