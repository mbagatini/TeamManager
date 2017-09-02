<%-- 
    Document   : cadastroPosicao
    Created on : 14/09/2016, 18:09:58
    Author     : Lucas
--%>

<%@page import="entidades.Posicao"%>
<%@page import="entidades.Posicao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro de posição</title>
    </head>
    <jsp:include page="navegacao.jsp" ></jsp:include>

        <h1 style="text-align:center;">Cadastro de posição</h1>

    <%
        Posicao posicao = (Posicao) request.getAttribute("posicao");
        if (posicao == null) {
            posicao = new Posicao();
            posicao.setDescricao("");
        }
    %>

    <div class="container">
        <form name="formPosicao" method="post" action="/TeamManager/acao?parametro=cadastrarPosicao" onsubmit="return validar()" >
            <input type="hidden" name="codigo" value="<%= posicao.getCodigo()%>">
            <br>
            <label >Descrição</label>
            <label style="color: red" >*</label>
            <input type="text" name="descricao" value="<%= posicao.getDescricao()%>" class="form-control" required>
            <br>
            <br>
            <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="min-width: 100px">
        </form>
    </div>

    <!-- Importacao do arquivo de validacao -->
    <script language="JavaScript" src="JS/validacaoPosicao.js"></script>
</html>
