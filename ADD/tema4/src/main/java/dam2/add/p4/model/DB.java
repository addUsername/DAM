package dam2.add.p4.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dam2.add.p4.entities.Pregunta;

public class DB {

	private Connection con;
	private final String TABLE = "pregunta";
	
	public DB(String name, String host, String user, String pass) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		try {

	        con = DriverManager.getConnection("jdbc:mysql://" + host + "/"+ name, user, pass);
	        
		}catch (Exception e) {
			con = DriverManager.getConnection("jdbc:mysql://"+host+"/?user="+user+"&password="+pass);
			
	        con.createStatement().executeUpdate("CREATE DATABASE "+name);
	        con.close();
	        con = DriverManager.getConnection("jdbc:mysql://" + host + "/"+ name, user, pass);
		}
        con.setAutoCommit(false);		
	}

	public Pregunta[] findAll() {
		String query = "SELECT * FROM "+TABLE;
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return DBHelper.parsePreguntas(rs);
	}

	public Boolean save(List<Pregunta> list, boolean shouldOverwrite) {
		
		try {
			if(shouldOverwrite) dropTable();		
			return overWrite(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void dropTable() throws SQLException {
		con.createStatement().executeUpdate("DROP TABLE "+TABLE);
		con.commit();
		con.createStatement().executeUpdate("CREATE TABLE Pregunta(id int AUTO_INCREMENT PRIMARY KEY, question varchar (255) not null, o1 varchar(255) not null,o2 varchar(255) not null,o3 varchar(255) not null,answer int not null);");
		con.commit();
	}

	private Boolean overWrite(List<Pregunta> list) throws SQLException {
		for(Pregunta p: list) {
			/*
			 * You have an error in your SQL syntax ??
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+TABLE+" (question,o1,o2,o3,answer) VALUES(?,?,?,?,?");
			ps.setString(1, p.getQuestion());
			ps.setString(2, p.getOptions()[0]);
			ps.setString(3, p.getOptions()[1]);
			ps.setString(4, p.getOptions()[2]);
			ps.setInt(5, p.getCorrect());
			int r = ps.executeUpdate();

			if (r == 0)	System.out.println("error");
			ps.close();
			*/
			String a = "INSERT INTO "+TABLE+"(question,o1,o2,o3,answer) values('"+		
					p.getQuestion().replace("'", "")+"','"+
					p.getOptions()[0].replace("'", "")+"','"+
					p.getOptions()[1].replace("'", "")+"','"+
					p.getOptions()[2].replace("'", "")+"',"+
					p.getCorrect()+");";
			con.createStatement().executeUpdate(a);
			con.commit();
		}
		return true;
	}
	
}
