package es.marialamo.holajee;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "MuestraVariablesServidor", value = "/muestra")
public class MuestraVariablesServidor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MostrarVariables</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>MostrarVariables</h1>");
            out.println("Este servlet muestra las variables contenidas en la petición (objeto request) <br/><br />");
            out.println("<table border=2px width=800px margin=10px>");
            out.println("<tr><td><b>Variable</b></td><td><b>Valor</b></td></tr>");
            out.println("<tr><td>Software de SERVIDOR</td><td>" + request.getServletContext().getServerInfo() + "</td></tr>");
            out.println("<tr><td>Directorio de DESPLIEGUE</td><td>" + request.getServletContext().getRealPath("/")+ "</td></tr>");
            out.println("</table>");

            out.println("<hr /><br />");

            out.println("<table border=2px width=800px margin=10px>");
            out.println("<tr><td><b>Variable</b></td><td><b>Valor</b></td></tr>");
            out.println ("<tr> <td>Dirección remota (request.getRemoteAddress)</td> <td>" + request.getRemoteAddr() + "</td></tr>");
            out.println ("<tr> <td>Puerto remoto (request.getRemotePort)</td> <td>" + request.getRemotePort() + "</td></tr>");
            out.println("<tr><td>URL invocada (request.getRequestURL)</td><td>" + request.getRequestURL() + "</td></tr>");
            out.println ("<tr><td>Método de petición (request.getMethod)</td> <td>" + request.getMethod() + "</td></tr>");
            out.println ("<tr><td>Protocolo (request.getProtocol)</td> <td>" + request.getProtocol() + "</td></tr>");

            out.println ("<tr><td>Nombre del Servidor (request.getServerName)</td> <td>" + request.getServerName() + "</td></tr>");
            out.println ("<tr><td>Puerto del Servidor (request.getServerPort)</td> <td>" + request.getServerPort() + "</td></tr>");
            out.println ("<tr><td>Nombre del ServletPath (request.getServletPath)</td> <td>" + request.getServletPath()+ "</td></tr>");

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
