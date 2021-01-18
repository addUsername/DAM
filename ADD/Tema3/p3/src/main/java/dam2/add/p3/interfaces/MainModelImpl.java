package dam2.add.p3.interfaces;

import java.io.InputStream;

/**
 *
 * @author SERGI
 *
 */
public interface MainModelImpl {

	void startGame();

	void addQuestion(InputStream inputStream);

	void importQuestions(InputStream inputStream);

	void seeRecords();

	void seeWiki();

}
