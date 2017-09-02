<%@page import="entidades.PosicaoJogador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.*"%>
<%@page import="daos.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        Jogo jogo = (Jogo) request.getAttribute("jogo");
        if (jogo == null) {
            jogo = new Jogo();

            Adversario adv = new Adversario();
            adv.setCodigo(0);
            jogo.setAdversario(adv);

            Competicao com = new Competicao();
            com.setCodigo(0);
            jogo.setCompeticao(com);

            Cidade cidade = new Cidade();
            cidade.setUf("X");
            jogo.setCidade(cidade);

            jogo.setPontuacaotime(0);
            jogo.setPontuacaoadversario(0);
            jogo.setStatus('E');
        }
    %>
    <head>
        <script type="text/javascript" DEFER="DEFER">
            function popular() {
                document.getElementById("uf").click();
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro de jogo</title>
    </head>
    <jsp:include page="navegacao.jsp" ></jsp:include>
        <body onload="popular()" >
            <h1 style="text-align:center;">Cadastro de jogo</h1>
            <br>
            <br>
            <div class="container">
                <form name="formJogo" method="post" action="/TeamManager/acao?parametro=cadastroJogo" onsubmit="return validar()" >
                    <input type="hidden" name="codigo" value="<%= jogo.getCodigo()%>">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <label >Data</label><label style="color: red" >*</label>
                                                <input type="date" name="data" style="max-width: 160px" value="<%= jogo.getData()%>" class="form-control" required><br>
                                            </div>
                                            <div class="col-md-3">
                                                <label >Hora</label><label style="color: red" >*</label>
                                                <input type="time" name="hora" value="<%= jogo.getHora()%>" class="form-control" required><br>
                                            </div>
                                            <div class="col-md-5">
                                                <label >Lugar</label><label style="color: red" >*</label>
                                                <select class="form-control" style="max-width: 150px" name="lugar" id="lugar">
                                                    <option value="X" <% if (jogo.getLugar() == 'X') { %> selected <% } %> >SELECIONE</option>
                                                    <option value="L" <% if (jogo.getLugar() == 'L') { %> selected <% } %> >LOCAL</option>
                                                    <option value="V" <% if (jogo.getLugar() == 'V') { %> selected <% }%> >VISITANTE</option>
                                                </select><br>
                                            </div>
                                        </div>

                                        <label >Estado</label><label  style="color: red" >*</label>
                                        <select class="form-control" style="max-width: 400px" onclick="prencherCidade(<%= jogo.getCidade().getCodigo()%>)" 
                                                onChange="prencherCidade(<%= jogo.getCidade().getCodigo()%>)" name="uf" id="uf">
                                            <option value="X"  <% if (jogo.getCidade().getUf().equals("X")) { %> selected <% } %> >SELECIONE</option>
                                            <option value="AC" <% if (jogo.getCidade().getUf().equals("AC")) { %> selected <% } %> >Acre</option>
                                            <option value="AL" <% if (jogo.getCidade().getUf().equals("AL")) { %> selected <% } %> >Alagoas</option>
                                            <option value="AP" <% if (jogo.getCidade().getUf().equals("AP")) { %> selected <% } %> >Amapá</option>
                                            <option value="AM" <% if (jogo.getCidade().getUf().equals("AM")) { %> selected <% } %> >Amazonas</option>
                                            <option value="BA" <% if (jogo.getCidade().getUf().equals("BA")) { %> selected <% } %> >Bahia</option>
                                            <option value="CE" <% if (jogo.getCidade().getUf().equals("CE")) { %> selected <% } %> >Ceará</option>
                                            <option value="DF" <% if (jogo.getCidade().getUf().equals("DF")) { %> selected <% } %> >Distrito Federal</option>
                                            <option value="ES" <% if (jogo.getCidade().getUf().equals("ES")) { %> selected <% } %> >Espírito Santo</option>
                                            <option value="GO" <% if (jogo.getCidade().getUf().equals("GO")) { %> selected <% } %> >Goiás</option>
                                            <option value="MA" <% if (jogo.getCidade().getUf().equals("MA")) { %> selected <% } %> >Maranhão</option>
                                            <option value="MT" <% if (jogo.getCidade().getUf().equals("MT")) { %> selected <% } %> >Mato Grosso</option>
                                            <option value="MS" <% if (jogo.getCidade().getUf().equals("MS")) { %> selected <% } %> >Mato Grosso do Sul</option>
                                            <option value="MG" <% if (jogo.getCidade().getUf().equals("MG")) { %> selected <% } %> >Minas Gerais</option>
                                            <option value="PA" <% if (jogo.getCidade().getUf().equals("PA")) { %> selected <% } %> >Pará</option>
                                            <option value="PB" <% if (jogo.getCidade().getUf().equals("PB")) { %> selected <% } %> >Paraíba</option>
                                            <option value="PR" <% if (jogo.getCidade().getUf().equals("PR")) { %> selected <% } %> >Paraná</option>
                                            <option value="PE" <% if (jogo.getCidade().getUf().equals("PE")) { %> selected <% } %> >Pernambuco</option>
                                            <option value="PI" <% if (jogo.getCidade().getUf().equals("PI")) { %> selected <% } %> >Piauí</option>
                                            <option value="RJ" <% if (jogo.getCidade().getUf().equals("RJ")) { %> selected <% } %> >Rio de Janeiro</option>
                                            <option value="RN" <% if (jogo.getCidade().getUf().equals("RN")) { %> selected <% } %> >Rio Grande do Norte</option>
                                            <option value="RS" <% if (jogo.getCidade().getUf().equals("RS")) { %> selected <% } %> >Rio Grande do Sul</option>
                                            <option value="RO" <% if (jogo.getCidade().getUf().equals("RO")) { %> selected <% } %> >Rondônia</option>
                                            <option value="RR" <% if (jogo.getCidade().getUf().equals("RR")) { %> selected <% } %> >Roraima</option>
                                            <option value="SC" <% if (jogo.getCidade().getUf().equals("SC")) { %> selected <% } %> >Santa Catarina</option>
                                            <option value="SP" <% if (jogo.getCidade().getUf().equals("SP")) { %> selected <% } %> >São Paulo</option>
                                            <option value="SE" <% if (jogo.getCidade().getUf().equals("SE")) { %> selected <% } %> >Sergipe</option>
                                            <option value="TO" <% if (jogo.getCidade().getUf().equals("TO")) { %> selected <% }%> >Tocantins</option>
                                        </select>
                                        <br>
                                        <label >Cidade</label><label style="color: red" >*</label>
                                        <select class="form-control" style="max-width: 400px" name="cidade" id="cidade">
                                            <option value="X" >Selecione</option>
                                        </select><br>

                                        <label >Adversário</label><label style="color: red" >*</label>
                                        <select class="form-control" style="max-width: 300px" name="adversario" id="adversario">
                                            <option value="X" >SELECIONE</option>
                                            <%
                                                Adversario adversario;
                                                AdversarioDAO adversariodao = new AdversarioDAO();
                                                for (Object adversarioatual : adversariodao.consultarTodos()) {
                                                    adversario = (Adversario) adversarioatual;
                                            %>
                                            <option value="<%=adversario.getCodigo()%>" <% if (jogo.getAdversario().getCodigo() == adversario.getCodigo()) { %> selected <% }%> ><%=adversario.getNome()%></option>
                                            <%
                                                }
                                            %>
                                        </select><br>

                                        <label >Competição</label><label style="color: red" >*</label>
                                        <select class="form-control" style="max-width: 300px" name="competicao" id="competicao">
                                            <option value="X" >SELECIONE</option>
                                            <%
                                                Competicao competicao;
                                                CompeticaoDAO competicaodao = new CompeticaoDAO();
                                                for (Object competicaoatual : competicaodao.consultarTodos()) {
                                                    competicao = (Competicao) competicaoatual;
                                            %>
                                            <option value="<%=competicao.getCodigo()%>" <% if (jogo.getCompeticao().getCodigo() == competicao.getCodigo()) { %> selected <% }%> ><%=competicao.getDescricao()%></option>
                                            <%
                                                }
                                            %>
                                        </select><br>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div  class="col-md-6">
                            <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="tabelajogadores">
                                <thead>
                                    <tr>
                                        <th>Titular</th>
                                        <th>Escalação</th>
                                        <th>Posicão</th>
                                        <th>Nome Jogador</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<Object> jogadores = new JogadorDAO().consultarTodos();
                                        ArrayList<JogadorJogo> jogadoresjogo = jogo.getJogadoresJogo();
                                        for (int i = 0; i < jogadores.size(); i++) {
                                            Jogador _jogador = (Jogador) jogadores.get(i);
                                            Posicao _posicao = new Posicao();
                                            boolean escalado = false;
                                            boolean titular = false;
                                            if (jogo.getCodigo() != 0) {
                                                for (int j = 0; j < jogadoresjogo.size(); j++) {
                                                    if (_jogador.getCodigo() == jogadoresjogo.get(j).getJogador().getCodigo()) {
                                                        escalado = true;
                                                        titular = jogadoresjogo.get(j).getTitular();
                                                        break;
                                                    }
                                                }
                                            }
                                            for (PosicaoJogador posicaolista : _jogador.getPosicoesJogador()) {
                                                if (posicaolista.getPrincipal()) {
                                                    _posicao = posicaolista.getPosicao();
                                                }
                                            }
                                    %>
                                    <tr>
                                        <td><input type="checkbox" name="titular" value="<%=_jogador.getCodigo()%>" <% if (titular) { %> Checked <% }%> ></td>
                                        <td><input type="checkbox" name="escalacao" value="<%=_jogador.getCodigo()%>" <% if (escalado) { %> Checked <% }%> ></td>
                                        <td><%=_posicao.getDescricao()%></td>
                                        <td><%=_jogador.getNome()%></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>   

                    </div>
                    <div class="container">
                        <br>
                        <input type="submit" value="Gravar" class="btn btn-md btn-primary" style="">
                    </div>
                </div>
            </form>
        </div>
    </body>

    <!-- Importacao do arquivo de validacao -->
    <script language="JavaScript" src="JS/validacaoCidade.js"></script>
    <script language="JavaScript" src="JS/popularCidade.js"></script>
</html>
