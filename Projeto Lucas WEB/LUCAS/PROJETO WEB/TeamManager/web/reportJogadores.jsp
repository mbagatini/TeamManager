<%-- 
    Document   : reportJogadores
    Created on : 28/11/2016, 03:57:42
    Author     : Lucas
--%>

<%@page import="daos.JogadorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            byte[] bytes = new JogadorDAO().gerarRelatorio();

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>


    </body>
</html>
