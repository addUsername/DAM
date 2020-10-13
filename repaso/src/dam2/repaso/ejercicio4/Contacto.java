package dam2.repaso.ejercicio4;

import java.sql.ResultSet;
import java.util.HashMap;

public class Contacto {
	
	private String name;
	private String phone;
	private String home;
	
	public Contacto() {}
	
	public Contacto (ResultSet rs) {
		//SQL to pojo
	}
	
	public Contacto(String name, String phone, String home) {
		super();
		this.name = name;
		this.phone = phone;
		this.home = home;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
	
	public HashMap<String, String> toHashMap() {
		
		HashMap<String, String> toReturn = new HashMap<String, String>();
		toReturn.put("Name",getName());
		toReturn.put("Phone",getPhone());
		toReturn.put("Home",getHome());		
		return toReturn;
	}
	

}
