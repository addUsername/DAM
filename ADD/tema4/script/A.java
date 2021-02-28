package a;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


public class A {
	private static final String CHILD = "pregunta";
	private static final String Q = "texto";
	private static final String O = "respuesta";
	private static final String C = "correcta";

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String string = new String(Files.readAllBytes(new File("preguntas.xml").toPath()), StandardCharsets.UTF_8);
		SAXBuilder b = new SAXBuilder();
		Document xml = null;
		try {
			xml = b.build(new StringReader(string));

		} catch (Exception e) {
			e.printStackTrace();
		}
		Element root = xml.getRootElement();

		Pregunta[] toReturn = new Pregunta[root.getChildren().size()];
		int i = 0;
		for (Element child : root.getChildren()) {
			toReturn[i] = parseQuestion(child);
			i++;
		}
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/trivial" ,"root", "root");
		for (Pregunta p: toReturn) {
			String a = "INSERT INTO pregunta(question,o1,o2,o3,answer) values('"+		
					p.getQuestion().replace("'", "")+"','"+
					p.getOptions()[0].replace("'", "")+"','"+
					p.getOptions()[1].replace("'", "")+"','"+
					p.getOptions()[2].replace("'", "")+"','"+
					p.getCorrect()+"');";
			con.createStatement().executeUpdate(a);		
			System.out.println("inseertgt");
		}
		
		System.out.println("finishh!!");
		
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

class Pregunta {

	private String question;
	private long id;
	private String[] options;
	private int answer;

	public Pregunta() {
		id = new Random().nextLong();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public int getCorrect() {
		return answer;
	}

	public void setCorrect(int answer) {
		this.answer = answer;
	}

	public long getId() {
		return id;
	}
}
