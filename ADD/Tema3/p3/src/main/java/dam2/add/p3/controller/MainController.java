package dam2.add.p3.controller;

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

	public void mainMenuOptionSelected(int i) {
		String[] data;
		Boolean bool;
		switch (i) {
		case 0:
			// empezar
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
			bool = model.addQuestion(data);
			if (bool == null || false) {
				view.processError();
			} else {
				view.proccesOK();
			}
			break;
		case 2:
			// importar preguntas
			String path = view.requestImportFile();
			bool = model.SaveImportedQuestions(path);
			if (bool) {

				int shouldOverwrite = view.overwriteQuestions();

				if (process(shouldOverwrite)) {
					view.proccesOK();
				} else {
					view.processError();
				}
			} else {
				view.processError();
			}
			break;
		case 3:
			// ver records
			view.showRecords(model.seeText(true));
			break;
		case 4:
			// Instrucciones
			view.showWiki(model.seeText(false));
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

	private boolean process(int shouldOverwrite) {

		boolean bool = false;
		switch (shouldOverwrite) {
		case 1:
			// append
			bool = model.writeImportedQuestions(false);
			break;
		case 2:
			// overwrite
			bool = model.writeImportedQuestions(true);
			break;
		case 3:
			// delete changes
			model = new MainModel();
		default:
			// in memory
			bool = true;
			break;
		}
		return bool;
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
			boolean bool = model.createPDF();
			if (bool) {
				view.proccesOK();
			} else {
				view.processError();
			}
			view.showPDF();
			break;
		default:
			view.showClose();
			System.exit(0);
			break;
		}

	}

}
