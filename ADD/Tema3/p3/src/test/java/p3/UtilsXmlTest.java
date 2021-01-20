package p3;

import java.io.IOException;
import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dam2.add.p3.entities.Pregunta;
import dam2.add.p3.model.UtilsXml;

public class UtilsXmlTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void parseQuestion_goodChildBuild_notNullTrue() throws JDOMException, IOException {

		SAXBuilder b = new SAXBuilder();
		Document doc = b.build(new StringReader(xml));
		Element child = doc.getRootElement().getChildren().get(0);

		Pregunta p = UtilsXml.parseQuestion(child);
		Assert.assertNotNull(p);
		Assert.assertTrue(p.getOptions().length == 3);
		Assert.assertTrue(p.getCorrect() == 0);
	}

	@Test
	public void parseString_goodXmlFormat_notNullTrue() {
		String Q1 = "question1";
		int correct = 0;

		Pregunta[] preguntas = UtilsXml.parseString(xml);

		Assert.assertNotNull(preguntas);
		Assert.assertTrue(preguntas.length == 2);
		Assert.assertTrue(preguntas[0].getQuestion().equals(Q1));
		Assert.assertTrue(preguntas[0].getCorrect() == correct);
	}

	private final String xml = "<?xml version=\"1.0\" ?>\r\n" + "<juego>\r\n" + "	<pregunta>\r\n"
			+ "		<texto>question1</texto>\r\n" + "		<respuesta1>Ohmios</respuesta1>\r\n"
			+ "		<respuesta2>Entero</respuesta2>\r\n" + "		<respuesta3>Algoritmo</respuesta3>\r\n"
			+ "		<correcta>0</correcta>\r\n" + "	</pregunta>\r\n" + "	<pregunta>\r\n"
			+ "		<texto>Tipo de dato que toma valores del conjunto de numeros que no tienen parte decimal</texto>\r\n"
			+ "		<respuesta1>Ohmios</respuesta1>\r\n" + "		<respuesta2>Entero</respuesta2>\r\n"
			+ "		<respuesta3>Algoritmo</respuesta3>\r\n" + "		<correcta>1</correcta>\r\n" + "	</pregunta>\r\n"
			+ "</juego> ";
}
