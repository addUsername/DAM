package dam2.add.p3.model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import dam2.add.p3.entities.Pregunta;

public class UtilsXml {

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

	public static Pregunta parseQuestion(Element child) {

		Pregunta toReturn = new Pregunta();
		String[] op = { child.getChildText(O + 1), child.getChildText(O + 2), child.getChildText(O + 3) };

		toReturn.setQuestion(child.getChildText(Q));
		toReturn.setOptions(op);
		toReturn.setCorrect(Integer.parseInt(child.getChildText(C)));

		return toReturn;
	}

	public static Boolean addQuestion(String[] data, String path) {
		// TODO Auto-generated method stub

		SAXBuilder b = new SAXBuilder();
		Document xml;
		try {
			xml = b.build(new File(path));
			Element question = new Element(CHILD);
			question.addContent(new Element(Q).setText(data[0]));
			question.addContent(new Element(O + 1).setText(data[1]));
			question.addContent(new Element(O + 2).setText(data[2]));
			question.addContent(new Element(O + 3).setText(data[3]));
			question.addContent(new Element(C).setText(data[4]));
			xml.getRootElement().addContent(question);

			FileWriter fw = new FileWriter(path);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(xml, fw);
			fw.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Boolean overwriteExcel(String path, List<Pregunta> preguntas, boolean shouldOverride) {
		// TODO Auto-generated method stub
		SAXBuilder b = new SAXBuilder();
		Document xml;
		try {
			xml = b.build(new File(path));
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
