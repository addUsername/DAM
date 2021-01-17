package dam2.add.p3.entities;

/**
 * Esto no se puede mover al paquete model porque view lo necesita
 * 
 * @author SERGI
 *
 */
public class Pregunta {

	private String question;
	private String[] options;
	private int correct;

	public Pregunta() {

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
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

}
