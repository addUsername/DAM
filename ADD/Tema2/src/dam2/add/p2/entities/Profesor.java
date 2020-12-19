package dam2.add.p2.entities;

/**
 * @author SERGI
 *
 */
public class Profesor extends Persona {
	
	private String materia;
	
	public Profesor(String name, String cumpleanos, String materia, String grupo) {
		super(name, cumpleanos, grupo);
		this.materia = materia;
	}
	public String getMateria() {
		return materia;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PROFESOR "+getName()+", "+prettyCalendar()+", "+getMateria()+", "+getGrupo()+"\n";
	}
	
}
