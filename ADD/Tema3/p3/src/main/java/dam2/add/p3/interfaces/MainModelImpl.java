package dam2.add.p3.interfaces;

import dam2.add.p3.entities.Pregunta;

/**
 *
 * @author SERGI
 *
 */
public interface MainModelImpl {

	void startGame(int[] config);

	Pregunta getQuestion();

	void addQuestion(String[] data);

	void importQuestions(String path);

	String[] seeText(boolean b);

	boolean validate(int ans);

	void setUsername(String nextLine);

	void createPDF();

	String[] finishGame();

}
