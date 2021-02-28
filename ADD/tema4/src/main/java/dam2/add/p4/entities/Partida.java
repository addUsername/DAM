package dam2.add.p4.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @author SERGI
 */
public class Partida {

	public int MAXPREGUNTAS = 10;
	public int POINTSCORRECTANS = 1;
	public int POINTSWRONGANS = 0;

	private long id;
	private String userName;
	private Calendar calendar;
	private List<Pregunta> preguntas;
	private int score;
	private List<String> userAns;

	public Partida() {
		calendar = Calendar.getInstance();
		id = new Random().nextLong();
		score = 0;
		preguntas = new ArrayList<Pregunta>(MAXPREGUNTAS);
		userAns = new ArrayList<String>(MAXPREGUNTAS);
	}

	public void setConfig(int num, int sum, int sus) {
		MAXPREGUNTAS = num;
		POINTSCORRECTANS = sum;
		POINTSWRONGANS = sus;
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

	public List<String> getUserAns() {
		return userAns;
	}

	public long getId() {
		return id;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void addPoints() {
		score += POINTSCORRECTANS;
	}

	public void removePoints() {
		score -= POINTSWRONGANS;
		if (score < 0) {
			score = 0;
		}
	}

	public int getScore() {
		return score;
	}

	public void setAns(String string) {
		userAns.add(string);
	}
}
