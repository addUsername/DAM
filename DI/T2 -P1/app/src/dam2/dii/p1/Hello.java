package dam2.dii.p1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = Utils.parse(request);

		if (usuario == null) {
			// response.sendRedirect("WEB-INF/error.jsp"); return;

			request.setAttribute("error", "formulario incompleto");
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		if (Utils.validateUser(usuario)) {
			/*
			 * request.setAttribute("user", usuario.getName());
			 * getServletContext().getRequestDispatcher("/index.jsp").include(request,
			 * response); return;
			 */
			request.setAttribute("user", usuario.getName());
			getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
			return;
		}
		// response.sendRedirect("WEB-INF/error.jsp"); return;

		request.setAttribute("error", "Las contraseñas no coinciden");
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		return;
	}

}
