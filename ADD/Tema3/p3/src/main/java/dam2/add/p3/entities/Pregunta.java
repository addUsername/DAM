package dam2.add.p3.entities;

import java.util.Random;

/**
 * REQUISITO 2
 *
 * @author SERGI
 *
 */
public class Pregunta {

	private String question;
	private long id;
	private String[] options;
	private int answer;

	public Pregunta() {
		id = new Random().nextLong();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public int getCorrect() {
		return answer;
	}

	public void setCorrect(int answer) {
		this.answer = answer;
	}

	public long getId() {
		return id;
	}
}
