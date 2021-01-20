package dam2.add.p3.model;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import dam2.add.p3.entities.Pregunta;

public class UtilsXml {

	private static final String ROOT = "juego";
	private static final String CHILD = "pregunta";
	private static final String Q = "texto";
	private static final String O = "respuesta";
	private static final String C = "correcta";

	public static Pregunta[] parseString(String string) {

		SAXBuilder b = new SAXBuilder();
		Document xml;
		try {
			xml = b.build(new StringReader(string));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Element root = xml.getRootElement();

		Pregunta[] toReturn = new Pregunta[root.getChildren().size()];
		int i = 0;
		for (Element child : root.getChildren()) {
			toReturn[i] = parseQuestion(child);
			i++;
		}
		return toReturn;
	}

	public static Pregunta parseQuestion(Element child) {

		Pregunta toReturn = new Pregunta();
		String[] op = { child.getChildText(O + 1), child.getChildText(O + 2), child.getChildText(O + 3) };

		toReturn.setQuestion(child.getChildText(Q));
		toReturn.setOptions(op);
		toReturn.setCorrect(Integer.parseInt(child.getChildText(C)));

		return toReturn;
	}
}
