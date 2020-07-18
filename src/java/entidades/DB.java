package entidades;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static DB INSTANCE = null;
    //private static String LABASE = "jdbc:hsqldb:file:"+System.getProperty("user.home")+"/personas.hsqldb";
    private static String LABASE = "jdbc:mysql://localhost/ecommerceGrupal";
    private static String LABASEUSUARIO = "cine";  // "root";
    private static String LABASECLAVE = "cine";    //"root";
    
    public static DB getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new DB();
        }
        return INSTANCE;
    }
    private DB() throws ClassNotFoundException,
            IOException, SQLException {
    }

    public Connection getConnection() throws ClassNotFoundException,
            IOException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(LABASE, LABASEUSUARIO, LABASECLAVE);
    }
}