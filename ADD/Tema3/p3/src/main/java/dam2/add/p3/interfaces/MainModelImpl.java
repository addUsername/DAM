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

	Boolean addQuestion(String[] data);

	Boolean SaveImportedQuestions(String path);

	String[] seeText(boolean b);

	boolean validate(int ans);

	void setUsername(String nextLine);

	boolean createPDF();

	String[] finishGame();

	boolean writeImportedQuestions(boolean ShouldOverride);

}
