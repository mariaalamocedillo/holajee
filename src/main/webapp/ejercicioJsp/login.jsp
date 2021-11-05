<%-- Directiva page --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8" />
    <title>Formulario contador de visitas</title>
</head>
<body>

<%-- Se importa la clase BeanAdivinarNumero--%>
<%@ page import = "es.marialamo.ejercicioJsp.ComprobarUser" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.util.Objects" %>

<%-- Se instancia un objeto de la clase BeanAdivinarNumero, ubicada en el paquete paqueteBeans,
     y se le identifica con el nombre adivinarNumero --%>
<%-- El atributo scope indica el ámbito en el que el bean está disponible.
     Puede tener 4 valores: page (por defecto), request, session y application --%>
<jsp:useBean id="comprobarUser" class="es.marialamo.ejercicioJsp.ComprobarUser" scope="session" />
<%-- Se establece el valor de una propiedad de un determinado objeto
     En este ejemplo no se da un valor --%>
<jsp:setProperty name="comprobarUser" property="*" />
<%  if (request.getParameter("username")==null){%>
<form action="login.jsp" method="POST">
        UserName: <input type="text" name="username">
        <br />
        Password: <input type="password" name="password" />
        <input type="submit" value="Submit" />
</form>
<% } else if (comprobarUser.check(request.getParameter("username"), request.getParameter("password"))) {%>
<p>Bienvenido, <b>aprendiz</b></p>
<%
    String[] contenidoCookie;
    Cookie[] cookies = null;

    cookies = request.getCookies();
    contenidoCookie = comprobarUser.checkCookies(cookies,request.getParameter("username")).split("-");
    String idioma = contenidoCookie[1];
    int visitas = Integer.parseInt(contenidoCookie[0]);
%>
<form name="f1" action="desconectar.jsp">
  <%--mostramos la botonera de idiomas--%>
<%    String[] idiomas = {"español", "inglés", "portugués"};
    for (String elemento: idiomas) {
        if (Objects.equals(elemento, idioma)){%>
    <label><input type="radio" name="idioma" value="<%= elemento%>" checked/><%= elemento%></label>
        <%}else{ %>
    <label><input type="radio" name="idioma" value="<%= elemento%>" checked/><%= elemento%></label>
        <%}
    };
    %>
    <p>Nº de visitas: <%= visitas %></p>
      <input type="text" id="usuario" name="usuario" value="<%= request.getParameter("username") %>" hidden>
      <% visitas += 1; %>   <%--añadimos una visita aquí al valor de las visitas (así cuando desconecte ya está el valor necesario asignado--%>
      <input type="text" id="visitas" name="visitas" value="<%= visitas %>" hidden>
    <input type="submit" value="Desconectar" />
</form>
<% } else {%>
<form action="login.jsp" method="POST">
    UserName: <input type="text" name="username">
    <br />
    Password: <input type="password" name="password" />
    <input type="submit" value="Submit" />
</form>
<% }%>
    </body>
</html>
