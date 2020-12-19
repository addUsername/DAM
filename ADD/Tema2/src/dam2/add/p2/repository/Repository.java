package dam2.add.p2.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dam2.add.p2.entities.Persona;

/**
 * @author SERGI
 *
 */
public class Repository {

	private final String FILENAME = "personas.dat";
	private final int MAXSTOREDOBJECTS = 101;
	
	private List<Persona> personas;
	
	/**
	 * REQUISITO 2
	 */
	public Repository() {
		this.personas = loadDB();
	}
	public List<Persona>getPersonas() {
		return personas;
	}

	private List<Persona> loadDB() {

		File file = new File(FILENAME);
		List <Persona> toReturn = new ArrayList<Persona>();
		
		if(!file.exists())
			try {
				//prop not needed
				file.createNewFile();
				return toReturn;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
			while(true) {
				toReturn.add((Persona) ois.readObject());
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} 
		return toReturn;
	}

	public void add(Persona pers) {		
		if(personas.size()<MAXSTOREDOBJECTS ) personas.add(pers);		
	}
	public void save() {
		
		try {
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(FILENAME));			
			for(Persona pers: personas) {
				ois.writeObject(pers);
			}
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
