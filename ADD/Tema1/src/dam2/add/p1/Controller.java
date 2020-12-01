package dam2.add.p1;

import java.util.HashMap;

/**
 * @author SERGI
 * Implementa la lógica de la app y se comunica con el Repository.java, Ui.java y Logger.
 */
public class Controller {

	private final int MAX = 3;	// Número max de intentos login por usuario valido
	private final String ADMIN = "admin"; // username con privilegios admin
	private final String PIN = "ABCDE"; // Codigo para desbloquear admin
	private HashMap<String, User> users; // "tabla" Usuarios. user:{ username: "", pass: "", isValid: true}
	
	/**
	 * Constructor, welcome mensaje y carga la base de usuarios
	 */
	public Controller() {
		Ui.printHello();
		this.users = Repository.findAll();
	}
	/**
	 * Método principal de la app, es llamada en un while(true) por Main.java,
	 * controla el flujo del programa. 
	 */
	public void go() {

		User user = Ui.printLogin();
		if (users.get(user.getUsername()) != null) {
			
			for(int i = 0; i<MAX-1; i++) {				
				switch(verify(user)) {				
					case 2:
						Ui.printMessage("Hola "+user.getUsername());
						Logger.log("- [SUCCESS] "+user.getUsername());
						
						if(isAdmin(user.getUsername())) {
							if(unblock()) {
								Ui.printMessage("Usuario desbloqueado");
								Logger.log("- [ADMIN / UNBLOCK] ");
							}
						}
						return;
						
					case 1:
						Ui.printError("usuario/contraseña incorrectos");
						Logger.log("- [ERROR / WRONG PASS] "+user.getUsername());
						user.setPass(Ui.getPass());
						break;
						
					case 0:
						Ui.printError("Usuario "+user.getUsername()+" bloqueado.");
						Logger.log("- [ERROR / BLOCKED] "+user.getUsername());
						
						if(isAdmin(user.getUsername())) {
							if(tryUnlockAdmin()){
								Ui.printMessage(" Pin correcto, usuario desbloquado");
								Logger.log("- [ADMIN / UNBLOCK] "+ADMIN);
							}else {
								Ui.printMessage(" Pin incorrecto.");
								Logger.log("- [ADMIN / ERROR] Wrong ping");
							}
						}
						return;
				}				
			}
			Repository.block(user);
			Ui.printMessage("Usuario bloqueado, max num intentos: " + MAX);
			Logger.log("- [BLOCK] "+user.getUsername());
			this.users = Repository.findAll();
			return;
			
		} else {			
			Ui.printMessage(user.getUsername() + " no esta registrado.");
			if(addUser(user.getUsername())) {
				Ui.printMessage("Usuario registrado con exito!");
				Logger.log("- [SIGN IN] "+user.getUsername());
			}
		}				
	}
	/**
	 * Si invalid user == ADMIN, el programa pedira el PIN para desbloquarlos
	 */
	private boolean tryUnlockAdmin() {
		String cod = Ui.askForAdminPin();
		
		if(cod.equals(PIN)) {
			Repository.unblock(ADMIN);
			this.users = Repository.findAll();
			return true;
		}
		return false;
	}
	/**
	 * Comprueba si el user es el Admin.
	 */
	private boolean isAdmin(String username) {
		return username.equalsIgnoreCase(ADMIN);
	}
	/**
	 * Ejecuta el menu de registro
	 */
	private boolean addUser(String username) {
		
		User user = Ui.addUser(username);
		if(user == null) return false;
		Repository.save(user);
		
		this.users = Repository.findAll();
		return true;
		
	}
	/**
	 * Ejecuta el menu para el rol Admin
	 */
	private boolean unblock() {
		String username =  Ui.unblock(Repository.getBlockedAsString());
		if(username == null) return false;
		Repository.unblock(username);
		
		this.users = Repository.findAll();
		return true;
	}
	/**
	 * Comprueba las credenciales
	 */
	private int verify(User user) {
		// 0 invalid user | 1 invalid pass | 2 Ok.
		User a = users.get(user.getUsername());
		
		if(!a.isValid()) return 0;		
		if(a.getPass().contentEquals(user.getPass())) return 2;		
		return 1;
	}

}
