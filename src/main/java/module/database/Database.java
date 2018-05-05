package module.database;
/**
 *
 * */
import module.productos.Producto;
import module.ventas.Venta;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * */
public class Database extends Thread {
    protected Connection conObj;
    protected Statement stObj;
    protected PreparedStatement ps = null;
    protected ResultSet result = null;
    private ArrayList<String> productList;
    private String query = "";

    private  void Setup() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver"); /*Loading Driver class for JDBC*/
        conObj = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10236271","sql10236271","YKACBtGFXM");
        stObj = conObj.createStatement();
    }

    public void run() {
        try {
            Setup();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Database() throws SQLException, ClassNotFoundException {
        run();
    }

    /**
     * Regresa la lista de productos.
     * @return productList para pintar.
     * */
    public ArrayList<String> productos() throws SQLException {
        //run();
        productList = new ArrayList<>();
        query = "SELECT * FROM PRODUCTOS WHERE existencia>0";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);
        while (result.next()) {
            productList.add(result.getString("nombre"));
        }

        conObj.close();
        return productList;
    }

    public ArrayList<Venta> listaVenta() throws SQLException {
        ArrayList<Venta> ventas = new ArrayList<>();
        Venta venta;
        query = "SELECT * FROM VENTAS";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);

        while (result.next()) {
            venta = new Venta();
            venta.setSubtotal(result.getFloat("subtotal"));
            venta.setIva(result.getInt("iva"));
            venta.setTotal(result.getFloat("total"));
            venta.setFecha(result.getDate("fecha"));
            venta.setDescripcion(result.getString("descripcion"));

            ventas.add(venta);
        }

        return ventas;
    }

    /**
     * Registro de ventas
     * */
    public void registrarVenta(int mVtaId,float subtotal, float iva, float total, Date fecha, String descripcion)
            throws SQLException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(fecha);

        //String subquery = "SELECT MAX(idventa)+1 FROM VENTAS";
        query = "INSERT INTO VENTAS (idventa,subtotal,iva,total,fecha,descripcion) " +
                "VALUES ("+mVtaId+",?,?,?,?,?)";
        ps = conObj.prepareStatement(query);

        ps.setFloat(1,subtotal);
        ps.setFloat(2,iva);
        ps.setFloat(3,total);
        ps.setString(4, currentDate);
        ps.setString(5,descripcion);
        ps.execute();

        //conObj.close();
    }

    public String prodName (int idProd)
            throws SQLException {
        String pName = "";
        query = "SELECT nombre FROM PRODUCTOS WHERE idproducto = ?";
        ps = conObj.prepareStatement(query);

        ps.setInt(1, idProd);
        result = stObj.executeQuery(query);
        pName = result.getString("nombre");

        //conObj.close();
        return pName;
    }

    public int prodId (String pName)
            throws SQLException {
        int pId = -1;
        query = "SELECT idproducto FROM PRODUCTOS WHERE nombre = '"+pName+"'";

        result = stObj.executeQuery(query);
        if (result.next()) {
            pId = result.getInt(1);
        }

        //conObj.close();
        return pId;
    }

    public int maxVtaId ()
            throws SQLException {
        int vMaxId = -1;
        query = "SELECT MAX(idventa) FROM VENTAS";
        result = stObj.executeQuery(query);
        if (result.next()) {
            vMaxId = result.getInt(1);
        }
        System.out.println("maximo id venta: "+vMaxId);
        //conObj.close();
        return vMaxId;
    }
    public int maxPrVtaId ()
            throws SQLException {
        int vMaxId = -1;
        query = "SELECT MAX(idproducto_venta) FROM PRODUCTO_VENTA";
        result = stObj.executeQuery(query);
        if (result.next()) {
            vMaxId = result.getInt(1);
        }
        System.out.println("maximo id prod_venta: "+vMaxId);
        //conObj.close();
        return vMaxId;
    }
    public void registrarProdVta(int idProd,int idVta, float precio,String descripcion)
            throws SQLException {
        int maxId = maxPrVtaId ()+1;
        query = "INSERT INTO PRODUCTO_VENTA(idproducto_venta,idproducto,idventa,precio,descripcion) " +
                                "VALUES("+maxId+",?,?,?,?)";

        ps = conObj.prepareStatement(query);

        ps.setInt(1,idProd);
        ps.setInt(2,idVta);
        ps.setFloat(3,precio);
        ps.setString(4,descripcion);

        ps.execute();
       // conObj.close();
    }
    /**
     * verifica la existencia del producto.
     * */
    public int prodStock (String nombreProd)
            throws SQLException {
        int stock = 0;
        query = "SELECT existencia FROM PRODUCTOS WHERE nombre = ?";
        ps = conObj.prepareStatement(query);

        ps.setString(1,nombreProd);
        result = stObj.executeQuery(query);
        stock = result.getInt("existencia");

        //conObj.close();
        return stock;
    }
    /**
     * regresa el producto y sus columnas de acuerdo al nombre.
     * */
    public Producto prodData (String nombreProd)
            throws SQLException {
        result = null;
        run();
        Producto producto = new Producto();
        query = "SELECT * FROM PRODUCTOS WHERE nombre = '"+nombreProd+"'";
        stObj = conObj.createStatement();

        result = stObj.executeQuery(query);
        while (result.next()) {
            System.out.println(result.getString("nombre"));
            System.out.println(result.getInt("tipo"));

            producto.setNombre(result.getString("nombre"));
            producto.setTipo(result.getInt("tipo"));
            producto.setDescripcion(result.getString("descripcion"));
            producto.setPrecio(result.getFloat("precio"));
            producto.setExistencia(result.getInt("existencia"));
        }
        //conObj.close();
        return producto;
    }
    /**
     *
     * */
    public void updateProds(int stock, String prodName)
            throws SQLException {

        query = "UPDATE PRODUCTOS SET " +
                              "existencia = ? "+
                              "WHERE nombre = ?";
        ps = conObj.prepareStatement(query);

        ps.setInt(1,stock-1);
        ps.setString(2,prodName);

        ps.executeUpdate();
        conObj.close();
    }

/**
 * UPDATE `sql10236271`.`PRODUCTOS`
 * SET
 * `existencia` = (SELECT MAX(`PRODUCTOS`.`existencia`)-1)
 * WHERE `idproducto` = 1;
 */
































    public int validateUser(String user, String pass,String role) throws Exception {
        int rol = -1;
        System.out.println(role);
        switch (role) {
            case "Admin": rol = 1;
                break;
            case "Gerente": rol = 2;
                break;
            case "Medico": rol = 3;
                break;
            case "N/A": rol = 4;
                break;
        }
        System.out.println(rol);
        String query = "select * from Usuario where username = ? and pass = ? and role = ?";
        ps = (PreparedStatement) conObj.prepareStatement(query);
        ps.setString(1,user);
        ps.setString(2,pass);
        ps.setInt(3, rol);
        ResultSet rs = ps.executeQuery();

        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
}
