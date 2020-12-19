package dam2.add.p2.entities;


/**
 * @author SERGI
 *
 */
public class Alumno extends Persona {
	
	private String matricula;

	public Alumno(String name, String cumpleanos, String matricula, String grupo) {
		super(name, cumpleanos, grupo);
		this.matricula = matricula;
	}
	public String getMatricula() {
		return matricula;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ALUMNO "+getName()+", "+prettyCalendar()+", "+getMatricula()+", "+getGrupo()+"\n";
	}
	
}
