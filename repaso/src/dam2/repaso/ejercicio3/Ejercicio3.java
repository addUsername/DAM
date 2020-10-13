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
		System.out.println("\nNumero valores: " + num);
		int sum = list.stream().reduce(Integer::sum).get();
		System.out.println("Suma: " + sum);
		int mea = sum / num;
		System.out.println("Media: " + mea );
		
		System.out.println("Valores superiores a la media: ");
		list.forEach((Integer i) -> {
			if(i > mea) System.out.print(i+" ");
		});
		
	}
}
