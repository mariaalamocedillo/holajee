package es.marialamo.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/CreaCookie")
public class CreaCookie extends HttpServlet {

    protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // se inicializa un objeto Cookie "vacío"
        String nombreCookie = "";
        String contenidoCookie = "";
        String clave="";
        String idioma="";
        Cookie unaCookie = null;
        
        // Recepción de parámetros
        nombreCookie = request.getParameter("usuario");
        clave = request.getParameter("clave");
        idioma = request.getParameter("idioma");
       
        contenidoCookie=request.getQueryString();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Crea y Recupera</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Crea y Recupera</h1>");

        try {
          // se crea el objeto cookie en el servidor
          unaCookie = new Cookie(nombreCookie, contenidoCookie);
            
          // se a�ade a la respuesta para enviar al cliente
          response.addCookie(unaCookie);                           
          
          out.println("Se crea una cookie de nombre " + nombreCookie);
          out.println("<br />");
          out.println("El contenido de la cookie es " + contenidoCookie);
          out.println("<br />");
      } 
      catch (Exception e){ 
          out.println("Se produce una excepción: ");
          out.println(e.getMessage());
          out.println("<br />");
      }
      finally {            
          out.println("<a href=\"RecuperaCookie\">Ir a RecuperaCookie <a/> ");
          out.println("</body>");
          out.println("</html>");
    	  out.close();
      }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesaSolicitud(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesaSolicitud(request, response);
    }

}