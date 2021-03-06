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
    private AnchorPane anchorpane_workspace;
    // todas las tabs:
    @FXML
    private TabPane principal_tab_pane;
    // citas:
    @FXML
    private Tab tab_citas;
    @FXML
    private Button boton_nueva_cita;
    @FXML
    private Button boton_editar_cita;
    @FXML
    private Button boton_ver_cita;
    // pacientes:
    @FXML
    private Tab tab_pacientes;
    @FXML
    private Button boton_nuevo_paciente;
    @FXML
    private Button boton_editar_paciente;
    @FXML
    private Button boton_lista_paciente;
    // compra y venta:
    @FXML
    private Tab tab_compra_venta;
    @FXML
    private Button boton_nueva_venta;
    @FXML
    private Button boton_lista_venta;
    @FXML
    private Button boton_stats_venta;
    @FXML
    private Button boton_nueva_compra;
    @FXML
    private Button boton_lista_compra;
    @FXML
    private Button boton_stats_compra;
    @FXML
    private Button boton_ganancias;
    // productos:
    @FXML
    private Tab tab_inventario;
    @FXML
    private Button boton_nuevo_producto;
    @FXML
    private Button boton_editar_producto;
    @FXML
    private Button boton_lista_producto;
    // Recetas:
    @FXML
    private Tab tab_recetas;
    @FXML
    private Button boton_nueva_receta;
    @FXML
    private Button boton_editar_receta;
    @FXML
    private Button boton_lista_receta;
    // usuarios:
    @FXML
    private Tab tab_usuarios;
    @FXML
    private Button boton_nuevo_usuario;
    @FXML
    private Button boton_editar_usuario;
    @FXML
    private Button boton_lista_usuario;
    // Help:
    @FXML
    private Tab tab_ayuda;
    @FXML
    private Button boton_ayuda_help;
    @FXML
    private Button boton_ayuda_info;

    @FXML
    public void nuevaCita() throws Exception {
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
    @FXML
    public void nuevaVenta() throws Exception {
        // carga la vista de ver cita
        final String RUTA_VENTA = "/res_ventas/VentaMenu.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_VENTA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void listarVentas() throws Exception {
        // carga la vista de ver cita
        final String RUTA_VENTA = "/res_ventas/VentasListado.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_VENTA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void detalleVenta() throws Exception {
        // carga la vista de ver cita
        final String RUTA_VENTA = "/res_ventas/VentaDetalle.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_VENTA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void nuevaCompra() throws Exception {
        // carga la vista de ver cita
        final String RUTA_VENTA = "/res_compras/CompraMenu.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_VENTA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void listaCompra() throws Exception {
        // carga la vista de ver cita
        final String RUTA_VENTA = "/res_compras/CompraLista.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_VENTA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void detalleCompra() throws Exception {
        // carga la vista de ver cita
        final String RUTA_VENTA = "/res_compras/CompraDetalle.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_VENTA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void listaProductos() throws Exception {
        // carga la vista de ver cita
        final String RUTALISTA = "/res_productos/ProductoListado.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTALISTA));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    //*************************************//
    @FXML
    public void onClickUsuario() throws Exception {
        // carga la vista de ver cita
        final String RUTA_EMPLEADOS = "/res_empleados/EmpleadosListado.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_EMPLEADOS));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickPaciente() throws Exception {
        // carga la vista de ver cita
        final String RUTA_PACIENTES = "/res_pacientes/PacientesListado.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_PACIENTES));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickRecetas() throws Exception {
        // carga la vista de ver cita
        final String RUTA_RECETAS = "/res_recetas/RecetasListado.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_RECETAS));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickEditar() throws Exception {
        // carga la vista de ver cita
        System.out.println("Hola");
        final String RUTA_PACIENTES_EDITAR = "/res_pacientes/PacientesEditar.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_PACIENTES_EDITAR));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickEditar_Usuarios() throws Exception {
        // carga la vista de ver cita
        System.out.println("Hola");
        final String RUTA_EMPLEADOS_EDITAR = "/res_empleados/EmpleadosEditar.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_EMPLEADOS_EDITAR));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickEditar_Recetas() throws Exception {
        // carga la vista de ver cita
        System.out.println("Hola");
        final String RUTA_RECETAS_EDITAR = "/res_recetas/RecetasEditar.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_RECETAS_EDITAR));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickInsertar_Pacientes() throws Exception {
        // carga la vista de ver cita
        System.out.println("Hola");
        final String RUTA_PACIENTES_INSERTAR = "/res_pacientes/PacientesInsertar.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_PACIENTES_INSERTAR));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickInsertar_Users() throws Exception {
        // carga la vista de ver cita
        System.out.println("Hola");
        final String RUTA_EMPLEADOS_INSERTAR = "/res_empleados/EmpleadosInsertar.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_EMPLEADOS_INSERTAR));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }

    @FXML
    public void onClickInsertar_Recetas() throws Exception {
        // carga la vista de ver cita
        System.out.println("Hola");
        final String RUTA_RECETAS_INSERTAR = "/res_recetas/RecetasInsertar.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_RECETAS_INSERTAR));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
    @FXML
    public void insertNuevoProducto() throws Exception {
        // carga la vista de ver cita
        final String RUTA_RECETAS_INSERTAR = "/res_productos/ProductoNuevo.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(RUTA_RECETAS_INSERTAR));
        AnchorPane temp = (AnchorPane) fxmlLoader.load();
        anchorpane_workspace.getChildren().setAll(temp);
    }
}
