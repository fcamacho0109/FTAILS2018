package module.citas;
/**
 * */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * */
public class CitaEditarController implements Initializable {
    /**
     * */
    @FXML
    private AnchorPane anchor_pane_base;
    /**
     * */
    @FXML
    private ScrollPane scroll_agenda;
    /**
     * */
    @FXML
    private Button boton_guardar;
    /**
     * */
    @FXML
    public final void updateCitas() {
        // actualizar citas en la BD
        // controller cita tiene funcion re agendar con metodos que funcionarian
        // revisar mis notas de voz para ver como tratar los datos de la agenda
    }
    /**
     * */
    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        // cargar los datos de la BD tabla de citas en la agenda
    }
}

/* *
 * http://jfxtras.org/doc/8.0/jfxtras-agenda/jfxtras/scene/control/agenda/Agenda.html
 * */
