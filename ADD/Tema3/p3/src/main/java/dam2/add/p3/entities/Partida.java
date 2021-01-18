package dam2.add.p3.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 *
 * @author SERGI
 *
 *         esto se puede ir al paquete model easy
 */
public class Partida {

	private long id;
	private String userName;
	private Calendar calendar;
	private List<Pregunta> preguntas;
	private int score;

	public Partida() {
		calendar = Calendar.getInstance();
		id = new Random().nextLong();
		preguntas = new ArrayList<Pregunta>(10);
		score = 0;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public long getId() {
		return id;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * Sums int to score, score can not be lower than 0;
	 * 
	 * @param points
	 */
	public void addPoints(int points) {
		score += points;
		score = (score < 0) ? 0 : score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
