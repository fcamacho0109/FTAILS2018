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
import module.ventas.VentaDetalle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerCompraDetalle implements Initializable {
    private ArrayList<CompraDetalle> compraDetalles;
    @FXML
    ScrollPane scrollpane_detalle;

    public void addDetalles() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        compraDetalles = database.listaCompraDetalle();
        pintarTabla(FXCollections.observableArrayList(compraDetalles));
    }

    private void pintarTabla (ObservableList<CompraDetalle> listaProductosVenta) {
        TableView<CompraDetalle> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        TableColumn nomCol = new TableColumn("Nombre");
        nomCol.setCellValueFactory(new PropertyValueFactory<CompraDetalle, String>("producto"));
        nomCol.setMinWidth(80);

        TableColumn vtaCol = new TableColumn("Compra");
        vtaCol.setCellValueFactory(new PropertyValueFactory<CompraDetalle, Integer>("compra"));
        vtaCol.setMinWidth(40);

        TableColumn totalCol = new TableColumn("Precio");
        totalCol.setCellValueFactory(new PropertyValueFactory<CompraDetalle, Float>("precio"));
        totalCol.setMinWidth(80);

        TableColumn descripcionCol = new TableColumn("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<CompraDetalle, String>("descripcion"));
        descripcionCol.setMinWidth(400);


        table.getColumns().addAll(nomCol,vtaCol,totalCol,descripcionCol);
        table.setItems(listaProductosVenta);

        scrollpane_detalle.setContent(table);
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
