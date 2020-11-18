package dam2.add.p1;

import java.util.Scanner;

/**
 * @author SERGI
 * Esta clase se encarga de la interacción con el usuario.
 */
public class Ui {

	
	public static void printHello() {
		
		System.out.println(" -----------------");
		System.out.println("| ADD Actividad 1 |");
		System.out.println(" -----------------");
	}
	
	/**
	 * Menu principal
	 */
	public static int printMenu() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n1.- Login\n2.- Salir\n");
		return sc.nextInt();
	}
	/**
	 * Pedir credenciales
	 */
	public static User printLogin() {
		
		System.out.println("\nLOGIN");
		User toReturn = new User();
		Scanner sc = new Scanner(System.in);
		System.out.print("User: ");
		toReturn.setUsername(sc.nextLine());
		System.out.print("Pass: ");
		toReturn.setPass(sc.nextLine());
		return toReturn;
	}
	/**
	 * Añadir nuevo usuario
	 */
	public static User addUser(String username) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Añadir "+username+"? S/N: ");
		if(sc.nextLine().equalsIgnoreCase("n")) return null;
		return getPassword(username);
	}
	/**
	 * Check password or exit
	 */
	private static User getPassword(String username) {
		
		String a = "a", b = "b";
		Scanner sc = new Scanner(System.in);
		while(!a.equals(b)) {
			
			System.out.print("Pass (introduce 0 para salir): ");
			a = sc.nextLine();
			if (a.equals("0")) return null;
			
			System.out.print("Repetir Pass: ");
			b = sc.nextLine();
		}		
		return new User(username, a);
	}
	/**
	 * Feedback al usuario
	 */
	public static void printMessage(String message) {
		System.out.println(message);
	}
	public static void printError(String error) {
		System.err.println(error);
	}
	public static String getPass() {		
		Scanner sc = new Scanner(System.in);
		System.out.print("Pass: ");
		return sc.nextLine();
	}
	public static String unblock(String blockedUsers) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Usuarios bloqueados, escriba un nombre para desbloquar o exit para salir");
		System.out.println(blockedUsers);
		String username = sc.nextLine();
		if(username.equals("exit")) return null;
		return username;
	}
}
