package dam2.add.p3;

import dam2.add.p3.controller.MainController;

/**
 *
 * @author SERGI
 *
 */
public class Main {

	public static void main(String[] args) {

		MainController controller = new MainController();
		while (true) {
			controller.start();
		}
	}
}
