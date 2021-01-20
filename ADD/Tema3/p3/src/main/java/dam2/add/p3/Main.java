package dam2.add.p3;

import dam2.add.p3.controller.MainController;

/**
 *
 * @author SERGI
 *
 */
public class Main {

	/**
	 * TODO Launch controller as different process allowing act as a kinda server.
	 * Each process should open it's own terminal (System.setIn()) TODO OCR
	 * trivial.pdf, Save Python project in resources/pre/ocrToXml.py fo
	 * documentation purposes Run once and forget, NOT ENV provided TODO The actual
	 * app.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// MainController controller = new MainController(System.in);
		MainController controller = new MainController();
		while (true) {
			controller.start();
		}
	}
}
