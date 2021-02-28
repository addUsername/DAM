package dam2.add.p4.view;

/**
 * Language strings
 *
 * @author SERGI
 *
 */
public class Strings {

	public static final String[] OPTIONS = { "Jugar", "Añadir pregunta", "Importar preguntas (.xls)",
			"Ver records (.txt)", "Instrucciones", "Configurar partida", "Salir" };

	public static final String ERROR_INPUT = "introduzca un numero valido";

	public static final String REQXMLQUESION = "Escriba la Pregunta";
	public static final String REQXMLOPTION1 = "Escriba la primera opcion";
	public static final String REQXMLOPTION2 = "Escriba la segunda opcion";
	public static final String REQXMLOPTION3 = "Escriba la tercera opcion";
	public static final String REQXMLANS = "Escriba el numero de la opcion correcta <0-2>";

	public static final String REQXLSFILEPATH = "Introduzca el path del fichero .xls que contiene la(s) pregunta(s)";

	public static final String ENDGAME_SCORE = "Su puntacion ha sido de :";
	public static final String ENDGAME_ANS = "El historial de preguntas :";
	public static final String ENDMENU1 = "Introduzca su nombre";
	public static final String[] ENDMENU2 = { "Menu principal", "Ver records", "Generar informe (.pdf)", "Salir" };
	public static final String IN = "Introduzca opcion elegida: ";

	public static final String CONFIGMENU1 = "Introduzca el numero de preguntas: ";
	public static final String CONFIGMENU2 = "Puntos por respuesta correcta: ";
	public static final String CONFIGMENU3 = "Puntos por respuesta incorrecta: ";

	public static final String WAIT = "Pulsa INTRO para contnuar...";

	public static final String PROCESSERROR = "Se ha producido un error al realizar la operacion";
	public static final String PROCESSOK = "Proceso finalizado con exito!";

	public static final String OVERRIDEQUESTIONS = "Como desea persistir el fichero preguntas.xml?\n";

	public static final String[] XLSOPTIONS = { "En memoria", "Añadir a preguntas.xml", "Sobreescribir preguntas,xml",
			"Deshacer cambios" };

}
