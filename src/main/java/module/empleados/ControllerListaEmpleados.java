package module.empleados;

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

public class ControllerListaEmpleados implements Initializable {

    private ArrayList<Empleado> empleadosArray;

    @FXML
    ScrollPane scrollpane_lista;

    public void addEmpleados() throws SQLException, ClassNotFoundException {

        Database database = new Database();

        empleadosArray = database.listaEmpleados();

        pintarTabla(FXCollections.observableArrayList(empleadosArray));
    }

    private void pintarTabla (ObservableList<Empleado> listaEmpleados) {
        TableView<Empleado> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);
        TableColumn nameCol = new TableColumn("Nombre");
        nameCol.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        nameCol.setMinWidth(80);

        TableColumn telCol = new TableColumn("Telefono");
        telCol.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono"));
        telCol.setMinWidth(40);

        TableColumn domCol = new TableColumn("Domicilio");
        domCol.setCellValueFactory(new PropertyValueFactory<Empleado, String>("domicilio"));
        domCol.setMinWidth(80);

        TableColumn rolCol = new TableColumn("Rol");
        rolCol.setCellValueFactory(new PropertyValueFactory<Empleado, String>("rol"));
        rolCol.setMinWidth(60);

        table.getColumns().addAll(nameCol,telCol,domCol,rolCol);
        table.setItems(listaEmpleados);
        scrollpane_lista.setContent(table);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            addEmpleados();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
