<%-- 
    Document   : cadastroPosicao
    Created on : 14/09/2016, 18:09:58
    Author     : Lucas
--%>

<%@page import="entidades.Cidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <jsp:include page="navegacao.jsp" ></jsp:include>
            <title>Cadastro de cidade</title>
        </head>
        <body>
            <h1 style="text-align:center;">Cadastro de cidade</h1>
            <%
                Cidade cidade = (Cidade) request.getAttribute("cidade");
                if (cidade == null) {
                    cidade = new Cidade();
                    cidade.setNome("");
                    cidade.setUf("X");
                }
            %>
        <div class="container">
            <form name="formCidade" method="post" action="/TeamManager/acao?parametro=cadastroCidade" onsubmit="return validar()" >
                <input type="hidden" name="codigo" value="<%= cidade.getCodigo()%>">
                <br>
                <label >Descrição</label>
                <label style="color: red" >*</label>
                <input type="text" name="nome" style="text-transform: uppercase;" value="<%= cidade.getNome()%>" class="form-control" required>
                <br>
                <label >Estado</label>
                <label style="color: red" >*</label>
                <select class="form-control" name="uf">
                    <option value="X"  <% if (cidade.getUf().equals("X"))  { %> selected <% } %> >Selecione</option>
                    <option value="AC" <% if (cidade.getUf().equals("AC")) { %> selected <% } %> >Acre</option>
                    <option value="AL" <% if (cidade.getUf().equals("AL")) { %> selected <% } %> >Alagoas</option>
                    <option value="AP" <% if (cidade.getUf().equals("AP")) { %> selected <% } %> >Amapá</option>
                    <option value="AM" <% if (cidade.getUf().equals("AM")) { %> selected <% } %> >Amazonas</option>
                    <option value="BA" <% if (cidade.getUf().equals("BA")) { %> selected <% } %> >Bahia</option>
                    <option value="CE" <% if (cidade.getUf().equals("CE")) { %> selected <% } %> >Ceará</option>
                    <option value="DF" <% if (cidade.getUf().equals("DF")) { %> selected <% } %> >Distrito Federal</option>
                    <option value="ES" <% if (cidade.getUf().equals("ES")) { %> selected <% } %> >Espírito Santo</option>
                    <option value="GO" <% if (cidade.getUf().equals("GO")) { %> selected <% } %> >Goiás</option>
                    <option value="MA" <% if (cidade.getUf().equals("MA")) { %> selected <% } %> >Maranhão</option>
                    <option value="MT" <% if (cidade.getUf().equals("MT")) { %> selected <% } %> >Mato Grosso</option>
                    <option value="MS" <% if (cidade.getUf().equals("MS")) { %> selected <% } %> >Mato Grosso do Sul</option>
                    <option value="MG" <% if (cidade.getUf().equals("MG")) { %> selected <% } %> >Minas Gerais</option>
                    <option value="PA" <% if (cidade.getUf().equals("PA")) { %> selected <% } %> >Pará</option>
                    <option value="PB" <% if (cidade.getUf().equals("PB")) { %> selected <% } %> >Paraíba</option>
                    <option value="PR" <% if (cidade.getUf().equals("PR")) { %> selected <% } %> >Paraná</option>
                    <option value="PE" <% if (cidade.getUf().equals("PE")) { %> selected <% } %> >Pernambuco</option>
                    <option value="PI" <% if (cidade.getUf().equals("PI")) { %> selected <% } %> >Piauí</option>
                    <option value="RJ" <% if (cidade.getUf().equals("RJ")) { %> selected <% } %> >Rio de Janeiro</option>
                    <option value="RN" <% if (cidade.getUf().equals("RN")) { %> selected <% } %> >Rio Grande do Norte</option>
                    <option value="RS" <% if (cidade.getUf().equals("RS")) { %> selected <% } %> >Rio Grande do Sul</option>
                    <option value="RO" <% if (cidade.getUf().equals("RO")) { %> selected <% } %> >Rondônia</option>
                    <option value="RR" <% if (cidade.getUf().equals("RR")) { %> selected <% } %> >Roraima</option>
                    <option value="SC" <% if (cidade.getUf().equals("SC")) { %> selected <% } %> >Santa Catarina</option>
                    <option value="SP" <% if (cidade.getUf().equals("SP")) { %> selected <% } %> >São Paulo</option>
                    <option value="SE" <% if (cidade.getUf().equals("SE")) { %> selected <% } %> >Sergipe</option>
                    <option value="TO" <% if (cidade.getUf().equals("TO")) { %> selected <% } %> >Tocantins</option>
                </select>
                <br>
                <br>
                <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="min-width: 100px">
            </form>
            <hr>
        </div>
    </body>x
    <!-- Importacao do arquivo de validacao -->
    <script language="JavaScript" src="JS/validacaoCidade.js"></script>
</html>
