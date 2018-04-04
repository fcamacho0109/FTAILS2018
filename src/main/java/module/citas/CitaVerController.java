package module.citas;
/**
 * */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * */
public class CitaVerController implements Initializable {
    /**
     * */
    private boolean enabled = false;
    /**
     * */
    @FXML
    AnchorPane anchor_pane_base;
    /**
     * */
    @FXML
    ScrollPane scroll_pane_citas;
    /**
     * */
    @FXML
    RadioButton radio_lista;
    /**
     * */
    @FXML
    RadioButton radio_agenda;
    /**
     * */
    @FXML
    Button boton_buscar;
    /**
     * */
    @FXML
    TextField text_buscar;
    /**
     * */
    final ToggleGroup group = new ToggleGroup();
    /**
     * */
    @FXML
    public void buscarCita() throws Exception {
        // ejecutar progress bar // tentativo a eliminar progress bar
        // buscar por summary en la BD y activar el progress indicator con codigo de Android
        // mostrar como tabla en lista
        boton_buscar.setDisable(true);
        // activarlo despues del query
    }
    /**
     * */
    public void showAsAgenda() {
        // ejecutar progress bar
        // select a la BD tabla de citas y acomodar datos para generar agenda
    }
    /**
     * */
    public void showAsLista() {
        // ejecutar progress bar
        // select a la BD tabla de citas para presentarla como tabla
    }
    /**
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // cargar la agenda como agenda por default
        // sera mas facil como lista en tabla???
        radio_lista.setToggleGroup(group);
        radio_lista.setSelected(true);
        radio_agenda.setToggleGroup(group);
    }

}
