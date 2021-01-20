package dam2.add.p3.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

import dam2.add.p3.entities.Pregunta;

/**
 * Esta clse "Deberia" hacer uso del data access object directamente de los
 * ficheros
 *
 * @author SERGI
 *
 */
public class RepositoryService {

	/**
	 * Detectar auto si viene de .xls o .xml
	 *
	 * @param path
	 * @return
	 */
	public static Pregunta readPregunta(String path) {

		return null;
	}

	public static String readFileAsString(String path) {
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
		if (!b)
			return lines.toArray(new String[lines.size()]);

		String[] toReturn = new String[lines.size() / 2];
		for (int i = 0; i < lines.size() / 2; i++) {
			toReturn[i] = lines.get(i * 2) + "#" + lines.get(2 * i + 1) + "\n";
		}
		return toReturn;
	}

	public static void writeRecord(String path, HashMap<Integer, String> dict, SortedSet<Integer> keys) {

		File file = new File(path);
		try {
			FileWriter fw = new FileWriter(file);
			String text = "";
			for (Integer key : keys) {
				text += "\n" + dict.get(key) + "\n" + key;
			}
			fw.write(text.substring(1, text.length()));
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

}
