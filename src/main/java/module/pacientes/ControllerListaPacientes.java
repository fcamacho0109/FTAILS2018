package module.pacientes;

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

public class ControllerListaPacientes implements Initializable {

    private ArrayList<Paciente> pacienteArrayList;

    @FXML
    ScrollPane scrollPane_lista_pacientes;

    public void addPacientes() throws SQLException, ClassNotFoundException {

        Database database = new Database();

        pacienteArrayList = database.listaPacientes();

        pintarTabla(FXCollections.observableArrayList(pacienteArrayList));
    }

    private void pintarTabla(ObservableList<Paciente> listaPacientes) {
        TableView<Paciente> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);
        TableColumn nameCol = new TableColumn("Nombre");
        nameCol.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
        nameCol.setMinWidth(80);

        TableColumn mascCol = new TableColumn("Mascota");
        mascCol.setCellValueFactory(new PropertyValueFactory<Paciente, String>("mascota"));
        mascCol.setMinWidth(40);

        TableColumn domCol = new TableColumn("Domicilio");
        domCol.setCellValueFactory(new PropertyValueFactory<Paciente, String>("domicilio"));
        domCol.setMinWidth(80);


        table.getColumns().addAll(nameCol, mascCol, domCol);
        table.setItems(listaPacientes);
        scrollPane_lista_pacientes.setContent(table);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            addPacientes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
