package dam2.dii.p1;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static Usuario parse(HttpServletRequest request) {

		if (request.getParameter("user") == "" || request.getParameter("pass") == null
				|| request.getParameter("pass2") == null)
			return null;

		return (new Usuario(request.getParameter("user"), request.getParameter("pass"), request.getParameter("pass2")));
	}

	public static boolean validateUser(Usuario usuario) {
		// TODO Auto-generated method stub
		return (usuario.getPass().equals(usuario.getPass2()));
	}
}
