<%@page import="entidades.Cidade"%>
<%@page import="entidades.Adversario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%
        Adversario adversario = (Adversario) request.getAttribute("adversario");
        if (adversario == null) {
            adversario = new Adversario();
            adversario.setNome("");
            adversario.setTelefone("");
            Cidade cidade = new Cidade();
            cidade.setUf("X");
            cidade.setCodigo(0);
            adversario.setCidade(cidade);
        }
    %>

    <head>
        <script type="text/javascript" DEFER="DEFER">
            function popular() {
                document.getElementById("uf").click();
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro de adversários</title>
    </head>
    <jsp:include page="navegacao.jsp"></jsp:include>
        <body onload="popular()">
            <h1 style="text-align:center;">Cadastro de Adversário</h1>
            <form name="formAdversario" method="post" action="/TeamManager/acao?parametro=cadastroAdversario" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <input type="hidden" name="codigo" value="<%= adversario.getCodigo()%>">
                        <br>
                        <label >Nome</label><label style="color: red" >*</label>
                        <input type="text" name="nome" style="text-transform: uppercase;" placeholder="EX: NOME DO TIME ADVERSÁRIO" maxlength="45" value="<%= adversario.getNome()%>" class="form-control" required>
                        <br>
                        <label >Telefone</label><label style="color: red" >*</label>
                        <input type="text" name="telefone" placeholder="EX: (55)99999-9999" id="telefone" value="<%= adversario.getTelefone()%>" style="max-width: 200px" maxlength="14" class="form-control" required>
                        <br>
                    </div>

                    <div class="col-md-6">
                        <br>
                        <label >Estado</label><label style="color: red" >*</label>
                        <select class="form-control" onclick="prencherCidade(<%= adversario.getCidade().getCodigo()%>)" 
                                onChange="prencherCidade(<%= adversario.getCidade().getCodigo()%>)" style="max-width: 400px" name="uf" id="uf" >
                            <option value="X"  <% if (adversario.getCidade().getUf().equals("X")) { %> selected <% } %> >Selecione</option>
                            <option value="AC" <% if (adversario.getCidade().getUf().equals("AC")) { %> selected <% } %> >Acre</option>
                            <option value="AL" <% if (adversario.getCidade().getUf().equals("AL")) { %> selected <% } %> >Alagoas</option>
                            <option value="AP" <% if (adversario.getCidade().getUf().equals("AP")) { %> selected <% } %> >Amapá</option>
                            <option value="AM" <% if (adversario.getCidade().getUf().equals("AM")) { %> selected <% } %> >Amazonas</option>
                            <option value="BA" <% if (adversario.getCidade().getUf().equals("BA")) { %> selected <% } %> >Bahia</option>
                            <option value="CE" <% if (adversario.getCidade().getUf().equals("CE")) { %> selected <% } %> >Ceará</option>
                            <option value="DF" <% if (adversario.getCidade().getUf().equals("DF")) { %> selected <% } %> >Distrito Federal</option>
                            <option value="ES" <% if (adversario.getCidade().getUf().equals("ES")) { %> selected <% } %> >Espírito Santo</option>
                            <option value="GO" <% if (adversario.getCidade().getUf().equals("GO")) { %> selected <% } %> >Goiás</option>
                            <option value="MA" <% if (adversario.getCidade().getUf().equals("MA")) { %> selected <% } %> >Maranhão</option>
                            <option value="MT" <% if (adversario.getCidade().getUf().equals("MT")) { %> selected <% } %> >Mato Grosso</option>
                            <option value="MS" <% if (adversario.getCidade().getUf().equals("MS")) { %> selected <% } %> >Mato Grosso do Sul</option>
                            <option value="MG" <% if (adversario.getCidade().getUf().equals("MG")) { %> selected <% } %> >Minas Gerais</option>
                            <option value="PA" <% if (adversario.getCidade().getUf().equals("PA")) { %> selected <% } %> >Pará</option>
                            <option value="PB" <% if (adversario.getCidade().getUf().equals("PB")) { %> selected <% } %> >Paraíba</option>
                            <option value="PR" <% if (adversario.getCidade().getUf().equals("PR")) { %> selected <% } %> >Paraná</option>
                            <option value="PE" <% if (adversario.getCidade().getUf().equals("PE")) { %> selected <% } %> >Pernambuco</option>
                            <option value="PI" <% if (adversario.getCidade().getUf().equals("PI")) { %> selected <% } %> >Piauí</option>
                            <option value="RJ" <% if (adversario.getCidade().getUf().equals("RJ")) { %> selected <% } %> >Rio de Janeiro</option>
                            <option value="RN" <% if (adversario.getCidade().getUf().equals("RN")) { %> selected <% } %> >Rio Grande do Norte</option>
                            <option value="RS" <% if (adversario.getCidade().getUf().equals("RS")) { %> selected <% } %> >Rio Grande do Sul</option>
                            <option value="RO" <% if (adversario.getCidade().getUf().equals("RO")) { %> selected <% } %> >Rondônia</option>
                            <option value="RR" <% if (adversario.getCidade().getUf().equals("RR")) { %> selected <% } %> >Roraima</option>
                            <option value="SC" <% if (adversario.getCidade().getUf().equals("SC")) { %> selected <% } %> >Santa Catarina</option>
                            <option value="SP" <% if (adversario.getCidade().getUf().equals("SP")) { %> selected <% } %> >São Paulo</option>
                            <option value="SE" <% if (adversario.getCidade().getUf().equals("SE")) { %> selected <% } %> >Sergipe</option>
                            <option value="TO" <% if (adversario.getCidade().getUf().equals("TO")) { %> selected <% }%> >Tocantins</option>
                        </select>
                        <br>

                        <label >Cidade</label><label style="color: red" >*</label>
                        <select class="form-control" style="max-width: 400px" name="cidade" id="cidade">
                            <option value="0" >Selecione</option>
                        </select>
                        <br>
                    </div>
                </div>
            </div>
            <div class="container">
                <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="min-width: 100px">
            </div>
        </form>
    </body>

    <!-- Importacao do arquivo de validacao -->
    <script language="JavaScript" src="JS/popularCidade.js"></script>
    <script language="JavaScript" src="JS/validacaoAdversario.js"></script>

</html>
