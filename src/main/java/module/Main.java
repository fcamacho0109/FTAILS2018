package module;
/**
 * */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * */
public class Main extends Application {
    /**
     * */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/res_main/MainScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(final String[] args) {
        launch(args);
    }
}
