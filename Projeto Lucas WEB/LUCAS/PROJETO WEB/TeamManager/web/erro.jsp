<%-- 
    Document   : erro
    Created on : 05/09/2016, 21:53:04
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Erro</title>
    </head>
    <%@include file ="navegacao.jsp" %>
    <body>
        <h1>Aconteceu um erro!</h1>


        <%
            String pagina = (String) request.getAttribute("paginaRetorno");
        %>

        <a href='<%=pagina%>'>Volta para o cadastro</a>
        <br>
        <a href='index.jsp'>Volta para o Início</a>
    </body>
</html>
