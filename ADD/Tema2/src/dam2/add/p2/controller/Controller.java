package dam2.add.p2.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dam2.add.p2.entities.Alumno;
import dam2.add.p2.entities.Persona;
import dam2.add.p2.repository.Repository;
import dam2.add.p2.ui.Ui;

/**
 * @author SERGI
 *
 */
public class Controller {

	private Repository repo;
	
	public Controller() {
		this.repo = new Repository();
	}
	/**
	 * REQUISITO 3
	 */
	public void anadirPersona() {
		String rol = Ui.askTypePersona();
		Persona pers;
		switch(rol) {
			case "Profesor":
				pers = Ui.askForProfesor();
				break;
			default:
				pers = Ui.askForAlumno(makeId());
				break;
		}
		repo.add(pers);
	}
	/**
	 * REQUISITO Autogenerar ID
	 */
	private String makeId() {
		return "M"+(repo.getPersonas().size()+1);
	}
	/**
	 * REQUISITO 4
	 */
	public void listarPersonas() {
		Ui.showMessage(repo.getPersonas().toString());		
	}
	/**
	 * REQUISITO 5
	 * Crea un objeto Calendar con la fecha indicada e itera sobre el ArrayList<Persona> comparando las fechas.
	 */
	public void felicitarPersona() {
		String[] date = Ui.askForDate();
		List<Persona> filteredPersonas = new ArrayList<Persona>();
		String type;
		
		Calendar bDay = new GregorianCalendar(1970,Integer.parseInt(date[0]),Integer.parseInt(date[1]));
		
		for(Persona pers: repo.getPersonas()) {
			
			if(bDay.equals(pers.getCumpleanos())) filteredPersonas.add(pers); 
		}
		for(Persona p: filteredPersonas) {
			type = (p instanceof Alumno)? "Alumno" : "Profesor";
			Ui.showMessage("FELICIDADES "+ type + " " + p.getName());
		}		
	}
	/**
	 * REQUISITO 6
	 */
	public void grabar() {
		repo.save();		
	}

}
