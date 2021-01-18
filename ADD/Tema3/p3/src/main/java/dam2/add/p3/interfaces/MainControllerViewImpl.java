package dam2.add.p3.interfaces;

import java.io.InputStream;

/**
 *
 * @author SERGI
 *
 */
public interface MainControllerViewImpl {

	void mainMenuOptionSelected(int i);

	void uploadQuestion(InputStream in);

	void importFile(InputStream in);
}
