package dam2.add.p3.interfaces;

/**
 *
 * @author SERGI
 *
 */
public interface MainViewImpl {

	int showMenu();

	String[] requestQuestionFile();

	String requestImportFile();

	void showQuestion(String question);

	int showAnswers(String[] posibleAnswers);

	void correct();

	void incorrect();

	int showEndMenu(String[] data);

	void showPDF();

	void showRecords(String[] lines);

	void showWiki(String[] lines);

	String getName();

	void showClose();

	void showInit();

	int[] showConfig();

	void processError();

	void proccesOK();

	int overwriteQuestions();

}
