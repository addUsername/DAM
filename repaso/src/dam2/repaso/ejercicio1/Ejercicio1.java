package dam2.repaso.ejercicio1;

import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		final int numAttemps = 3;
		int i;
		String[] userPass;
		String user = "";
		
		for(i = 0; i<numAttemps; i++) {
			userPass = getUserPass();
			if(check(userPass)){
				user=userPass[0];
				break;
			}
		}
		if(!user.equals("")) {
			System.out.println("Hola "+user);
			System.out.println("Numero intentos: "+(++i));
		}else {
			
			System.out.println("has consumido todos tus intentos");
		}		
	}
	public static String[] getUserPass() {

		String[] toReturn = {"",""};
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEJERCICIO 1");
		System.out.println("Usuario: ");
		toReturn[0]=sc.nextLine();
		System.out.println("Contraseña: ");
		toReturn[1] = sc.nextLine(); 
		return toReturn;		
	}
	public static boolean check(String[] userPass) {
		
		final String USER = "USER";
		final String PASS = "PASS";
		
		if(USER.equalsIgnoreCase(userPass[0]) && PASS.equalsIgnoreCase(userPass[1])) return true;		
		return false;
	}	
}
