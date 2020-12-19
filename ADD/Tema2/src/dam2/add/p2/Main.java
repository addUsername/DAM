package dam2.add.p2;

import dam2.add.p2.controller.Controller;
import dam2.add.p2.ui.Ui;

/**
 * @author SERGI
 *
 */
public class Main {

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		
		while(true) {
			
			switch(Ui.showMenu()) {
			
			case "1":
				controller.anadirPersona();
				break;
			case "2":
				controller.felicitarPersona();
				break;
			case "3":
				controller.listarPersonas();
				break;
			case "4":
				controller.grabar();
				break;
			case "5":
				System.exit(0);
				break;
			default:
				Ui.showMessage("Opcion incorrecta");	
			}
		}
	}
}
