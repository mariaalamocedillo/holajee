package es.marialamo.ejercicio;

/*No se visualiza nada.
Se crea y manda al cliente la cookie con la información necesaria.
Se redirecciona a index.html*/


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/guardaCookie")
public class GuardaCookie extends HttpServlet {
    //hacer que reciba la info de una cookie y use el método de creacookie
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        finalizaCookies(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        finalizaCookies(request, response);
    }
//al desconectar, se realiza este método; se saca los datos del formulario y se crea una cookie con ellos, luego manda al formulario de nuevo
    protected void finalizaCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rutaContexto = request.getContextPath();
        String vistaDestino;
        // Recepción de parámetros
        String user = "";
        String visitas = "";
        String idioma = "";

        user = request.getParameter("usuario");
        visitas = request.getParameter("visitas");
        idioma = request.getParameter("idioma");

        Integer num = Integer.parseInt(visitas);
        visitas = Integer.toString(num + 1);

        creaCookies(response, user, visitas, idioma);

        vistaDestino = "/formEjercicio.html";
        response.sendRedirect(rutaContexto + vistaDestino);

    }


    //método que devuelve un array de String con el idioma y número de visitas almacenados en cookies
    protected String[] compruebaCookies(HttpServletRequest request, String user){
        String[] partes = new String[0];
        Boolean tieneCookie = false; //para comprobar si tiene o no cookie
        try {
            int i;

            // Recepción de cookies
            Cookie[] arrayCookies = request.getCookies();
            int longitud = arrayCookies.length;

            //si hay cookies guardadas, busca la cookie del nombre de usuario y obtiene su contenido
            if (arrayCookies!=null && longitud>0) {
                for (i=0; i < longitud; i++) {
                    String nombreCookie = arrayCookies[i].getName();

                    if (nombreCookie.equals(user)){
                        String contenidoCookie = arrayCookies[i].getValue();
                        partes = contenidoCookie.split("-");
                        tieneCookie = true;
                        break;
                    }
                }
            }
            if (tieneCookie){
                return partes;
            } else {    //el 0 nos indicará que no hay datos válidos
                return new String[]{"0"};
            }
        } 
        catch (Exception e){ 
            return new String[]{"0"};
        }
    }
//método que crea una cookie con los datos recibidos
    protected void creaCookies(HttpServletResponse response,
                               String user, String visitas, String idioma){

        //establecemos el valor del contenido, que son las visitas con el idioma separado por un guión
        String contenidoCookie = visitas + "-" + idioma;

        // se inicializa un objeto Cookie "vacío"
        Cookie nuevaCookie = null;

        try {
            // se crea el objeto cookie en el servidor
            nuevaCookie = new Cookie(user, contenidoCookie);

            // se añade a la respuesta para enviar al cliente
            response.addCookie(nuevaCookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
