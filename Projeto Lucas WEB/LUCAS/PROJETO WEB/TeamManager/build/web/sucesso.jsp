<%-- 
    Document   : sucesso
    Created on : 19/09/2016, 22:21:49
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Sucesso</title>
    </head>
    <%@include file ="navegacao.jsp" %>
    <body>
        <div class="container">
            <h1>Sucesso!</h1>
            <%
                String pagina = (String) request.getAttribute("paginaRetorno");
            %>
            <a href='<%=pagina%>'>Voltar para a página anterior</a>
            <br>
            <a href='menu.jsp'>Volta para o início</a>
        </div>
    </body>
</html>
