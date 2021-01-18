package dam2.add.p3.view;

/**
 * Añadir colores en un futuro oq, tarjeta pregunta, etc
 *
 * @author SERGI
 *
 */
public class Print {

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printOptions(String[] options) {
		for (int i = 0; i < options.length; i++) {
			System.out.println(i + "- " + options[i]);
		}
		System.out.print(Strings.IN);
	}

	public static void inputError() {
		System.out.println(Strings.ERROR_INPUT);

	}
}
