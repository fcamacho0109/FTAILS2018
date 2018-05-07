package module.database;
/**
 *
 * */
import module.empleados.Empleado;
import module.pacientes.Paciente;
import module.productos.Producto;
import module.recetas.Receta;
import module.ventas.Venta;

import java.sql.*;
import java.util.ArrayList;

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
    public void registrarVenta(float subtotal, float iva, float total, Date fecha, String descripcion)
            throws SQLException {
        String subquery = "SELECT MAX(idventa)+1";
        query = "INSERT INTO VENTAS (idventa,subtotal,iva,total,fecha,descripcion) " +
                "VALUES ("+subquery+",?,?,?,?,?)";
        ps = conObj.prepareStatement(query);

        ps.setFloat(2,subtotal);
        ps.setFloat(3,iva);
        ps.setFloat(4,total);
        ps.setDate(5,fecha);
        ps.setString(6,descripcion);
        ps.execute();

        conObj.close();
    }

    public String prodName (int idProd)
            throws SQLException {
        String pName = "";
        query = "SELECT nombre FROM PRODUCTOS WHERE idproducto = ?";
        ps = conObj.prepareStatement(query);

        ps.setInt(1, idProd);
        result = stObj.executeQuery(query);
        pName = result.getString("nombre");

        conObj.close();
        return pName;
    }

    public void registrarProdVta(int idProd,int idVta, float precio,String descripcion)
            throws SQLException {
        String subquery = "SELECT MAX(idproducto_venta)+1";
        query = "INSERT INTO PRODUCTO_VENTA(idproducto_venta,idproducto,idventa,precio,descripcion) " +
                                "VALUES("+subquery+",?,?,?,?)";

        ps = conObj.prepareStatement(query);

        ps.setInt(2,idProd);
        ps.setInt(3,idVta);
        ps.setFloat(4,precio);
        ps.setString(5,descripcion);

        ps.execute();
        conObj.close();
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

        conObj.close();
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
        conObj.close();
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

    public ArrayList<Empleado> listaEmpleados() throws SQLException {
        ArrayList<Empleado> empleadoArrayList = new ArrayList<>();
        Empleado empleados;
        query = "SELECT * FROM EMPLEADOS";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);

        while (result.next()) {
            empleados = new Empleado();
            empleados.setNombre(result.getString("nombre"));
            empleados.setTelefono(result.getString("telefono"));
            empleados.setDomicilio(result.getString("domicilio"));
            empleados.setRol(result.getString("rol"));


            empleadoArrayList.add(empleados);
        }

        return empleadoArrayList;
    }

    public ArrayList<Paciente> listaPacientes() throws SQLException {
        ArrayList<Paciente> pacienteArrayList = new ArrayList<>();
        Paciente pacientes;
        query = "SELECT * FROM PACIENTES";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);

        while (result.next()) {
            pacientes = new Paciente();
            pacientes.setNombre(result.getString("nombre"));
            pacientes.setMascota(result.getString("mascota"));
            pacientes.setDomicilio(result.getString("domicilio"));



            pacienteArrayList.add(pacientes);
        }

        return pacienteArrayList;
    }

    public ArrayList<Receta> listaRecetas() throws SQLException {
        ArrayList<Receta> recetaArrayList = new ArrayList<>();
        Receta recetas;
        query = "SELECT MEDICOS.nombre AS medico,PACIENTES.nombre AS paciente,PRODUCTOS.nombre,cantidad,descripcion_med,fecha_receta\n" +
                "FROM RECETAS\n" +
                "INNER JOIN MEDICOS\n" +
                "ON RECETAS.idmedico = MEDICOS.idmedico \n" +
                "INNER JOIN PACIENTES\n" +
                "ON RECETAS.idpaciente = PACIENTES.idpaciente \n" +
                "INNER JOIN PRODUCTOS\n" +
                "ON RECETAS.idproducto = PRODUCTOS.idproducto  ";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);

        while (result.next()) {
            recetas = new Receta();
            recetas.setNombreMedico(result.getString(1));
            recetas.setNombrePaciente(result.getString(2));
            recetas.setMedicamento(result.getString(3));
            recetas.setCantidad(result.getString(4));
            recetas.setDescripcion(result.getString(5));
            recetas.setFecha(result.getString(6));



            recetaArrayList.add(recetas);
        }

        return recetaArrayList;
    }


    public ArrayList<String> pacientes() throws SQLException {
        //run();
       ArrayList<String> pacientesList = new ArrayList<>();
        query = "SELECT * FROM PACIENTES";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);
        while (result.next()) {
            pacientesList.add(result.getString("nombre"));
        }

        conObj.close();
        return pacientesList;
    }

    public ArrayList<String> pacientesDatos(String nombre) throws SQLException {
        //run();
        ArrayList<String> pacientesList = new ArrayList<>();
        query = "SELECT * FROM PACIENTES WHERE nombre = '" + nombre + "'";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);
        while (result.next()) {
            pacientesList.add(result.getString("nombre"));
            pacientesList.add(result.getString("mascota"));
            pacientesList.add(result.getString("domicilio"));
        }

        conObj.close();
        return pacientesList;
    }


    public ArrayList<String> usuarios() throws SQLException {
        //run();
        ArrayList<String> usuariosList = new ArrayList<>();
        query = "SELECT * FROM EMPLEADOS";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);
        while (result.next()) {
            usuariosList.add(result.getString("nombre"));
        }

        conObj.close();
        return usuariosList;
    }

    public ArrayList<String> usuariosDatos(String nombre) throws SQLException {
        //run();
        ArrayList<String> usuariosList = new ArrayList<>();
        query = "SELECT * FROM EMPLEADOS WHERE nombre = '" + nombre + "'";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);
        while (result.next()) {
            usuariosList.add(result.getString("nombre"));
            usuariosList.add(result.getString("telefono"));
            usuariosList.add(result.getString("domicilio"));
        }

        conObj.close();
        return usuariosList;
    }

    public ArrayList<String> recetas() throws SQLException {
        //run();
        ArrayList<String> recetasList = new ArrayList<>();
        query = "SELECT MEDICOS.nombre AS medico,PACIENTES.nombre AS paciente,PRODUCTOS.nombre,cantidad,descripcion_med,fecha_receta\n" +
                "FROM RECETAS\n" +
                "INNER JOIN MEDICOS\n" +
                "ON RECETAS.idmedico = MEDICOS.idmedico \n" +
                "INNER JOIN PACIENTES\n" +
                "ON RECETAS.idpaciente = PACIENTES.idpaciente \n" +
                "INNER JOIN PRODUCTOS\n" +
                "ON RECETAS.idproducto = PRODUCTOS.idproducto  ";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);
        while (result.next()) {
            recetasList.add(result.getString("medico"));
        }

        conObj.close();
        return recetasList;
    }

    public ArrayList<String> recetasDatos(String nombre) throws SQLException {
        //run();
        ArrayList<String> recetasList = new ArrayList<>();
        query = "SELECT MEDICOS.nombre AS medico,PACIENTES.nombre AS paciente,PRODUCTOS.nombre,cantidad,descripcion_med,fecha_receta\n" +
                "FROM RECETAS\n" +
                "INNER JOIN MEDICOS\n" +
                "ON RECETAS.idmedico = MEDICOS.idmedico \n" +
                "INNER JOIN PACIENTES\n" +
                "ON RECETAS.idpaciente = PACIENTES.idpaciente \n" +
                "INNER JOIN PRODUCTOS\n" +
                "ON RECETAS.idproducto = PRODUCTOS.idproducto  " +
                "WHERE MEDICOS.NOMBRE = '" + nombre + "'";
        stObj = conObj.createStatement();
        result = stObj.executeQuery(query);
        while (result.next()) {
            recetasList.add(result.getString(3));
            recetasList.add(result.getString(4));
            recetasList.add(result.getString(5));
        }

        conObj.close();
        return recetasList;
    }

    public void updateUser(String telefono, String domicilio, String nombre)
            throws SQLException {

        query = "UPDATE EMPLEADOS\n" +
                "SET\n" +
                "telefono = ?,\n" +
                "domicilio = ?\n" +
                "WHERE nombre = ?";
        ps = conObj.prepareStatement(query);

        ps.setString(1,telefono);
        ps.setString(2,domicilio);
        ps.setString(3,nombre);

        ps.executeUpdate();
        conObj.close();
    }

    public void updatePacientes(String mascota, String domicilio, String nombre)
            throws SQLException {

        query = "UPDATE PACIENTES\n" +
                "SET\n" +
                "mascota = ?,\n" +
                "domicilio = ?\n" +
                "WHERE nombre = ?";
        ps = conObj.prepareStatement(query);

        ps.setString(1,mascota);
        ps.setString(2,domicilio);
        ps.setString(3,nombre);

        ps.executeUpdate();
        conObj.close();
    }

    public void updateRecetas(String nombreMedicamento, int cantidad,String descripcionReceta,String nombreMedico)
            throws SQLException {

        int idProducto = getProductobyId(nombreMedicamento);
        int idNombreMedico = getMedicobyName(nombreMedico);

        query = "UPDATE RECETAS\n" +
                "SET\n" +
                "idproducto = ?,\n" +
                "cantidad = ?,\n" +
                "descripcion_med = ?\n" +
                "WHERE idmedico = ?";
        ps = conObj.prepareStatement(query);

        ps.setInt(1,idProducto);
        ps.setInt(2,cantidad);
        ps.setString(3,descripcionReceta);
        ps.setInt(4,idNombreMedico);

        ps.executeUpdate();
        conObj.close();
    }

    public int getMedicobyName(String nombre) throws SQLException {
        int idMedico = -1;

        query = "SELECT idmedico FROM MEDICOS WHERE nombre = '" + nombre + "'";

        result = stObj.executeQuery(query);
        if(result.next()){
            idMedico = result.getInt(1);
        }
        return idMedico;
    }

    public int getPacientebyId(String nombre) throws SQLException {
        int idPaciente = -1;

        query = "SELECT idpaciente FROM PACIENTES WHERE nombre = '" + nombre + "'";

        result = stObj.executeQuery(query);
        if(result.next()){
            idPaciente = result.getInt(1);
        }
        return idPaciente;
    }

    public int getProductobyId(String nombre) throws SQLException {
        int idProducto = -1;

        query = "SELECT idproducto FROM PRODUCTOS WHERE nombre = '" + nombre + "'";

        result = stObj.executeQuery(query);
        if(result.next()){
            idProducto = result.getInt(1);
        }
        return idProducto;
    }


    public void insertarPacientes(String nombre,String mascota, String domicilio)
            throws SQLException {

        int pacientId = getIdPAcienteByNombre();
        String subquery = "SELECT MAX(idpaciente)+1  FROM PACIENTES";
        query = "INSERT INTO PACIENTES(nombre,mascota,domicilio,idpaciente) " +
                "VALUES(?,?,?,?)";

        ps = conObj.prepareStatement(query);

        ps.setString(1,nombre);
        ps.setString(2,mascota);
        ps.setString(3,domicilio);
        ps.setInt(4,pacientId);


        ps.execute();
        conObj.close();
    }


    public int getIdPAcienteByNombre() throws SQLException {

        int pacienteId = -1;

        query ="SELECT MAX(idpaciente)+1 FROM PACIENTES";
        result = stObj.executeQuery(query);
        if(result.next()){
            pacienteId = result.getInt(1);
        }
        return pacienteId;
    }


    public void insertarUsuarios(String nombre,String telefono, String domicilio,String rol)
            throws SQLException {

        int userId = getIdUserMax();
        query = "INSERT INTO EMPLEADOS(idempleado,nombre,telefono,domicilio,rol) " +
                "VALUES(?,?,?,?,?)";

        ps = conObj.prepareStatement(query);

        ps.setInt(1,userId);
        ps.setString(2,nombre);
        ps.setString(3,telefono);
        ps.setString(4,domicilio);
        ps.setString(5,rol);


        ps.execute();
        conObj.close();
    }


    public int getIdUserMax() throws SQLException {

        int userId = -1;

        query ="SELECT MAX(idempleado)+1 FROM EMPLEADOS";
        result = stObj.executeQuery(query);
        if(result.next()){
            userId = result.getInt(1);
        }
        return userId;
    }

    public void insertarRecetas(String nombreMedico,String nombrePaciente, String nombreProducto,String descripcion,int cantidad,Date fecha_receta)
            throws SQLException {

        int recetaId = getIdRecetaMax();
        int medicoId = getMedicobyName(nombreMedico);
        int pacienteId = getPacientebyId(nombrePaciente);
        int productoId = getProductobyId(nombreProducto);

        query = "INSERT INTO RECETAS(idreceta,idmedico,idpaciente,idproducto,cantidad,descripcion_med,fecha_receta) " +
                "VALUES(?,?,?,?,?,?,?)";

        ps = conObj.prepareStatement(query);

        ps.setInt(1,recetaId);
        ps.setInt(2,medicoId);
        ps.setInt(3,pacienteId);
        ps.setInt(4,productoId);
        ps.setInt(5,cantidad);
        ps.setString(6,descripcion);
        ps.setDate(7,fecha_receta);


        ps.execute();
        conObj.close();
    }

    public int getIdRecetaMax() throws SQLException {

        int recetaId = -1;

        query ="SELECT MAX(idreceta)+1 FROM RECETAS";
        result = stObj.executeQuery(query);
        if(result.next()){
            recetaId = result.getInt(1);
        }
        return recetaId;
    }
}
