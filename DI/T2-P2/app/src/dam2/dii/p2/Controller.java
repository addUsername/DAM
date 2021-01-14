package dam2.dii.p2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post")
public class Controller extends HttpServlet {

	List<Contacto> contactos;

	public Controller() {
		try {
			contactos =  (ArrayList<Contacto>) getServletContext().getAttribute("contactos");
		} catch (NullPointerException e) {
			contactos  = new ArrayList<Contacto>();
		}		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Contacto contacto = ContactoUtils.parse(req);

		if(contacto == null) {
			req.setAttribute("error", "Rellena todos los campos, compi");
			getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
			return;
		}
		
		if (isDuplicate(contacto)) {
			req.setAttribute("error", "Ya tenemos tus datos compi");
			getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
			return;
		}
		
		contactos.add(contacto);
		getServletContext().setAttribute("contactos", contactos);
		getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
		return;		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private boolean isDuplicate(Contacto contacto) {
		return contactos.stream().anyMatch(con -> con.getEmail().equals(contacto.getEmail()));
	}

}
