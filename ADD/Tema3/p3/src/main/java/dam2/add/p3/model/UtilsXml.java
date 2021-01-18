package dam2.add.p3.model;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import dam2.add.p3.entities.Pregunta;

public class UtilsXml {

	public static Pregunta[] parseString(String string) {

		System.out.println(string);
		// string = cleanUnicode(string);
		SAXBuilder b = new SAXBuilder();
		try {
			Document xml = b.build(new StringReader(string));
			Element root = xml.getRootElement();

			Pregunta[] toReturn = new Pregunta[root.getChildren().size()];
			int i = 0;
			for (Element child : root.getChildren()) {
				toReturn[i] = parseQuestion(child);
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public static String cleanUnicode(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
		return string.replace("\\u[0-9]{4}", "");
	}

	private static Pregunta parseQuestion(Element child) {

		return null;
	}
}
