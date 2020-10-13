package dam2.repaso.ejercicio2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2 {
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("./output/listado.txt");
		if(!file.exists()) throw new Exception("listado.txt no existe, ruta: "+file.getAbsolutePath() );
		
		Scanner sc = new Scanner(file,"utf-8");		
		List <String> lines = new ArrayList<>();

		while(sc.hasNext()) {
			lines.add(cleanLine(sc.nextLine()));
		}
		if(write(lines, file)) System.out.println("Terminado. Ver fichero en ruta ./output/listado.txt");
	}
	/** This method first splits line to find the first word (aka name) and then another line split by name to get rid of cloned words
	 * 
	 * @param line The line read by Scanner
	 * @return cleanedline.
	 */
	
	public static String cleanLine(String line) {
		String name = line.split(" ")[0];
		return name + line.split(name)[1];
	}
	
	public static boolean write(List<String> lines, File file) {

		java.util.Collections.sort(lines);
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write("");
			fw = new FileWriter(file,true);
			for(String i:lines) {
				fw.write(i + "\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return true;
	}
}
