package dam2.add.p3.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import com.itextpdf.text.DocumentException;

import dam2.add.p3.entities.Partida;
import dam2.add.p3.entities.Pregunta;
import dam2.add.p3.interfaces.MainModelImpl;

/**
 *
 * @author SERGI
 *
 */
public class MainModel implements MainModelImpl {

	private final String RESFOLDER = "src/main/resources";
	private final String QUESTIONSXML = "/preguntas.xml";
	private final String RECORDSTXT = "/records.txt";
	private final String WIKITXT = "/wiki.txt";

	private Partida currentGame;
	private Pregunta currentPregunta;
	private int index;

	public MainModel() {
		super();
	}

	public void startGame(int[] config) {
		currentGame = new Partida();
		currentGame.setConfig(config[0], config[1], config[2]);

		Pregunta[] all = UtilsXml.parseString(RepositoryService.readFileAsString(RESFOLDER + QUESTIONSXML));
		List<Pregunta> toReturn = new ArrayList<Pregunta>(currentGame.MAXPREGUNTAS);
		currentGame.setPreguntas(chooseQuestions(toReturn, all, currentGame.MAXPREGUNTAS));
		index = -1;
	}

	private List<Pregunta> chooseQuestions(List<Pregunta> toReturn, Pregunta[] all, int max) {
		// Check for duplicates bc id,

		for (int i = 0; i < max; i++) {
			int rd = new Random().nextInt(all.length);
			toReturn.add(all[rd]);
		}
		int size = toReturn.size();
		if (size < max) {
			for (int i = max - size; i < max; i++) {
				toReturn.add(all[i]);
			}
		}

		return toReturn;
	}

	public Pregunta getQuestion() {
		// check for index > getPreguntas.length
		Pregunta toReturn = null;
		if (index + 1 < currentGame.getPreguntas().size()) {
			currentPregunta = currentGame.getPreguntas().get(++index);
			toReturn = currentPregunta;
		}
		return toReturn;
	}

	public String[] finishGame() {
		String[] data = new String[2];
		data[0] = "" + currentGame.getScore();
		data[1] = ansToString();
		return data;
	}

	private String ansToString() {
		String toReturn = "";
		for (String a : currentGame.getUserAns()) {
			toReturn += "\n" + a;
		}
		return toReturn;
	}

	public boolean validate(int ans) {
		if (currentPregunta.getCorrect() == ans) {
			currentGame.addPoints();
			currentGame.setAns("true#" + ans + "#" + currentPregunta.getCorrect());
			return true;
		} else {
			currentGame.removePoints();
			currentGame.setAns("false#" + ans + "#" + currentPregunta.getCorrect());
			return false;
		}
	}

	public String[] seeText(boolean b) {
		// b records false wiki
		String where = (b) ? RECORDSTXT : WIKITXT;
		String[] lines = RepositoryService.getLines(RESFOLDER + where, b);
		return lines;
	}

	public void setUsername(String nextLine) {
		currentGame.setUserName(nextLine);
		setRecord();
	}

	private void setRecord() {
		// TODO Auto-generated method stub
		String[] lines = seeText(true);
		HashMap<Integer, String> dict = new HashMap<Integer, String>(lines.length);
		String[] split;

		for (String line : lines) {
			line = line.replace("\n", "");
			split = line.split("#");
			dict.put(Integer.parseInt(split[1]), split[0]);
		}
		dict.put(currentGame.getScore(), currentGame.getUserName());
		SortedSet<Integer> keys = new TreeSet<Integer>(dict.keySet());

		RepositoryService.writeRecord(RESFOLDER + RECORDSTXT, dict, keys);
	}

	public void createPDF() {
		// TODO Auto-generated method stub
		try {
			UtilsPDF.generatePDF(currentGame);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addQuestion(String[] data) {
		// TODO Auto-generated method stub

	}

	public void importQuestions(String path) {
		// TODO Auto-generated method stub

	}

}
