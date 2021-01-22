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
	private List<Pregunta> imported;

	public MainModel() {
		super();
		currentGame = new Partida();
	}

	/**
	 * REQUSITO 1
	 *
	 * El método {@link UtilsXml#parseString(String)} mapea preguntas.xml a
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

		if (!setPreguntas) {
			currentGame.setPreguntas(imported);
		} else {
			Pregunta[] all = UtilsXml.parseString(RepositoryService.readFileAsString(RESFOLDER + QUESTIONSXML));
			currentGame.setPreguntas(chooseQuestions(all, currentGame.MAXPREGUNTAS));
		}
		index = -1;
	}

	/**
	 * Se selecciona un indice al azar hasta obtener @param max preguntas
	 *
	 * @param all Todas las preguntas disponibles en la partida
	 * @param max Numero de preguntas por partida
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

	/**
	 * Envia la puntuación y las correccion de respuestas
	 */
	public String[] finishGame() {
		String[] data = new String[2];
		data[0] = "" + currentGame.getScore();
		data[1] = ansToString();
		return data;
	}

	/**
	 * Formatea las respuestas
	 *
	 * @return
	 */
	private String ansToString() {
		String toReturn = "";
		for (String a : currentGame.getUserAns()) {
			toReturn += "\n" + a;
		}
		return toReturn;
	}

	/**
	 * REQUISITO 3 Corrige la respuesta y actualiza la puntuacion
	 */
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
	 * REQUISITOS 7 y 9 Devuelve records.txt o wiki.txt como String[]
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
		return;
	}

	/**
	 * REQUISITO 7 Mapea records.txt a HashMap<puntos, nombre>, se ordenan las keys
	 * y llama a{@link RepositoryService#writeRecord(String, HashMap, SortedSet),
	 * evita duplicados
	 */
	private void setRecord() {
		String[] lines = seeText(true);
		HashMap<Integer, String> dict = new HashMap<Integer, String>(lines.length);
		String[] split;
		boolean firstTime = true;

		for (String line : lines) {
			line = line.replace("\n", "");
			split = line.split("#");

			if (split[0].equals(currentGame.getUserName())) {
				firstTime = false;

				if (currentGame.getScore() > Integer.parseInt(split[1])) {
					dict.put(currentGame.getScore(), currentGame.getUserName());

				} else {
					dict.put(Integer.parseInt(split[1]), split[0]);
				}
			} else {
				dict.put(Integer.parseInt(split[1]), split[0]);
			}
		}

		if (firstTime) {
			dict.put(currentGame.getScore(), currentGame.getUserName());
		}

		SortedSet<Integer> keys = new TreeSet<Integer>(Collections.reverseOrder());
		keys.addAll(dict.keySet());

		RepositoryService.writeRecord(RESFOLDER + RECORDSTXT, dict, keys);
		return;
	}

	/**
	 * REQUISITO 6 Genera el pdf
	 */
	public boolean createPDF() {
		try {
			UtilsPDF.generatePDF(currentGame);

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * REQUISITO 4 Añade una pregunta a preguntas.xml
	 */
	public Boolean addQuestion(String[] data) {
		if (Integer.parseInt(data[4]) < 0 || Integer.parseInt(data[4]) > 2)
			return null;

		Pregunta p = new Pregunta();
		p.setQuestion(data[0]);
		String[] op = { data[1], data[2], data[3] };
		p.setOptions(op);
		p.setCorrect(Integer.parseInt(data[4]));
		List<Pregunta> list = new ArrayList<Pregunta>();
		list.add(p);

		return UtilsXml.overwriteExcel(RepositoryService.readFileAsString(RESFOLDER + QUESTIONSXML),
				RESFOLDER + QUESTIONSXML, list, false);
	}

	/**
	 * REQUISITO 5 Importa el fichero .xls y guarda las preguntas en memoria
	 */
	public Boolean SaveImportedQuestions(String path) {
		path = (path == null) ? PREGUNTASXLS : path;

		Pregunta[] preguntas;
		try {
			preguntas = UtilsExcel.parseFile(path);

		} catch (Exception e) {
			return false;
		}
		imported = chooseQuestions(preguntas, currentGame.MAXPREGUNTAS);
		setPreguntas = false;
		return (preguntas != null);
	}

	/**
	 * Persiste (sobreescibe o añade) las preguntas importadas en formato .xls a
	 * preguntas.xml
	 */
	public boolean writeImportedQuestions(boolean shouldOverwrite) {

		Boolean bool = UtilsXml.overwriteExcel(RepositoryService.readFileAsString(RESFOLDER + QUESTIONSXML),
				RESFOLDER + QUESTIONSXML, currentGame.getPreguntas(), shouldOverwrite);
		return bool;
	}

}
