package dam2.add.p4;

import dam2.add.p4.controller.MainController;

/**
 *
 * @author SERGI
 *
 */
public class Main {
	public static void main(String[] args) {

		// MainController controller = new MainController(System.in);
		MainController controller = new MainController();
		while (true) {
			controller.start();
		}
	}
}
