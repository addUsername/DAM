package dam2.repaso.ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Introduzca los numeros, (-1 para salir).");
		List<Integer> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int input = 0;
		
		while(input != -1) {
			input = sc.nextInt();
			if(input != -1) list.add(input);
		}		
		showStatics(list);		
	}
	private static void showStatics(List<Integer> list) {
		
		int num = list.size();
		int sum = list.stream().reduce(Integer::sum).get();
		double mea = (double)sum / num;
		String valores="";
		
		for(int i:list) {
			if(i > mea) valores+=i+" ";
		}
		
		System.out.println(String.format("%10s %10s %5s %5s %5s %5s %5s", "Numero valores", "|", "Suma", "|", "Media", "|", "Valores superiores a la media"));
	    System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
	    System.out.println(String.format("%14s %10s %5s %5s %.3f %5s %5s",
				num, "|", sum, "|", mea,"|", valores));
	}
}
