<%@page import="entidades.Competicao"%>
<%@page import="entidades.TipoCompeticao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        Boolean isedit = true;
        Competicao competicao = (Competicao) request.getAttribute("competicao");
        if (competicao == null) {
            isedit = false;
            competicao = new Competicao();
            competicao.setDescricao("");
            TipoCompeticao tipo = new TipoCompeticao('X');
            competicao.setTipo(tipo);
        }
    %>
    <head>
        <script type="text/javascript" DEFER="DEFER">
            function setTipo() {
                $("#tipo").val("<%= competicao.getTipo().getCodigo()%>");
                //$("#tipo").val("<%= competicao.getTipo().getCodigo()%>");

                //var tipo = document.getElementById("tipo");
                //tipo.options[tipo.options.selectedIndex].selected = true;
            }
            // Chamada da função
            $(document).ready(function () {
                setTipo();
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro de competições</title>
    </head>
    <body>
        <jsp:include page="navegacao.jsp" ></jsp:include>
            <h1 style="text-align:center;">Cadastro de competição</h1>
            <div class="container">
                <form name="formCompeticao" method="post" action="/TeamManager/acao?parametro=cadastroCompeticao" onsubmit="return validar()" >
                    <input type="hidden" name="codigo" value="<%= competicao.getCodigo()%>">
                <br/>
                <label >Descrição</label>
                <label style="color: red" >*</label>
                <input type="text" name="descricao" style="text-transform: uppercase;" value="<%= competicao.getDescricao()%>" class="form-control" required>
                <br/>
                <label >Tipo</label>
                <label style="color: red" >*</label>
                <select class="form-control" name="tipo" id="tipo" >
                    <option value="X" <% if (competicao.getTipo().getCodigo() == 'X') { %> selected <% } %> >SELECIONE</option>
                    <option value="1" <% if (competicao.getTipo().getCodigo() == '1') { %> selected <% } %> >AMISTOSO</option>
                    <option value="2" <% if (competicao.getTipo().getCodigo() == '2') { %> selected <% } %> >ESTADUAL</option>
                    <option value="3" <% if (competicao.getTipo().getCodigo() == '3') { %> selected <% } %> >REGIONAL</option>
                    <option value="4" <% if (competicao.getTipo().getCodigo() == '4') { %> selected <% } %> >NACIONAL</option>
                </select>
                <br/>
                <br/>
                <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="min-width: 100px">
            </form>
        </div>
    </body>
    <!-- Importacao do arquivo de validacao -->
    <script language="JavaScript" src="JS/validacaoAdversario.js"></script>
</html>
