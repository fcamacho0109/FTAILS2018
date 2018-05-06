package module.compras;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import module.database.Database;
import module.productos.Producto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * */
public class ControllerCompra implements Initializable{
    private Producto producto;
    private float total = 0.0f;
    private ArrayList<String> listaProductos;
    private ObservableList<Producto> listaProductosVenta = FXCollections.observableArrayList();
    @FXML
    ComboBox<String> combobox_productos;
    @FXML
    TextField textfield_cantidad,textfield_descripcion;
    @FXML
    Button button_agregar, button_compra;
    @FXML
    ScrollPane scrollpane_compra;
    @FXML
    Label label_total;

    @FXML
    public void addProducts() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        int cantidad = 0;
        cantidad = Integer.valueOf(textfield_cantidad.getText().toString());

        if (cantidad <= 0 || combobox_productos.getValue() == null) {
            return;
        } else {

            producto = database.prodData (combobox_productos.getValue());
            producto.setCantidad(cantidad);

            total+=producto.getPrecio()*producto.getCantidad();
            listaProductosVenta.add(producto);

        }
        pintarTabla(listaProductosVenta);
    }
    private void pintarTabla(ObservableList<Producto> listaProductosVenta) {
        TableView<Producto> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        TableColumn nameCol = new TableColumn("Producto");
        nameCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        nameCol.setMinWidth(140);

        TableColumn tipoCol = new TableColumn("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("tipo"));
        tipoCol.setMinWidth(40);

        TableColumn descripcionCol = new TableColumn("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        descripcionCol.setMinWidth(400);

        TableColumn precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
        precioCol.setMinWidth(60);

        TableColumn existenciaCol = new TableColumn("Existencia");
        existenciaCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("existencia"));
        existenciaCol.setMinWidth(60);

        TableColumn cantidadCol = new TableColumn("Cantidad");
        cantidadCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidad"));
        cantidadCol.setMinWidth(60);


        table.getColumns().addAll(nameCol,tipoCol,descripcionCol,precioCol,existenciaCol,cantidadCol);
        table.setItems(listaProductosVenta);


        scrollpane_compra.setContent(table);
        label_total.setText(String.valueOf(total));
    }
    @FXML
    public void comprar() throws SQLException, ClassNotFoundException {
        // sumar existencia de productos y registrar en COMPRAS
        Database database = new Database();
        Database aux1 = new Database();
        int maxCpraId = aux1.maxCpraId()+1;
        float compraTotal;
        Date compraFecha = new Date();
        String compraDescripcion;


        compraTotal = Float.parseFloat(label_total.getText());
        compraDescripcion = textfield_descripcion.getText();

        if (!(compraDescripcion == null)) { // ingresar compra
            database.registrarCompra(maxCpraId,compraTotal,compraFecha,compraDescripcion);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No descripcion");
            alert.setHeaderText(null);
            alert.setContentText("Favor de ingresar descripcion para la venta!");

            alert.showAndWait();
        }
        /*
         * insertar venta_producto y actualizar stock:
         * */
        Database aux = new Database();
        for (Producto p: listaProductosVenta) {
            database = new Database();
            int pId = aux.prodId(p.getNombre());
            int cId = aux.maxCpraId();
            int pStock = p.getExistencia() + p.getCantidad();

            database.registrarProdCpra(pId,cId,p.getPrecio(),compraDescripcion); //idProducto, idVenta, precio, descripcion
            database.updateProds(pStock,p.getNombre()); //stock, prodName
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra");
        alert.setHeaderText(null);
        alert.setContentText("Compra registrada!");

        alert.showAndWait();

    }
    public void printProducts () throws SQLException, ClassNotFoundException {

        Database data = new Database(); // crea instancia para conectar al server
        listaProductos = data.productos();
        combobox_productos.getItems().addAll(listaProductos);
        System.out.println("Producto seleccionado: "+combobox_productos.getValue());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            printProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
