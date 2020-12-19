package dam2.add.p2.ui;

import java.util.Scanner;

import dam2.add.p2.entities.Alumno;
import dam2.add.p2.entities.Persona;
import dam2.add.p2.entities.Profesor;

/**
 * @author SERGI
 *
 */
public class Ui {
	/**
	 * REQUISITO 7
	 */
	public static String showMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENÚ\r\n" + 
				"1- Añadir Persona\r\n" + 
				"2- Felicitar\r\n" + 
				"3- Listar\r\n" + 
				"4- Grabar\r\n" + 
				"5- Fin\r\n" + 
				"Introducir opción:\r\n");
		return sc.nextLine();
	}
	public static void showMessage(String msg) {
		System.out.println(msg);
	}

	public static String askTypePersona() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce (1) para añadir alumno y (2) para añadir profesor");
		
		return (sc.nextLine().equals("2"))? "Profesor" : "Alumno";
	}

	public static Persona askForProfesor() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce nombre y apellidos");
		String name = sc.nextLine();
		
		System.out.println("Introduce dia y mes del cumpleaños, en formato DD MM");
		String cumpleanos = sc.nextLine();
		
		System.out.println("Introduce materia");
		String materia = sc.nextLine();
		
		System.out.println("Introduce grupo");
		String grupo = sc.nextLine();
		
		return new Profesor(name,cumpleanos,materia,grupo);
	}

	public static Persona askForAlumno(String matricula) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce nombre y apellidos");
		String name = sc.nextLine();
		
		System.out.println("Introduce dia y mes del cumpleaños, en formato DD MM");
		String cumpleanos = sc.nextLine();
		System.out.println("Introduce grupo");
		String grupo = sc.nextLine();
		
		return new Alumno(name,cumpleanos,matricula,grupo);
	}
	public static String[] askForDate() {
		Scanner sc = new Scanner(System.in);
		
		String[] toReturn = new String[2];
		System.out.println("Introduce el dia");
		toReturn[0] = sc.nextLine();
		System.out.println("Introduce el mes");
		toReturn[1] = sc.nextLine();
		return toReturn;
		
	}
}
