package dam2.add.p2.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * REQUISITO 1
 * @author SERGI
 *
 */
public abstract class Persona implements Serializable {

	protected final String name;
	protected final Calendar cumpleanos;
	protected final String grupo;
	
	public Persona(String name, String cumpleanos, String grupo) {
		this.name = name;
		this.cumpleanos = formatCumpleanos(cumpleanos);
		this.grupo = grupo;
	}
	private Calendar formatCumpleanos(String cumpleanos) {
		String [] dates = cumpleanos.split(" ");
		return new GregorianCalendar(1970,Integer.parseInt(dates[0]),Integer.parseInt(dates[1]));
	}
	public String getName() {
		return name;
	}
	public Calendar getCumpleanos() {
		return cumpleanos;
	}
	public String prettyCalendar() {
		return cumpleanos.get(Calendar.DAY_OF_MONTH)+" del "+(cumpleanos.get(Calendar.MONTH));
	}
	public String getGrupo() {
		return grupo;
	}
	public abstract String toString();
}
