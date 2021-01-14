package dam2.dii.p2;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

public class ContactoUtils {

	public static Contacto parse(HttpServletRequest req) {

		Contacto toReturn = new Contacto();		
		toReturn.setName(req.getParameter("name").toString());
		toReturn.setSurname(req.getParameter("surname").toString());
		toReturn.setPhone(req.getParameter("phone").toString());
		toReturn.setEmail(req.getParameter("email").toString());
		toReturn.setComments(req.getParameter("comments").toString());		
		
		return checkForVoids(toReturn);
	}

	private static Contacto checkForVoids(Contacto toReturn) {
		
		Field[] fields = toReturn.getClass().getDeclaredFields();
		
		if(toReturn.getName().equals("")) return null;
		if(toReturn.getSurname().equals("")) return null;
		if(toReturn.getEmail().equals("")) return null;
		return toReturn;		
	}

}
