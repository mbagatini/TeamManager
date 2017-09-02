<%-- 
    Document   : index
    Created on : 05/09/2016, 19:49:59
    Author     : lucas.motta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="http://downloadclipart.org/do-upload/clipart/2016-07/American_football_icon.png"> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Team Manager</title>
        <!-- Bootstrap core CSS -->
        <link href="boots/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template -->
        <link href="boots/css/signin.css" rel="stylesheet">
    </head>
    <body>
        <h1 style="text-align:center;">Bem-vindo ao Team Manager</h1>
        <div class="container">
            <form name="login" method="post" action="/TeamManager/acao?parametro=login" class="form-signin" onsubmit="return validar()">
                <br>
                <label >Usuário</label>
                <input type="email" name="emailUsuario" class="form-control" id="inputEmail" required >
                <br>
                <label>Senha</label>
                <input type="password" name="senha" class="form-control" id="inputPassword" required >
                <br>
                <input type="submit" value="Acessar" class="btn btn-lg btn-primary btn-block">
            </form>
        </div>
    </body>
    <!-- Importacao do arquivo de validacao -->
    <script language="JavaScript" src="JS/validacaoLogin.js"></script>

</html>
