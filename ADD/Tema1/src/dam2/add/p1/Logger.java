package dam2.add.p1;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class Logger {
	private static final String LOG_LOG = "login.log";
	
	/**
	 * Escribe el log en loging.log
	 * Ex: Wed Nov 18 22:59:45 CET 2020 - [SUCCESS] admin
	 */
	public static void log(String string) {
		try {
			File file = new File(LOG_LOG);
			if(!file.exists()) file.createNewFile();
			
			FileWriter fw = new FileWriter(file, true);
			fw.write("\n"+new Date()+" "+string);
			fw.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
