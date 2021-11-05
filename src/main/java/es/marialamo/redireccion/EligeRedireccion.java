package es.marialamo.redireccion;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("EligeRedireccion")
public class EligeRedireccion extends HttpServlet {

  @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rutaContexto = request.getContextPath();
    String metodoRedireccion = request.getParameter("redireccion");
    String vistaDestino;
		if (metodoRedireccion != null) {  // se ha recibido el parámetro metodoRedireccion
			if (metodoRedireccion.equals("F")) { // se ha seleccionado FORWARD
				vistaDestino = "/destinoForward.html";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			} else {  // se ha seleccionado REDIRECT
				vistaDestino = "/destinoRedirect.html";
				response.sendRedirect(rutaContexto + vistaDestino);
			}
		} else {  // no se ha recibido el parámetro, se devuelve a la página inicial
			vistaDestino = "/eligeRedireccion.html";
			response.sendRedirect(rutaContexto + vistaDestino);
		}

	}

}
