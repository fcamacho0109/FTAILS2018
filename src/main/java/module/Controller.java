package module;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField userLoginText;
    @FXML
    PasswordField userLoginPass;
    @FXML
    Button loginBtn;
    @FXML
    ComboBox<String> comboType;
    @FXML
    Label loginLabel;
    @FXML
    Label label_bienvenido;
    @FXML
    Label labelUsuario;
    @FXML
    Label labelContrasena;
    @FXML
    private void login_Btn(){

        String user = "";
        String pass = "";
        user = userLoginText.getText().toString();
        pass = userLoginPass.getText().toString();

        if (user.length()<=8 && pass.length()<=8) {
            userLoginText.setText("");
            userLoginPass.setText("");
            userLoginText.setPromptText("Minimo 8 caracteres");
            userLoginPass.setPromptText("Minimo 8 caracteres");
        }
        else {
            System.out.println("bienvenido");
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Aun no se definen acciones para el boton!" +
                "\nEditar metodo login_Btn() para agregar login \n" +
                "con user role y password de la BD");


        alert.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLoginText.setPromptText("Minimo 8 caracteres");
        userLoginPass.setPromptText("Minimo 8 caracteres");
    }

    public void temporalLogin(){ //METODO TEMPORAL PARA BRINCAR EL LOGIN

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/res_toolbar/GeneralToolbar.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root1));
            primaryStage.show();
            Stage stage2 = (Stage) label_bienvenido.getScene().getWindow();
            stage2.close();
        }catch(Exception e){

        }
    }
}
