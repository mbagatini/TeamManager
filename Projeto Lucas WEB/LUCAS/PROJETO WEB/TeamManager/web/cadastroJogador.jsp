<%@page import="java.util.ArrayList"%>
<%@page import="entidades.*"%>
<%@page import="daos.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%
        Jogador jogador = (Jogador) request.getAttribute("jogador");
        if (jogador == null) {
            jogador = new Jogador();
            jogador.setCodigo(0);
            jogador.setNome("");
            jogador.setTelefone("");
            Cidade cidade = new Cidade();
            cidade.setUf("X");
            cidade.setCodigo(0);
            jogador.setCidade(cidade);
            jogador.setNascimento(null);
        }
    %>
    <head>
        <script type="text/javascript" DEFER="DEFER">
            function popular() {
                document.getElementById("uf").click();
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro de jogador</title>
    </head>
    <jsp:include page="navegacao.jsp" ></jsp:include>


        <body onload="popular()">
            <h1 style="text-align:center;">Cadastro de Jogador</h1>
            <form name="formJogador" method="post" action="/TeamManager/acao?parametro=cadastroJogador" onsubmit="return validar()" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <input type="hidden" name="codigo" value="<%= jogador.getCodigo()%>"><br>
                        <label >Nome</label><label style="color: red" >*</label>
                        <input type="text" name="nome" id="nome" maxlength="45" placeholder="EX: FULANO DE TAL" value="<%= jogador.getNome()%>" class="form-control" required ><br>

                        <label >Nascimento</label><label style="color: red" >*</label>
                        <input type="date" name="nascimento" id="nome" value="<%= jogador.getNascimento()%>" style="max-width: 200px" class="form-control" required ><br>

                        <label >Telefone</label><label style="color: red" >*</label>
                        <input type="" name="telefone" id="telefone" maxlength="14" placeholder="EX: (54)99999-9999" value="<%= jogador.getTelefone()%>" style="max-width: 200px" class="form-control" required ><br>

                        <label >Número</label><label style="color: red" >*</label>
                        <input type="text" id="numero" name="numero" maxlength="2" value="<%= jogador.getNumero()%>" style="max-width: 200px" class="form-control" required ><br>

                    </div>
                    <div class="col-md-6">
                        <br>
                        <label >Peso</label><label style="color: red" >*</label>
                        <input type="text" id="peso" name="peso" maxlength="5" value="<%= jogador.getPeso()%>" style="max-width: 200px" class="form-control" required ><br>

                        <label >Altura</label><label style="color: red" >*</label>
                        <input type="text" id="altura" name="altura" maxlength="4" value="<%= jogador.getAltura()%>" style="max-width: 200px" class="form-control" required ><br>

                        <label >Estado</label><label style="color: red">*</label>
                        <select class="form-control" style="max-width: 400px" onclick="prencherCidade(<%= jogador.getCidade().getCodigo()%>)" 
                                onChange="prencherCidade(<%= jogador.getCidade().getCodigo()%>)" name="uf" id="uf">
                            <option value="X"  <% if (jogador.getCidade().getUf().equals("X")) { %> selected <% } %> >Selecione</option>
                            <option value="AC" <% if (jogador.getCidade().getUf().equals("AC")) { %> selected <% } %> >Acre</option>
                            <option value="AL" <% if (jogador.getCidade().getUf().equals("AL")) { %> selected <% } %> >Alagoas</option>
                            <option value="AP" <% if (jogador.getCidade().getUf().equals("AP")) { %> selected <% } %> >Amapá</option>
                            <option value="AM" <% if (jogador.getCidade().getUf().equals("AM")) { %> selected <% } %> >Amazonas</option>
                            <option value="BA" <% if (jogador.getCidade().getUf().equals("BA")) { %> selected <% } %> >Bahia</option>
                            <option value="CE" <% if (jogador.getCidade().getUf().equals("CE")) { %> selected <% } %> >Ceará</option>
                            <option value="DF" <% if (jogador.getCidade().getUf().equals("DF")) { %> selected <% } %> >Distrito Federal</option>
                            <option value="ES" <% if (jogador.getCidade().getUf().equals("ES")) { %> selected <% } %> >Espírito Santo</option>
                            <option value="GO" <% if (jogador.getCidade().getUf().equals("GO")) { %> selected <% } %> >Goiás</option>
                            <option value="MA" <% if (jogador.getCidade().getUf().equals("MA")) { %> selected <% } %> >Maranhão</option>
                            <option value="MT" <% if (jogador.getCidade().getUf().equals("MT")) { %> selected <% } %> >Mato Grosso</option>
                            <option value="MS" <% if (jogador.getCidade().getUf().equals("MS")) { %> selected <% } %> >Mato Grosso do Sul</option>
                            <option value="MG" <% if (jogador.getCidade().getUf().equals("MG")) { %> selected <% } %> >Minas Gerais</option>
                            <option value="PA" <% if (jogador.getCidade().getUf().equals("PA")) { %> selected <% } %> >Pará</option>
                            <option value="PB" <% if (jogador.getCidade().getUf().equals("PB")) { %> selected <% } %> >Paraíba</option>
                            <option value="PR" <% if (jogador.getCidade().getUf().equals("PR")) { %> selected <% } %> >Paraná</option>
                            <option value="PE" <% if (jogador.getCidade().getUf().equals("PE")) { %> selected <% } %> >Pernambuco</option>
                            <option value="PI" <% if (jogador.getCidade().getUf().equals("PI")) { %> selected <% } %> >Piauí</option>
                            <option value="RJ" <% if (jogador.getCidade().getUf().equals("RJ")) { %> selected <% } %> >Rio de Janeiro</option>
                            <option value="RN" <% if (jogador.getCidade().getUf().equals("RN")) { %> selected <% } %> >Rio Grande do Norte</option>
                            <option value="RS" <% if (jogador.getCidade().getUf().equals("RS")) { %> selected <% } %> >Rio Grande do Sul</option>
                            <option value="RO" <% if (jogador.getCidade().getUf().equals("RO")) { %> selected <% } %> >Rondônia</option>
                            <option value="RR" <% if (jogador.getCidade().getUf().equals("RR")) { %> selected <% } %> >Roraima</option>
                            <option value="SC" <% if (jogador.getCidade().getUf().equals("SC")) { %> selected <% } %> >Santa Catarina</option>
                            <option value="SP" <% if (jogador.getCidade().getUf().equals("SP")) { %> selected <% } %> >São Paulo</option>
                            <option value="SE" <% if (jogador.getCidade().getUf().equals("SE")) { %> selected <% } %> >Sergipe</option>
                            <option value="TO" <% if (jogador.getCidade().getUf().equals("TO")) { %> selected <% }%> >Tocantins</option>
                        </select>
                        <br>
                        <label >Cidade</label><label style="color: red" >*</label>
                        <select class="form-control" style="max-width: 400px" name="cidade" id="cidade">
                            <option value="X" >Selecione</option>
                        </select>
                    </div>
                </div>
            </div>
                        <br>
                        <br>
            <div class="container">
                <div class="row">
                    <div  class="col-md-6">
                        <label style="font-size: 24px" >Posições</label><label style="color: red; font-size: 24px" >*</label>
                        <br>
                        <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="tabelajogadores">
                            <thead>
                                <tr>
                                    <th>Principal</th>
                                    <th>Possível</th>
                                    <th>Posicão</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Object> posicoes = new PosicaoDAO().consultarTodos();
                                    ArrayList<PosicaoJogador> posicoesjogador = jogador.getPosicoesJogador();
                                    for (int i = 0; i < posicoes.size(); i++) {
                                        Posicao posicao = (Posicao) posicoes.get(i);
                                        boolean existe = false;
                                        boolean principal = false;
                                        if (jogador.getCodigo() != 0) {
                                            for (int j = 0; j < posicoesjogador.size(); j++) {
                                                if (posicao.getCodigo() == posicoesjogador.get(j).getPosicao().getCodigo()) {
                                                    existe = true;
                                                    principal = posicoesjogador.get(j).getPrincipal();
                                                    break;
                                                }
                                            }
                                        }
                                %>

                                <tr>
                                    <td><input type="radio" <% if (principal) { %> Checked <% }%> name="principal" id="principal" value="<%= posicao.getCodigo()%>"></td>
                                    <td><input type="checkbox" name="posicao" <% if (existe) { %> Checked <% }%> value="<%= posicao.getCodigo()%>"></td>
                                    <td><%= posicao.getDescricao()%></td>
                                </tr>

                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>   
                    <div  class="col-md-6">
                        <label style="font-size: 24px" >Atributos</label><label style="color: red; font-size: 24px" >*</label>
                        <br>
                        <%
                            ArrayList<Object> atributos = new AtributoDAO().consultarTodos();
                            ArrayList<AtributoJogador> atributosjogador = jogador.getAtributosJogador();
                            for (int i = 0; i < atributos.size(); i++) {
                                Atributo atributo = (Atributo) atributos.get(i);
                                String valor = "";
                                if (jogador.getCodigo() != 0) {
                                    for (int j = 0; j < atributosjogador.size(); j++) {
                                        if (atributo.getCodigo() == atributosjogador.get(j).getAtributo().getCodigo()) {
                                            valor += atributosjogador.get(j).getPontuacao();
                                            break;
                                        }
                                    }
                                }
                        %>
                        <label style="min-width: 150px" ><%= atributo.getDescricao()%></label><label style="color: red" >*</label>
                        <label ><input type="text" maxlength="2" name="atributo<%= atributo.getCodigo()%>" value="<%= valor%>" class="form-control" required ></label><br>
                            <%
                                }
                            %>
                    </div>   
                </div>
            </div>
            <br>
            <div class="container">
                <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="min-width: 100px">
            </div>
        </form>
    </body>

    <script language="JavaScript" src="JS/popularCidade.js"></script>
    <script language="JavaScript" src="JS/validacaoJogador.js"></script>

</html>