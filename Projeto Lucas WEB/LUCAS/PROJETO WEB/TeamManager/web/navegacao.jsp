<%-- 
    Document   : navegacao
    Created on : 15/09/2016, 21:27:05
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="shortcut icon" href="http://downloadclipart.org/do-upload/clipart/2016-07/American_football_icon.png"> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Team Manager</title>
        <!-- Bootstrap core CSS -->
        <link href="boots/css/signin.css" rel="stylesheet">
        <link href="boots/css/bootstrap.min.css" rel="stylesheet">
        <link href="boots/css/navbar-fixed-top.css" rel="stylesheet" type="text/css"/>
        <link href="boots/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="boots/css/jquery.dataTables.min.css" rel="stylesheet">
        <link href="boots/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="boots/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                            data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="menu.jsp">Team Manager</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                               aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-edit"></span> Cadastros <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="cadastroJogador.jsp">Jogadores</a></li>
                                <li><a href="cadastroAtributo.jsp">Atributos</a></li>
                                <li><a href="cadastroPosicao.jsp">Posições</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="cadastroJogo.jsp">Jogos</a></li>
                                <li><a href="cadastroAdversario.jsp">Adversários</a></li>
                                <li><a href="cadastroCompeticao.jsp">Competições</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="cadastroCidade.jsp">Cidades</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-list"></span> Listagens <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="listagemJogadores.jsp">Jogadores</a></li>
                                <li><a href="listagemAtributos.jsp">Atributos</a></li>
                                <li><a href="listagemPosicoes.jsp">Posições</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="listagemJogos.jsp">Jogos</a></li>
                                <li><a href="listagemAdversarios.jsp">Adversários</a></li>
                                <li><a href="listagemCompeticoes.jsp">Competições</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="listagemCidades.jsp">Cidades</a></li>
                            </ul>
                        </li>                        
                        <li><a href="jogadas.jsp"><span class="glyphicon glyphicon-list-alt"></span> Jogadas</a></li>
                        <li><a href="formacoes.jsp"><span class="glyphicon glyphicon-blackboard"></span> Formações</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/TeamManager/acao?parametro=logout"><span class="glyphicon glyphicon-log-out"></span>  Log out</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>

    <script src="boots/js/jquery.min.js" type="text/javascript"></script>
    <script src="boots/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="bower_components/jquery/dist/jquery.min.js"></script>
</html>
