package dam2.add.p3.model;

import dam2.add.p3.interfaces.MainControllerModelImpl;
import dam2.add.p3.interfaces.MainModelImpl;

/**
 *
 * @author SERGI
 *
 */
public class MainModel implements MainModelImpl {

	private final String RESFOLDER = "";
	private final String QUESTIONNAME = "pregunta";
	private final String RESULTSNAME = "records";

	private MainControllerModelImpl controller;

	public MainModel(MainControllerModelImpl controller) {
		super();
		this.controller = controller;
	}

	public void startGame() {
		// TODO Auto-generated method stub

	}

}
