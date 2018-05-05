package module.ventas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import module.database.Database;
import module.productos.Producto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerVentas implements Initializable {
    private float total = 0.0f;
    private ArrayList<String> listaProductos;
    private Producto producto;
    private ObservableList<Producto> listaProductosVenta = FXCollections.observableArrayList();;
    private ArrayList<Producto> listaProds = new ArrayList<>();
    @FXML
    ComboBox<String> combobox_lista_productos;
    @FXML
    Label label_subtotal,label_iva,label_total;
    @FXML
    TextField textfield_producto;
    @FXML
    Button button_agregar;
    @FXML
    ScrollPane scrollpane_lista;
    @FXML
    public void addProducts() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        int cantidad = 0;
        cantidad = Integer.valueOf(textfield_producto.getText().toString());

        if (cantidad <= 0 || combobox_lista_productos.getValue() == null) {
            return;
        } else {

            producto = database.prodData (combobox_lista_productos.getValue());
            if (cantidad<producto.getExistencia())
                producto.setCantidad(cantidad);
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Not enough products");
                alert.setHeaderText(null);
                alert.setContentText("No hay suficientes productos para la dicha venta!");

                alert.showAndWait();
                return;
            }
            total+=producto.getPrecio()*producto.getCantidad();
            listaProductosVenta.add(producto);
            for (Producto p: listaProductosVenta) {
                System.out.println(p.getNombre());
                System.out.println(p.getPrecio());
                System.out.println(p.getDescripcion());
            }
        }
        pintarTabla(listaProductosVenta);
    }

    private void pintarTabla(ObservableList<Producto> listaProductosVenta) {
        TableView<Producto> table = new TableView();
        table.setEditable(true);
        table.setMinWidth(390);

        TableColumn nameCol = new TableColumn("Producto");
        nameCol.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("nombre"));
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


        scrollpane_lista.setContent(table);
        label_subtotal.setText(String.valueOf(total));
        label_iva.setText(String.valueOf(total*0.16));
        label_total.setText(String.valueOf(total*1.16));
    }

    @FXML
    public void vender(){
        // descontar existencia de productos y registrar en ventas


    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        try {
            printProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printProducts () throws SQLException, ClassNotFoundException {

        Database data = new Database(); // crea instancia para conectar al server
        listaProductos = data.productos();
        combobox_lista_productos.getItems().addAll(listaProductos);
        System.out.println("Producto seleccionado: "+combobox_lista_productos.getValue());

    }

    //public void
}
