<%-- 
    Document   : menu
    Created on : 05/09/2016, 21:29:45
    Author     : Lucas
--%>

<%@page import="entidades.Informacoes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <%
        Informacoes informacao = new Informacoes();
    %>

    <jsp:include page="navegacao.jsp" ></jsp:include>    
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>Team Manager</title>
        </head>
        <body background="Images/ball_grass.jpg" >
        <%
            // verificando se tem um atributo login na sessao
            // se houver vai continuar e mostrar a pagina
            if (session.getAttribute("usuarioLogado") != null) {
        %>
        <br>
        <div class="container">
            <div class="jumbotron">
                <h1 style="text-align:center" >Bem-vindo ao Team Manager</h1>
                <h2 style="text-align:center" >O portal de gerenciamento do Carlos Barbosa Ximangos</h2>
            </div>
            <br>
            <div class="row">
                <div class="col-md-6">
                    <div class="jumbotron" style="height: 500px" >
                        <h1>Último Jogo:</h1>
                        <br>
                        <h3>XIMANGOS: 25</h3>
                        <h3>BULLDOGS: 15</h3>
                        <br>
                        <br>
                        <h1>Jogadores: <%= informacao.getJogadores() %></h1>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="jumbotron" style="height: 500px">
                        <h1 style="color: darkgreen" >Vitórias: <%= informacao.getVitorias()%> </h1>
                        <br>
                        <h1 style="color: #c1c162" >Empates: <%= informacao.getEmpates()%></h1>
                        <br>
                        <h1 style="color: red" >Derotas: <%= informacao.getDerrotas()%></h1>
                    </div>
                </div>
            </div>
            <%
                // se não existir um login na sessao,
                // vai enviar para a página de login novamente
            } else {
            %>
            <jsp:forward page="index.jsp"></jsp:forward>
            <%
                }
            %>
    </body>
</html>
