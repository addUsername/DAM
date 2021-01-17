package dam2.add.p3.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author SERGI
 *
 *         esto se puede ir al paquete model easy
 */
public class Partida {

	private String userName;
	private Calendar calendar;
	private List<Pregunta> preguntas;
	private int score;

	public Partida() {
		calendar = Calendar.getInstance();
		preguntas = new ArrayList<Pregunta>(10);
	}

}
