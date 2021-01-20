package dam2.add.p3.controller;

import java.io.InputStream;
import java.io.PrintStream;

import dam2.add.p3.entities.Pregunta;
import dam2.add.p3.interfaces.MainModelImpl;
import dam2.add.p3.interfaces.MainViewImpl;
import dam2.add.p3.model.MainModel;
import dam2.add.p3.view.MainView;

/**
 *
 * @author SERGI
 *
 */
public class MainController {

	private MainModelImpl model;
	private MainViewImpl view;
	private Pregunta currentPregunta;
	private int[] config = { 3, 1, 0 };

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
	public MainController() {
		model = new MainModel();
		view = new MainView();
		view.showInit();
	}

	public boolean start() {
		mainMenuOptionSelected(view.showMenu());
		return false;
	}

	// MainControllerViewImpl
	public void mainMenuOptionSelected(int i) {
		String[] data;
		switch (i) {
		case 0:
			model.startGame(config);
			currentPregunta = model.getQuestion();

			while (currentPregunta != null) {
				view.showQuestion(currentPregunta.getQuestion());
				int ans = view.showAnswers(currentPregunta.getOptions());
				if (model.validate(ans)) {
					view.correct();
				} else {
					view.incorrect();
				}
				currentPregunta = model.getQuestion();
			}
			data = model.finishGame();
			model.setUsername(view.getName());
			endMenuOptionSelected(view.showEndMenu(data));
			break;
		case 1:
			// añadir preg
			data = view.requestQuestionFile();
			model.addQuestion(data);
			break;
		case 2:
			// importar preguntas
			String path = view.requestImportFile();
			model.importQuestions(path);
			break;
		case 3:
			// ver records
			view.showRecords(model.seeText(true));
			break;
		case 4:
			view.showWiki(model.seeText(false));
			// Instrucciones
			break;
		case 5:
			// Config
			config = view.showConfig();
			break;
		default:
			// 0 salir
			view.showClose();
			System.exit(0);
			return;
		}
		return;
	}

	public void endMenuOptionSelected(int input) {

		switch (input) {
		case 0:
			// menu ppal
			break;
		case 1:
			// ver records
			view.showRecords(model.seeText(true));
			break;
		case 2:
			// genenrar informe
			model.createPDF();
			view.showPDF();
			break;
		default:
			System.exit(0);
			break;
		}

	}

	public void fileText(String[] lines, boolean b) {
		if (b) {
			view.showRecords(lines);
		} else {
			view.showWiki(lines);
		}
	}

	public void sendError(String string) {
		// TODO Auto-generated method stub

	}

	/**
	 *
	 */

	public void wasCorrect(boolean b) {
		if (b) {
			view.correct();
		} else {
			view.incorrect();
		}
		model.getQuestion();
	}

	public void endGame(String[] data) {
		view.showEndMenu(data);
	}

	public void setUsername(String nextLine) {
		model.setUsername(nextLine);

	}

}
