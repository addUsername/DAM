package dam2.add.p4.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dam2.add.p4.entities.Pregunta;

public class DBHelper {
	

	public static DB createConexion(String path) {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(path));
			return new DB(
					prop.getProperty("bd.name"),
					prop.getProperty("bd.host"),
					prop.getProperty("bd.user"),
					prop.getProperty("bd.pass"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Pregunta[] parsePreguntas(ResultSet rs) {
		// TODO Auto-generated method stub
		List<Pregunta> list = new ArrayList<Pregunta>();
		
		try {
			while (rs.next()) {
				Pregunta p = new Pregunta();
				p.setQuestion(rs.getString("question"));
				p.setCorrect(rs.getInt("answer"));
				String[] pregs = {rs.getString("o1"),rs.getString("o2"),rs.getString("o3")};
				p.setOptions(pregs);
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.toArray(new Pregunta[list.size()]);
	}
	
}
