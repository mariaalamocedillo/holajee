<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Variables servidor</h1>
<%
  String documentRoot = request.getServletContext().getRealPath("/");
  String scriptName = request.getServletPath();
  String queryString = request.getQueryString();
  out.println("Document root: " + documentRoot + " <br />");
  out.println("Script name: " + scriptName + " <br />");
  out.println("Query String: " + queryString + " <br />");
%>
    </body>
</html>
