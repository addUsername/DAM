package dam2.add.p4.view;

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
	}

	public static void inputError() {
		System.err.println(Strings.ERROR_INPUT);
	}

	public static void printError(String processerror) {
		System.err.println(processerror);

	}
}
