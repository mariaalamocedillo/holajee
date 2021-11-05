<%@ page import="jakarta.servlet.http.Cookie" %><%-- Directiva page --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Se instancia un objeto de la clase BeanAdivinarNumero, ubicada en el paquete paqueteBeans,
     y se le identifica con el nombre adivinarNumero --%>
<%-- El atributo scope indica el ámbito en el que el bean está disponible.
     Puede tener 4 valores: page (por defecto), request, session y application --%>
<%-- Se establece el valor de una propiedad de un determinado objeto
     En este ejemplo no se da un valor --%>
<%  if (request.getParameter("usuario")!=null && request.getParameter("idioma")!=null
        &&  request.getParameter("visitas")!=null){
    String user = request.getParameter("usuario");
    String visitas = request.getParameter("visitas");
    String idioma = request.getParameter("idioma");

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
%>

<jsp:forward page = "login.jsp" />

<% }%>
