package dam2.repaso.ejercicio4;

import java.sql.Connection;
import java.util.Scanner;

public class Ejercicio4 {
	

	public static void main(String[] args) {
		
		Persistence per = new Persistence();
		Scanner sc = new Scanner(System.in);		
		printMenu();
		callAction(sc.nextInt(),per);
	}

	private static void printMenu() {
		
		System.out.println("\nAGENDA");
		System.out.println("1- Leer contacto \n2- Añadir contacto \n3- Borrar contacto \n0- Salir");		
	}

	private static void callAction(int nextInt, Persistence per) {
		
		
		switch (nextInt) {
		
		case 1:
			
			break;
		case 2:
			per.create(createContact());
			break;
		case 3:
			break;
		case 0:
			System.exit(0);
		default:
			break;
		}
	}

	private static Contacto createContact() {
		Contacto contact = new Contacto();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre:");
		contact.setName(sc.nextLine());
		System.out.println("Telefono:");
		contact.setPhone(sc.nextLine());
		System.out.println("Casa:");
		contact.setHome(sc.nextLine());
		return contact;
	}

}
