package dam2.add.p1;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//IO operations
public class Repository {

	private static final String USERS_TXT = "acceso.txt";
	private static final String BLOCK_TXT = "bloqueados.txt";
	
	/**
	 * Lee los archivos acceso.txt y bloqueados.txt generando un HashMap
	 */
	public static HashMap<String ,User> findAll() {
		
		HashMap<String, User> toReturn = new HashMap<String, User>();
		
		try {
				List<String> blockedUsers = getBlockedUsers();
				
				Stream<String> myStream = Files.lines(Paths.get(USERS_TXT),StandardCharsets.UTF_8);
				myStream.forEach( line -> {
					String[] pair = line.split(":");
					toReturn.put(pair[0] ,new User(
							pair[0],
							pair[1],
							!blockedUsers.contains(pair[0])
							));		
				});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	/**
	 * Lee el archivo bloqueados.txt y devuelve un array con los nombres de usuario bloqueados
	 */
	private static List<String> getBlockedUsers() throws Exception {
		
		Stream<String> myStream = Files.lines(Paths.get(BLOCK_TXT),StandardCharsets.UTF_8);
		return myStream.collect(Collectors.toList());
	}
	/**
	 * Escribe en el archivo bloqueados.txt una nueva entrada
	 */
	public static boolean block(User user) {
		
		try {
			if( getBlockedUsers().contains(user.getUsername())) return false;
			FileWriter fw = new FileWriter(new File(BLOCK_TXT), true);
			fw.write("\n"+user.getUsername());
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * Escribe en el archivo acceso.txt una nueva entrada
	 */
	public static void save(User user) {
		try {
			FileWriter fw = new FileWriter(new File(USERS_TXT), true);
			fw.write("\n"+user.toString());
			fw.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Elimina de bloquados.txt el usuario introducido
	 */
	public static void unblock(String username) {
		try {
			List<String> blockedUsers = getBlockedUsers();
			int index = blockedUsers.indexOf(username);
			
			if(index < 0) return;
			blockedUsers.remove(index);
			
			FileWriter fw = new FileWriter(new File(BLOCK_TXT));
			String users = blockedUsers.stream()
					.map(user ->"\n"+user)
					.collect(Collectors.joining(""));
			fw.write(users);
			fw.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Devuelve los nombres de los usuarios bloqueados como String
	 */
	public static String getBlockedAsString() {
		String toReturn = " | ";
		try {
			toReturn = getBlockedUsers().stream()
					.map(user ->user+" | ")
					.collect(Collectors.joining(""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}
}
