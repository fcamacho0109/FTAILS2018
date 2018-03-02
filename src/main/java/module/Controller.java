package module;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    TextField userLoginText;
    @FXML
    TextField userLoginPass;
    @FXML
    Button loginBtn;
    @FXML
    ComboBox<String> comboType;
    @FXML
    Label loginLabel;
    @FXML
    Label labelUsuario;
    @FXML
    Label labelContrasena;
    @FXML
    private void login_Btn(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Aun no se definen acciones para el boton!" +
                "\nEditar metodo login_Btn() para agregar login \n" +
                "con user role y password de la BD");

        alert.showAndWait();
    }

}
