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
		System.out.println("void controllerr");
		try {
			contactos =  (ArrayList<Contacto>) getServletContext().getAttribute("contactos");
		} catch (NullPointerException e) {
			// TODO: handle exception
			contactos  = new ArrayList<Contacto>();
		}
		
	}
	public Controller(List<Contacto> contactos) {
		System.out.println("custom controllerr");
		if (contactos != null) {
			System.out.println("inside ifff");
			this.contactos = contactos;
		} else {
			System.out.println("else :(");
			this.contactos = new ArrayList<Contacto>();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Contacto contacto = ContactoUtils.parse(req);

		if (contacto != null && !isDuplicate(contacto)) {
			System.out.println("inside ifff");
			contactos.add(contacto);
			getServletContext().setAttribute("contactos", contactos);
			req.setAttribute("success", true);
		} else {
			System.out.println("elseee servlet:(");
			req.setAttribute("error", "Invalid form");
		}
		getServletContext().getRequestDispatcher("/index.jsp").include(req, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private boolean isDuplicate(Contacto contacto) {
		// TODO Auto-generated method stub
		//return contactos.stream().anyMatch(con -> con.getEmail().equals(contacto.getEmail()));
		return false;
	}

}
