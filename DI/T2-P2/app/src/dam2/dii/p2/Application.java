package dam2.dii.p2;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class Application implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		
		// TODO Auto-generated method stub
		List<Contacto> contactos = (ArrayList<Contacto>) sce.getServletContext().getAttribute("contactos");
		sce.getServletContext().addServlet("controller", new Controller(contactos)).addMapping("/post");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// Eclipse is now happy.
		
	}

}
