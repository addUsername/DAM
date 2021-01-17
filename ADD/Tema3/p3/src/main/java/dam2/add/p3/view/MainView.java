package dam2.add.p3.view;

import dam2.add.p3.interfaces.MainControllerViewImpl;
import dam2.add.p3.interfaces.MainViewImpl;

/**
 *
 * @author SERGI
 *
 */
public class MainView implements MainViewImpl {

	MainControllerViewImpl controller;

	public MainView(MainControllerViewImpl controller) {
		super();
		this.controller = controller;
	}

	public void showMenu() {

	}

}
