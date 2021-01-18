package dam2.add.p3.model;

import java.io.InputStream;

import dam2.add.p3.entities.Partida;
import dam2.add.p3.interfaces.MainControllerModelImpl;
import dam2.add.p3.interfaces.MainModelImpl;

/**
 *
 * @author SERGI
 *
 */
public class MainModel implements MainModelImpl {

	private final String RESFOLDER = "./resources";
	private final String QUESTIONSXML = "preguntas.xml";
	private final String RECORDSTXT = "records";
	private final String WIKITXT = "wiki.txt";

	private MainControllerModelImpl controller;

	private Partida currentGame;

	public MainModel(MainControllerModelImpl controller) {
		super();
		this.controller = controller;
	}

	/**
	 *
	 */
	public void startGame() {
		// TODO Auto-generated method stub
		currentGame = new Partida();

	}

	public void addQuestion(InputStream inputStream) {
		// TODO Auto-generated method stub

	}

	public void importQuestions(InputStream inputStream) {
		// TODO Auto-generated method stub

	}

	public void seeRecords() {
		String[] lines = RepositoryService.getLines(RESFOLDER + RECORDSTXT, true);
		if (lines != null) {
			controller.fileText(lines);
		}
		controller.sendError("File does not exists");
	}

	public void seeWiki() {
		String[] lines = RepositoryService.getLines(RESFOLDER + WIKITXT, false);
		if (lines != null) {
			controller.fileText(lines);
		}
		controller.sendError("File does not exists");
	}

}
