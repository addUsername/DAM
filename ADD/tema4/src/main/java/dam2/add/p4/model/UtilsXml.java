package dam2.add.p4.model;

import java.io.FileWriter;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import dam2.add.p4.entities.Pregunta;

@Deprecated
public class UtilsXml {

	private static final String CHILD = "pregunta";
	private static final String Q = "texto";
	private static final String O = "respuesta";
	private static final String C = "correcta";

	/**
	 * Recibe el fichero xml como string y mapea a Pregunta[]
	 *
	 * @param string
	 * @return
	 */
	public static Pregunta[] parseString(String string) {

		SAXBuilder b = new SAXBuilder();
		Document xml;
		try {
			xml = b.build(new StringReader(string));

		} catch (Exception e) {
			// e.printStackTrace();
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

	/**
	 * Child -> Pregunta
	 *
	 * @param child
	 * @return
	 */
	public static Pregunta parseQuestion(Element child) {

		Pregunta toReturn = new Pregunta();
		String[] op = { child.getChildText(O + 1), child.getChildText(O + 2), child.getChildText(O + 3) };

		toReturn.setQuestion(child.getChildText(Q));
		toReturn.setOptions(op);
		toReturn.setCorrect(Integer.parseInt(child.getChildText(C)));

		return toReturn;
	}

	/**
	 * METER BBDD
	 * 
	 * @param string
	 * @param path
	 * @param preguntas
	 * @param shouldOverride
	 * @return
	 */

	public static Boolean overwriteExcel(String string, String path, List<Pregunta> preguntas, boolean shouldOverride) {

		SAXBuilder b = new SAXBuilder();
		Document xml;
		try {
			xml = b.build(new StringReader(string));
			if (shouldOverride) {
				xml.getRootElement().removeContent();
			}

			for (Pregunta p : preguntas) {
				Element question = new Element(CHILD);
				question.addContent(new Element(Q).setText(p.getQuestion()));
				question.addContent(new Element(O + 1).setText(p.getOptions()[1]));
				question.addContent(new Element(O + 2).setText(p.getOptions()[1]));
				question.addContent(new Element(O + 3).setText(p.getOptions()[1]));
				question.addContent(new Element(C).setText("" + p.getCorrect()));
				xml.getRootElement().addContent(question);
			}

			FileWriter fw = new FileWriter(path);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(xml, fw);
			fw.close();
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}
}
