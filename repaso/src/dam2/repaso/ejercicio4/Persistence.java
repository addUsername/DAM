package dam2.repaso.ejercicio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Persistence {

	private Connection con;
	private ResultSet rs;
	private Statement query;
	
	final private String driver = "jdbc:mysql://";
	final private String hostname = "127.0.0.1:3308/";
	final private String db = "agenda";
	final private String user = "root";
	final private String pass = "root";
	
	
	public Persistence() {
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Estableciendo conexion..");
	        con = DriverManager.getConnection(driver+hostname+db, user, pass);	        
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if(con!=null)System.out.println("Conectado con exito");
	}
	
	public void create(Contacto contacto) {
		
		HashMap<String,String> con = contacto.toHashMap();
		ResultSet rs = query.executeQuery("INSERT INTO contactos (nombre, telefono, casa) VALUES("+con[]);
		
	}
	
}
