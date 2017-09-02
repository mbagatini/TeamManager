<%-- 
    Document   : cadastroAtributo
    Created on : 14/09/2016, 18:09:41
    Author     : Lucas
--%>

<%@page import="entidades.Atributo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro de atributo</title>
    </head>
    <jsp:include page="navegacao.jsp" ></jsp:include>

        <h1 style="text-align:center;">Cadastro de atributo</h1>

    <%
        Atributo atributo = (Atributo) request.getAttribute("atributo");
        if (atributo == null) {
            atributo = new Atributo();
            atributo.setDescricao("");
        }
    %>

    <div class="container">
        <form name="formAtributo" method="post" action="/TeamManager/acao?parametro=cadastrarAtributo" onsubmit="return validar()" >
            <input type="hidden" name="codigo" value="<%= atributo.getCodigo()%>">
            <br>
            <label >Descrição</label>
            <label style="color: red" >*</label>
            <input type="text" name="descricao" value="<%= atributo.getDescricao()%>" class="form-control" >
            <br>
            <br>
            <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="min-width: 100px">
        </form>
    </div>

    <!-- Importacao do arquivo de validacao -->
    <script language="JavaScript" src="JS/validacaoAtributo.js"></script>

</html>
