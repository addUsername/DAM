package dam2.add.p3.controller;

import dam2.add.p3.interfaces.MainControllerModelImpl;
import dam2.add.p3.interfaces.MainControllerViewImpl;
import dam2.add.p3.interfaces.MainModelImpl;
import dam2.add.p3.interfaces.MainViewImpl;

public class MainController implements MainControllerViewImpl, MainControllerModelImpl {

	private MainModelImpl model;
	private MainViewImpl view;

	public MainController(MainModelImpl model, MainViewImpl view) {
		super();
		this.model = model;
		this.view = view;
	}

	public void start() {
		view.showMenu();
	}

	// MainControllerViewImpl
	public void mainMenuptionSelected(int i) {
		switch (i) {
		case 1:
			model.startGame();
			// jugar
			break;
		case 2:
			// añadir preg
			break;
		case 3:
			// importar preguntas
			break;
		case 4:
			// ver records
			break;
		case 5:
			// Instrucciones
			break;
		default:
			// 0 salir
			System.exit(0);
			return;
		}

		return;

	}

}
