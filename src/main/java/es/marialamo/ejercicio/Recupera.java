package es.marialamo.ejercicio;

/*Recepciona el usuario/clave, lo comprueba frente al array de usuarios reencaminando a index.html si no existe. Si existe el
usuario, recupera la cookie si existe, y si no, pinta la ventana en el estado por defecto. Al pulsar Desconectar, se mandan a
GuardaCookie.java los datos necesarios: nombre de usuario, opción seleccionada y número de visitas.*/
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet("/Recupera")
public class Recupera extends HttpServlet {

    @Inject
    private GuardaCookie guardaCookie;

    protected void comprobarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rutaContexto = request.getContextPath();
        String vistaDestino;

        response.setContentType("text/html;charset=UTF-8");
        // se obtienen los datos y se comparan con los usuarios existentes
        Map<String, String > users = new HashMap<String, String>();
        users.put("ana", "ana1");
        users.put("jon", "jon1");
        users.put("mel", "mel1");
        String user = "";
        String clave = "";

        // Recepción de parámetros
        user = request.getParameter("usuario");
        clave = request.getParameter("clave");

        //validamos que existe del array de usuarios y contraseñas
        if (users.containsKey(user) && Objects.equals(users.get(user), clave)){
            procesaSolicitud(request, response);
        } else {// si no se ha validado al usuario, se devuelve a la página inicial
            vistaDestino = "/formEjercicio.html";
            response.sendRedirect(rutaContexto + vistaDestino);
        }

    }

    protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String user = "";
        String idioma = "";
        String visitas = "0";

        //Array asociativo con iniciales = idioma
        Map<String, String > idiomas = new HashMap<String, String>();
        idiomas.put("ES", "español");
        idiomas.put("EN", "inglés");
        idiomas.put("PT", "portugués");

        user = request.getParameter("usuario");


        out.println("<html>");
        out.print("<head>");
        out.println("<title>Inicio usuario </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Crea y Recupera</h1>");
        out.println("<p>Bienvenido, <b>aprendiz</b></p>");

        try {
            String[] infoCookies;
            String refIdioma;

            infoCookies = guardaCookie.compruebaCookies(request, user);

            out.println("<form name=\"f1\" action=\"guardaCookie\">");
            //si no devuelve el valor 0, significa que sí tiene cookie
            if (infoCookies[0].equals("0")){
                int num = Integer.parseInt(visitas);
                visitas = Integer.toString(num + 1);
                idioma = "PT";  //ponemos este valor, que es el asignado por defecto
            } else {    //si no devuelve cookie, se hace normal
                visitas = infoCookies[0];
                idioma = infoCookies[1];
            }
            out.println("<input type=\"text\" id=\"usuario\" name=\"usuario\" value=\"" + user + "\" hidden>");
            out.println("<input type=\"text\" id=\"visitas\" name=\"visitas\" value=\"" + visitas + "\" hidden>");

            refIdioma = idioma;
            //campo de idioma
            out.print("<fieldset name=\"idioma\">");
            idiomas.forEach((k, v) -> {
                out.println("<label><input type=\"radio\" name=\"idioma\" value=\"" + k + "\"");
                //si la clave es igual al idioma almacenado o no hay idioma almacenado
                if (Objects.equals(k, refIdioma)){
                    out.print(" checked/>"+ v +"</label>");
                }else{
                    out.print("/>"+ v +"</label>");
                }
            });
            out.print(" </fieldset>");

            //campo con visitas
            out.println("<input type=\"submit\" value=\"Desconectar\" />\n");
            out.print("</form>");

            out.println("<p>Nº de visitas: " + visitas + "</p>");

            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e){
            out.println("Se produce una excepción <br/>");
            e.printStackTrace(out);
        }
        finally {
            out.close();
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        comprobarUsuario(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        comprobarUsuario(request, response);
    }

}