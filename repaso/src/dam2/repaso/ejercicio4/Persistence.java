package dam2.repaso.ejercicio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;

public class Persistence {

	private Connection con;
	private ResultSet rs;
	private Statement query;
	
	final private String driver = "jdbc:mysql://";
	final private String hostname = "127.0.0.1:3306/";
	final private String db = "agenda";
	final private String user = "root";
	final private String pass = "root";	
	
	public Persistence() {		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Estableciendo conexion..");
		    System.out.println(driver+hostname+db);
	        con = DriverManager.getConnection(driver+hostname+db, user, pass);
	        query = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			printMessage(e.getMessage());
		}
        if(con!=null)System.out.println("Conectado con exito");
	}	
	public void find(String name) {
		try {
		    System.out.println(String.format("%10s %20s %10s %5s %5s %5s %5s", "Nombre", "|", "Telefono", "|", "Casa", "|", "Añadido"));
		    System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
			
		    rs = query.executeQuery("SELECT * FROM contactos WHERE nb = '"+name+"';");
		    if(!rs.next()) {
		    	printMessage("Usuario con nombre "+name+" no encontrado");
		    	return;
		    }
		    rs.beforeFirst();
		    
			while(rs.next()) {
				System.out.println(String.format("%10s %20s %10s %5s %5s %5s %5s",
								rs.getString(2), "|", rs.getString(3), "|", rs.getString(4),"|",rs.getString(5)));				
			}			
		} catch (SQLException e) {
			printMessage(e.getMessage());
		}		
	}
	public void findAll() {
		try {
		    System.out.println(String.format("%10s %20s %10s %5s %5s %5s %5s", "Nombre", "|", "Telefono", "|", "Casa", "|", "Añadido"));
		    System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
			
		    rs = query.executeQuery("SELECT * FROM contactos;");
		    if(!rs.next()) {
		    	printMessage("Agenda vacia");	
		    	return;
		    }
		    rs.beforeFirst();
			while(rs.next()) {
				System.out.println(String.format("%10s %20s %10s %5s %5s %5s %5s",
								rs.getString(2), "|", prettyPhone(rs.getString(3)), "|", rs.getString(4),"|", prettyDate(rs.getDate(5))));				
			}
		} catch (SQLException e) {
			printMessage(e.getMessage());
		}		
	}
	public void delete(String name) {
		
		try {
			int bol = query.executeUpdate("DELETE FROM contactos WHERE nb='"+name+"';");
			if(bol == 1) printMessage(name+" borrado");
		} catch (SQLException e) {
			printMessage(e.getMessage());
		}		
	}
	public void create(Contacto contacto) {
		
		try {
			int bol = query.executeUpdate("INSERT INTO contactos (nb, phone, home) VALUES"+contacto.toString()+";");
			if(bol == 1) printMessage("Contacto guardado");
		} catch (SQLException e) {
			printMessage(e.getMessage());
		}		
	}
	private void printMessage(String message) {
		char [] line = new char[message.length() + 2];
		Arrays.fill(line, '-');
		System.out.println(line);
		System.out.println("| "+message+" |");
		System.out.println(line);
	}
	private String prettyPhone(String phone) {
		return ((String) phone).substring(0, 3) + "-" + ((String) phone).substring(3);
	}
	private String prettyDate(Date date) {
		return date.toString();
	}
	
}
