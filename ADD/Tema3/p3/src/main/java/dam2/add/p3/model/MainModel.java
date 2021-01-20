package dam2.add.p3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

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
	private final String PREGUNTASXLS = "preguntas.xls";

	private Partida currentGame;
	private Pregunta currentPregunta;
	private int index;
	private boolean setPreguntas = true;

	public MainModel() {
		super();
		currentGame = new Partida();
	}

	/**
	 * REQUSITO 1
	 *
	 * El método {@link UtilsXml#parseString(String)} mapea preguntas.xml como
	 * Pregunta[].
	 *
	 * El metodo {@link MainModel#chooseQuestions(Pregunta[], int)} selecciona int
	 * preguntas.
	 *
	 * El attributo {@link MainModel#setPreguntas} indicia si es necesario cargar
	 * las preguntas o no.
	 *
	 * @param config configura la partida ( num preguntas, puntos por acierto,
	 *               puntos por fallo ).
	 */
	public void startGame(int[] config) {
		currentGame = new Partida();
		currentGame.setConfig(config[0], config[1], config[2]);

		if (setPreguntas) {
			Pregunta[] all = UtilsXml.parseString(RepositoryService.readFileAsString(RESFOLDER + QUESTIONSXML));
			currentGame.setPreguntas(chooseQuestions(all, currentGame.MAXPREGUNTAS));
		}
		index = -1;
	}

	/**
	 * Se selecciona un indice al azar hasta @param max´
	 *
	 * @param all Todas las preguntas disponibles en la partida
	 * @param max Numer pregunta por partida
	 * @return
	 */
	private List<Pregunta> chooseQuestions(Pregunta[] all, int max) {
		List<Pregunta> toReturn = new ArrayList<Pregunta>(max);

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

	/**
	 * REQUISITO 1
	 *
	 * Se van leyendo las preguntas secuencialmente, si devuelve null se acaba la
	 * partida
	 */
	public Pregunta getQuestion() {
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

	/**
	 * REQUISITOS 7 y 9 Devuelve records.txt o wiki.txt
	 */
	public String[] seeText(boolean b) {
		String where = (b) ? RECORDSTXT : WIKITXT;
		String[] lines = RepositoryService.getLines(RESFOLDER + where, b);
		return lines;
	}

	/**
	 * REQUISITO 7 Recoge el nombre de usuario
	 */
	public void setUsername(String nextLine) {
		currentGame.setUserName(nextLine);
		setRecord();
	}

	/**
	 * REQUISITO 7 Mapea records.txt a HashMap<puntos, nombre>, se ordenan las keys
	 * y llama a{@link RepositoryService#writeRecord(String, HashMap, SortedSet)
	 */
	private void setRecord() {
		String[] lines = seeText(true);
		HashMap<Integer, String> dict = new HashMap<Integer, String>(lines.length);
		String[] split;

		for (String line : lines) {
			line = line.replace("\n", "");
			split = line.split("#");
			dict.put(Integer.parseInt(split[1]), split[0]);
		}
		dict.put(currentGame.getScore(), currentGame.getUserName());
		SortedSet<Integer> keys = new TreeSet<Integer>(Collections.reverseOrder());
		keys.addAll(dict.keySet());

		RepositoryService.writeRecord(RESFOLDER + RECORDSTXT, dict, keys);
	}

	public boolean createPDF() {
		try {
			UtilsPDF.generatePDF(currentGame);

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Boolean addQuestion(String[] data) {
		if (Integer.parseInt(data[4]) < 0 || Integer.parseInt(data[4]) > 2)
			return null;
		return UtilsXml.addQuestion(data, RESFOLDER + QUESTIONSXML);
	}

	public Boolean SaveImportedQuestions(String path) {
		path = (path == null) ? PREGUNTASXLS : path;

		Pregunta[] preguntas;
		try {
			preguntas = UtilsExcel.parseFile(path);

		} catch (Exception e) {
			return false;
		}
		List<Pregunta> p = chooseQuestions(preguntas, currentGame.MAXPREGUNTAS);

		currentGame.setPreguntas(p);
		setPreguntas = false;
		return (preguntas != null);
	}

	public boolean writeImportedQuestions(boolean shouldOverwrite) {

		Boolean bool = UtilsXml.overwriteExcel(RESFOLDER + QUESTIONSXML, currentGame.getPreguntas(), shouldOverwrite);
		return bool;
	}

}
