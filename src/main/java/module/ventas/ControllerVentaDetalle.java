package module.ventas;

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

public class ControllerVentaDetalle implements Initializable{
    private ArrayList<VentaDetalle> ventaDetalles;
    @FXML
    ScrollPane scrollpane_detalles;

    public void addDetalles() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        ventaDetalles = database.listaVentaDetalle();
        pintarTabla(FXCollections.observableArrayList(ventaDetalles));
    }

    private void pintarTabla (ObservableList<VentaDetalle> listaProductosVenta) {
        TableView<VentaDetalle> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        TableColumn nomCol = new TableColumn("Nombre");
        nomCol.setCellValueFactory(new PropertyValueFactory<VentaDetalle, String>("producto"));
        nomCol.setMinWidth(80);

        TableColumn vtaCol = new TableColumn("Venta");
        vtaCol.setCellValueFactory(new PropertyValueFactory<VentaDetalle, Integer>("venta"));
        vtaCol.setMinWidth(40);

        TableColumn totalCol = new TableColumn("Precio");
        totalCol.setCellValueFactory(new PropertyValueFactory<VentaDetalle, Float>("precio"));
        totalCol.setMinWidth(80);

        TableColumn descripcionCol = new TableColumn("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<VentaDetalle, String>("descripcion"));
        descripcionCol.setMinWidth(400);


        table.getColumns().addAll(nomCol,vtaCol,totalCol,descripcionCol);
        table.setItems(listaProductosVenta);

        scrollpane_detalles.setContent(table);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addDetalles();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
