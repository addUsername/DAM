package dam2.repaso.ejercicio2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2 {
	
	public static void main(String[] args) throws Exception {
		System.out.println("[INFO]  ./output/listado.txt es necesario");
		File file = new File("./output/listado.txt");
		if(!file.exists()) throw new Exception("listado.txt no existe, ruta: "+file.getAbsolutePath() );
		
		Scanner sc = new Scanner(file,"utf-8");		
		List <String> lines = new ArrayList<>();

		while(sc.hasNext()) {
			lines.add(cleanLine(sc.nextLine()));
		}
		if(write(lines, file)) System.out.println("Terminado. Ver fichero en ruta ./output/listado.txt");
	}
	/** Este metodo primero split() la linea por " " para encontrar el nombre y luego vuelve a dividir la linea por el nombre.
	 * 
	 * @param line The line read by Scanner
	 * @return cleaned line.
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
