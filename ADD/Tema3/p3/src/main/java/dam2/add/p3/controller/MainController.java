package dam2.add.p3.controller;

import java.io.InputStream;
import java.io.PrintStream;

import dam2.add.p3.interfaces.MainControllerModelImpl;
import dam2.add.p3.interfaces.MainControllerViewImpl;
import dam2.add.p3.interfaces.MainModelImpl;
import dam2.add.p3.interfaces.MainViewImpl;
import dam2.add.p3.model.MainModel;
import dam2.add.p3.view.MainView;

/**
 *
 * @author SERGI
 *
 */
public class MainController implements MainControllerViewImpl, MainControllerModelImpl {

	private MainModelImpl model;
	private MainViewImpl view;

	/**
	 * For mockito
	 */
	public MainController(MainModelImpl model, MainViewImpl view, InputStream in, PrintStream out) {
		super();
		this.model = model;
		this.view = view;
		System.setIn(in);
		System.setOut(out);
	}

	/**
	 * For Main.main();
	 **/
	public MainController(InputStream in, PrintStream out) {
		model = new MainModel(this);
		view = new MainView(this);
		System.setIn(in);
		System.setOut(out);
	}

	public void start() {
		view.showMenu();
	}

	// MainControllerViewImpl
	public void mainMenuOptionSelected(int i) {
		switch (i) {
		case 1:
			model.startGame();
			// jugar
			break;
		case 2:
			// añadir preg
			view.requestQuestionFile();
			break;
		case 3:
			// importar preguntas
			view.requestImportFile();
			break;
		case 4:
			// ver records
			model.seeRecords();
			break;
		case 5:
			model.seeWiki();
			// Instrucciones
			break;
		default:
			// 0 salir
			System.exit(0);
			return;
		}
		return;
	}

	public void uploadQuestion(InputStream in) {
		// TODO Auto-generated method stub
		model.addQuestion(in);
	}

	public void importFile(InputStream in) {
		// TODO Auto-generated method stub
		model.importQuestions(in);

	}

	public void fileText(String[] lines) {
		// TODO Auto-generated method stub

	}

	public void sendError(String string) {
		// TODO Auto-generated method stub

	}

}
