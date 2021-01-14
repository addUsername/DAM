package dam2.dii.p2;

import javax.servlet.http.HttpServletRequest;

public class ContactoUtils {

	public static Contacto parse(HttpServletRequest req) {

		Contacto toReturn = new Contacto();
		try {
			toReturn.setName(req.getParameter("name").toString());
			toReturn.setSurname(req.getParameter("surname").toString());
			toReturn.setPhone(req.getParameter("phone").toString());
			toReturn.setEmail(req.getParameter("email").toString());
			toReturn.setComments(req.getParameter("comments").toString());
		} catch (NullPointerException e) {
			toReturn = null;
		}
		return toReturn;
	}

}
