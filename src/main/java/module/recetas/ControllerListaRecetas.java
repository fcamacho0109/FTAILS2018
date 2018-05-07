package module.recetas;

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

public class ControllerListaRecetas implements Initializable {

    private ArrayList<Receta> recetaArrayList;

    @FXML
    ScrollPane scrollPane_recetas;

    public void addRecetas() throws SQLException, ClassNotFoundException {

        Database database = new Database();

        recetaArrayList = database.listaRecetas();

        pintarTabla(FXCollections.observableArrayList(recetaArrayList));
    }

    private void pintarTabla(ObservableList<Receta> listaReceta) {
        TableView<Receta> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        //Nombre medico
        TableColumn nameCol = new TableColumn("Medico");
        nameCol.setCellValueFactory(new PropertyValueFactory<Receta, String>("nombreMedico"));
        nameCol.setMinWidth(80);
        //Nombre paciente
        TableColumn pacCol = new TableColumn("Paciente");
        pacCol.setCellValueFactory(new PropertyValueFactory<Receta, String>("nombrePaciente"));
        pacCol.setMinWidth(40);
        //Nombre medicamento
        TableColumn medCol = new TableColumn("Medicamento");
        medCol.setCellValueFactory(new PropertyValueFactory<Receta, String>("medicamento"));
        medCol.setMinWidth(80);
        //Cantidad medicamento
        TableColumn cantCol = new TableColumn("Cantidad");
        cantCol.setCellValueFactory(new PropertyValueFactory<Receta, String>("cantidad"));
        cantCol.setMinWidth(80);
        //Descripcion Receta
        TableColumn descCol = new TableColumn("Descripcion");
        descCol.setCellValueFactory(new PropertyValueFactory<Receta, String>("descripcion"));
        descCol.setMinWidth(80);
        //Fecha Receta
        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<Receta, String>("fecha"));
        fechaCol.setMinWidth(80);


        table.getColumns().addAll(nameCol, pacCol, medCol,cantCol,descCol,fechaCol);
        table.setItems(listaReceta);
        scrollPane_recetas.setContent(table);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            addRecetas();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
