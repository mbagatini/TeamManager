<%@page import="entidades.PosicaoJogador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.*"%>
<%@page import="daos.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        Jogo jogo = (Jogo) request.getAttribute("jogo");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro dos pontos do jogo</title>
    </head>
    <jsp:include page="navegacao.jsp" ></jsp:include>
        <body >
            <h1 style="text-align:center;">Cadastro dos pontos do jogo</h1>
            <br>
            <div class="container">
                <form name="formJogo" method="post" action="/TeamManager/acao?parametro=atualizarJogo" >
                    <input type="hidden" name="codigo" value="<%= jogo.getCodigo()%>">
                <div class="row">
                    <div class="col-md-3">
                        <label >Pontuação do time</label>
                        <input type="text" name="pontostime" style="max-width: 70px" value="<%= jogo.getPontuacaotime()%>" class="form-control" required>
                    </div>
                    <div class="col-md-3">
                        <label >Pontuação do adversário</label>
                        <input type="text" name="pontosadversario" style="max-width: 70px" value="<%= jogo.getPontuacaoadversario()%>" class="form-control" required>
                    </div>
                </div>
                <br>
                <br>
                <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="">
            </form>
        </div>
    </body>

</html>
