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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerListaVentas implements Initializable {
    private ArrayList<Venta> listaVenta;
    @FXML
    ScrollPane scrollpane_lista;

    public void addVentas() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        listaVenta = database.listaVenta();
        pintarTabla(FXCollections.observableArrayList(listaVenta));
    }

    private void pintarTabla (ObservableList<Venta> listaProductosVenta) {
        TableView<Venta> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        TableColumn subCol = new TableColumn("Subtotal");
        subCol.setCellValueFactory(new PropertyValueFactory<Venta, Float>("subtotal"));
        subCol.setMinWidth(80);

        TableColumn ivaCol = new TableColumn("IVA");
        ivaCol.setCellValueFactory(new PropertyValueFactory<Venta, Integer>("iva"));
        ivaCol.setMinWidth(40);

        TableColumn totalCol = new TableColumn("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<Venta, Float>("total"));
        totalCol.setMinWidth(80);

        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<Venta, Date>("fecha"));
        fechaCol.setMinWidth(60);

        TableColumn descripcionCol = new TableColumn("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Venta, String>("descripcion"));
        descripcionCol.setMinWidth(400);


        table.getColumns().addAll(subCol,ivaCol,totalCol,fechaCol,descripcionCol);
        table.setItems(listaProductosVenta);


        scrollpane_lista.setContent(table);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addVentas();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
