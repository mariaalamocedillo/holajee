<%-- Directiva page --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adivinar número</title>
    </head>
    <body>
        <h1>Adivinar número</h1>

<%-- Se importa la clase BeanAdivinarNumero --%>
<%@ page import = "es.marialamo.jspejemp.AdivinarNumero.BeanAdivinarNumero" %>

<%-- Se instancia un objeto de la clase BeanAdivinarNumero, ubicada en el paquete paqueteBeans,
     y se le identifica con el nombre adivinarNumero --%>
<%-- El atributo scope indica el ámbito en el que el bean está disponible.
     Puede tener 4 valores: page (por defecto), request, session y application --%>
<jsp:useBean id="adivinarNumero" class="es.marialamo.jspejemp.AdivinarNumero.BeanAdivinarNumero" scope="session" />
<%-- Se establece el valor de una propiedad de un determinado objeto
     En este ejemplo no se da un valor --%>
<jsp:setProperty name="adivinarNumero" property="*" />

<%  if (request.getParameter("prueba")!=null)
        adivinarNumero.pruebaNumero(request.getParameter("prueba"));

    if (adivinarNumero.hasAcertado() ) { %>
	¡ Felicidades !  Has acertado. <br />
	Has necesitado <%= adivinarNumero.devuelveNumeroIntentos() %> intentos. <br />
      	<% adivinarNumero.volverAEmpezar(); %>
	¿Quiéres <a href="AdivinarNumero.jsp">probar de nuevo</a>? <br />

<% } else if (adivinarNumero.devuelveNumeroIntentos() == 0) { %>
        Bienvenido al juego de adivinar números. <br />
        Tienes que adivinar un número del 1 al 100. <br />

        <form method="get" action="AdivinarNumero.jsp">
        Número a probar <input type=text name='prueba'> <br />
        <input type='submit'>
        </form>

<% } else { %>
        Buen intento, pero no has acertado. <br />
        Intenta un número <b><%= adivinarNumero.devuelvePista() %></b>. <br />
        Llevas <%= adivinarNumero.devuelveNumeroIntentos() %> intentos. <br />
        Recuerda que tienes que adivinar un número del 1 al 100. <br />

        <form method="get" action="AdivinarNumero.jsp">
        Número a probar <input type=text name='prueba'> <br />
        <input type='submit'>
        </form>

<% } %>

</body>
</html>
