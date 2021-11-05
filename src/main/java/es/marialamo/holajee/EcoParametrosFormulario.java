package es.marialamo.holajee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Eco")
public class EcoParametrosFormulario extends HttpServlet {

    // Independiza al servlet del método de envío del formulario

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
                         throws IOException {
        procesaSolicitud(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                          throws IOException {
        procesaSolicitud(request, response);
    }
    // Función embudo
    protected void procesaSolicitud(HttpServletRequest request,
                                        HttpServletResponse response)
                           throws  IOException {

        // Recepción de parámetros
        String nombre = request.getParameter("txtNombre");
        String clave = request.getParameter("pswClave");
        String oculto = request.getParameter("hdnOculto");
        String semaforo = request.getParameter("rdSemaforo");
        String publicidad = request.getParameter("cbPublicidad");
        String[] listaIdiomas = request.getParameterValues("cbIdioma[]");
        String[] listaCodigosPostales = request.getParameterValues("selCodigosPostales[]");
        String comentarios = request.getParameter("txaComentarios");
        String archivo = request.getParameter("flArchivo");
        int anioFinEstudios = Integer.parseInt(request.getParameter("selAnioFinEstudios"));

        // Tratamiento de parámetros no recibidos

        if (semaforo ==null) {
            semaforo = "No se ha recibido un valor para el parametro rdsemaforo";
        }

        if (publicidad == null) {
          publicidad = "No se ha recibido un valor para el parametro cbPublicidad";
        }

        int numIdiomas;
        if (listaIdiomas == null){
            numIdiomas=0;
        } else
            numIdiomas = listaIdiomas.length;

        // Equivalente con la lista de codigos postales

        if (archivo == null) {
          archivo = "No se ha recibido un valor para el parámetro flArchivo";
        }

        // Creación de la página html de respuesta
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Eco de parámetros</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Eco de parámetros</h1>");
            out.println("<b>Respuesta enviada al cliente:</b><br/><br/>");
            out.println("<div style=\"width: 500px; float: left; border: solid 1px; margin-right: 100px;\">");
            out.println("Estos son sus datos: <br/><br/>");
            out.println("Nombre: " + nombre + " <br />");
            out.println("Contraseña: " + clave + " <br />");
            out.println("Oculto: " + oculto + " <br />");
            out.println("-------------------------- <br />");
            out.println("Semáforo: " + semaforo + " <br />");
            out.println("--------------------------- <br />");
            out.println("Publicidad: " + publicidad + " <br />");
            out.println("<br/>" +"Nº de idiomas = "+ numIdiomas + "<br/>");
            if (numIdiomas==0)
                out.println("Idiomas: No se ha recibido un valor para el parámetro cbIdiomas[]"+ "<br/>");
            for (int i = 0; i < numIdiomas; i++) {
                out.println("Idioma " + (i + 1) + ": " + listaIdiomas[i] + " <br />");
            }
            out.println("----------------------------- <br />");

            out.println("Año de fin de estudios: " + anioFinEstudios + " <br /><br />");
            for (int i = 0; i < listaCodigosPostales.length; i++) {
                out.println("Código postal " + (i + 1) + ": " + Integer.parseInt(listaCodigosPostales[i]) + " <br />");
            }
            out.println("------------------------------- <br />");
            out.println("Comentarios: " + comentarios + " <br />");
            out.println("-------------------------------- <br />");
            out.println("Archivo: " + archivo + " <br />");
            out.println("</div>");

            // Parámetros recibidos realmente

            out.println("<div style=\"float: left\">");
            out.println("<b>Parámetros recibidos realmente:</b><br/>");
            out.println("<table border='1'>");

            Enumeration <String> nombresParametros = request.getParameterNames();
            while(nombresParametros.hasMoreElements()) {
                String nombreParametro = nombresParametros.nextElement();
                out.println("<tr><td>" + nombreParametro + "</td>");
                String[] valoresParametro = request.getParameterValues(nombreParametro);
                out.println("<td>");
                if (valoresParametro.length == 0)
                    out.println("Parámetro vacío");
                else
                    if (valoresParametro.length == 1)
                        out.println(valoresParametro[0]);
                    else {
                        out.println("<ul>");
                        for (int i = 0; i < valoresParametro.length; i++)
                            out.println("<li>" + valoresParametro[i] + "</li>");
                        out.println("</ul>");
                    }
                out.println("</td></tr>");
            }
            out.println("</table>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}

