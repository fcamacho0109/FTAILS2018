package module;
/**
 * */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * */
public class Controller implements Initializable {
    /**
     * */
    private final int maxChars = 8;
    /**
     * */
    @FXML
    private TextField userLoginText;
    /**
     * */
    @FXML
    private PasswordField userLoginPass;
    /**
     * */
    @FXML
    private Button loginBtn;
    /**
     * */
    @FXML
    private ComboBox<String> comboType; // cambiar tipo de dato a desplegar
    /**
     * */
    @FXML
    private Label loginLabel;
    /**
     * */
    @FXML
    private Label labelBienvenido;
    /**
     * */
    @FXML
    private Label labelUsuario;
    /**
     * */
    @FXML
    private Label labelContrasena;
    /**
     * */
    @FXML
    private void loginBtn() {

        String user = "";
        String pass = "";
        user = userLoginText.getText().toString();
        pass = userLoginPass.getText().toString();

        if (user.length() <= maxChars && pass.length() <= maxChars) {
            userLoginText.setText("");
            userLoginPass.setText("");
            userLoginText.setPromptText("Minimo 8 caracteres");
            userLoginPass.setPromptText("Minimo 8 caracteres");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Aun no se definen acciones para el boton!"
                    + "\nEditar metodo login_Btn() para agregar login \n"
                    + "con user role y password de la BD");


            alert.showAndWait();
        } else {
           // lo de login con el user de la bd
            System.out.println("aqui va el login de la bd");
        }


    }

    /**
     * */
    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        userLoginText.setPromptText("Minimo 8 caracteres");
        userLoginPass.setPromptText("Minimo 8 caracteres");
      /*  comboType.setItems(FXCollections.observableArrayList(
                User.Rol.Admin,
                User.Rol.General,
                User.Rol.Medico,
                User.Rol.Root
        ));*/
    }
    /**
     * */
    @FXML
    public final void temporalLogin() { //METODO TEMPORAL PARA BRINCAR EL LOGIN

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass()
                    .getResource("/res_toolbar/GeneralToolbar.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root1));
            primaryStage.show();
            Stage stage2 = (Stage) labelBienvenido.getScene().getWindow();
            stage2.close();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
