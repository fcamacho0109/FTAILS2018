package module.toolbar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class ControllerToolBar {
    // anchorpane todo:
    @FXML
    AnchorPane anchorpane_workspace;
    // todas las tabs:
    @FXML
    TabPane principal_tab_pane;
    // citas:
    @FXML
    Tab tab_citas;
    @FXML
    Button boton_nueva_cita;
    @FXML
    Button boton_editar_cita;
    @FXML
    Button boton_ver_cita;
    // pacientes:
    @FXML
    Tab tab_pacientes;
    @FXML
    Button boton_nuevo_paciente;
    @FXML
    Button boton_editar_paciente;
    @FXML
    Button boton_lista_paciente;
    // compra y venta:
    @FXML
    Tab tab_compra_venta;
    @FXML
    Button boton_nueva_venta;
    @FXML
    Button boton_lista_venta;
    @FXML
    Button boton_stats_venta;
    @FXML
    Button boton_nueva_compra;
    @FXML
    Button boton_lista_compra;
    @FXML
    Button boton_stats_compra;
    @FXML
    Button boton_ganancias;
    // productos:
    @FXML
    Tab tab_inventario;
    @FXML
    Button boton_nuevo_producto;
    @FXML
    Button boton_editar_producto;
    @FXML
    Button boton_lista_producto;
    // Recetas:
    @FXML
    Tab tab_recetas;
    @FXML
    Button boton_nueva_receta;
    @FXML
    Button boton_editar_receta;
    @FXML
    Button boton_lista_receta;
    // usuarios:
    @FXML
    Tab tab_usuarios;
    @FXML
    Button boton_nuevo_usuario;
    @FXML
    Button boton_editar_usuario;
    @FXML
    Button boton_lista_usuario;
    // Help:
    @FXML
    Tab tab_ayuda;
    @FXML
    Button boton_ayuda_help;
    @FXML
    Button boton_ayuda_info;

    @FXML
    public void nuevaCita() throws Exception{
        // carga la vista de nueva cita
        final String RUTA_CITA = "/res_citas/NuevaCita.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_CITA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void editarCita() throws Exception {
        // carga la vista de editar cita
        final String RUTA_CITA = "/res_citas/EditarCita.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_CITA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void verCita() throws Exception {
        // carga la vista de ver cita
        final String RUTA_CITA = "/res_citas/VerCita.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_CITA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
}
