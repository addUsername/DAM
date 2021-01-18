package dam2.add.p3.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import dam2.add.p3.entities.Pregunta;

/**
 * Esta clse "Deberia" hacer uso del data access object directamente de los
 * ficheros
 *
 * @author SERGI
 *
 */
public class RepositoryService {

	public static Pregunta[] getAllQuestions(String path) {

		return UtilsXml.parseString(readFileAsString(path));
	}

	/**
	 * Detectar auto si viene de .xls o .xml
	 *
	 * @param path
	 * @return
	 */
	public static Pregunta readPregunta(String path) {

		return null;
	}

	private static String readFileAsString(String path) {
		String file = "";
		try {
			file = new String(Files.readAllBytes(new File(path).toPath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// e.printStackTrace();
			return null;
		}
		return file;
	}

	private static List<String> readFile(String path) {
		// TODO Auto-generated method stub
		List<String> list;
		try {
			list = Files.readAllLines(new File(path).toPath());
		} catch (IOException e) {
			// e.printStackTrace();
			return null;
		}
		return list;
	}

	public static String[] getLines(String path, boolean b) {
		// TODO Auto-generated method stub
		List<String> lines = readFile(path);
		if (lines == null)
			return null;
		if (b)
			return lines.toArray(new String[lines.size()]);

		String[] toReturn = new String[lines.size() / 2];
		for (int i = 0; i < lines.size(); i++) {
			if (i % 2 == 0) {
				toReturn[i / 2] = lines.get(i) + "\n" + lines.get(i + 1);
			}
		}
		return toReturn;
	}
}
