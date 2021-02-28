package dam2.add.p4.interfaces;

import dam2.add.p4.entities.Pregunta;

/**
 * Metodos implementados por MainModel
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
