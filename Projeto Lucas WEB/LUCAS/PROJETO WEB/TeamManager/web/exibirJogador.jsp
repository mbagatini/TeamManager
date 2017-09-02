<%@page import="java.util.ArrayList"%>
<%@page import="entidades.*"%>
<%@page import="daos.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%
        Jogador jogador = (Jogador) request.getAttribute("jogador");
    %>
    <head>
        <script type="text/javascript" src="JS/chart/Chart.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Cadastro de jogador</title>
    </head>
    <jsp:include page="navegacao.jsp" ></jsp:include>

        <body background="//shushi168.com/data/out/7/36104175-american-football-wallpaper.jpg">
            <h1 style="text-align:center;">Informações do Jogador</h1>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-md-6">

                        <label >Nome</label>
                        <input type="text" value="<%= jogador.getNome()%>" class="form-control" disabled ><br>

                    <label >Nascimento</label>
                    <input type="date" value="<%= jogador.getNascimento()%>" style="max-width: 200px" class="form-control" disabled ><br>

                    <label >Telefone</label>
                    <input type="" value="<%= jogador.getTelefone()%>" style="max-width: 200px" class="form-control" disabled ><br>

                    <label >Número</label>
                    <input type="text" value="<%= jogador.getNumero()%>" style="max-width: 200px" class="form-control" disabled ><br>

                </div>

                <div class="col-md-6">
                    <label >Peso</label>
                    <input type="text" value="<%= jogador.getPeso()%>" style="max-width: 200px" class="form-control" disabled ><br>

                    <label >Altura</label>
                    <input type="text" value="<%= jogador.getAltura()%>" style="max-width: 200px" class="form-control" disabled ><br>

                    <label >Cidade</label>
                    <input type="text" value="<%= jogador.getCidade().getNome()%> - <%= jogador.getCidade().getUf()%>" style="max-width: 300px" class="form-control" disabled ><br>
                </div>
            </div>
        </div>
        <br>
        <div class="container">
            <label >Atributos</label>
            <br>
            <canvas id="myChart" width="500" height="500"></canvas>
        </div>   
        <br>
        <div class="container" style="">
            <label >Posições</label>
            <br>
            <br>
            <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="tabelajogadores">
                <thead>
                    <tr>
                        <td>Titular</td>
                        <td>Posicão</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<PosicaoJogador> posicoesjogador = jogador.getPosicoesJogador();
                        for (int i = 0; i < posicoesjogador.size(); i++) {
                            Boolean principal = posicoesjogador.get(i).getPrincipal();
                            Posicao posicao = (Posicao) posicoesjogador.get(i).getPosicao();
                    %>

                    <tr>
                        <td><input type="checkbox" <% if (principal) { %> Checked <% }%> Disabled ></td>
                        <td><%= posicao.getDescricao()%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>   

        <script>
            var data = {labels: [
            <%
                ArrayList<AtributoJogador> atributosjogador = jogador.getAtributosJogador();
                for (int i = 0; i < atributosjogador.size(); i++) {
                    Atributo atributo = (Atributo) atributosjogador.get(i).getAtributo();
            %>
            "<%= atributo.getDescricao()%>",
            <%
                }
            %>
            ],
                    datasets: [{
                    label: "My First dataset",
                            fillColor: "rgba(151,187,205,0.5)",
                            strokeColor: "rgba(151,187,205,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(151,187,205,1)",
                            data: [

            <%
                for (int i = 0; i < atributosjogador.size(); i++) {
                    int pontuacao = atributosjogador.get(i).getPontuacao();
            %>
            <%= pontuacao%>,
            <%
                }
            %>
                            ]
                    }]
            };
            var options = {
            //Boolean - Whether to show lines for each scale point
            scaleShowLine: true,
                    //Boolean - Whether we show the angle lines out of the radar
                    angleShowLineOut: true,
                    //Boolean - Whether to show labels on the scale
                    scaleShowLabels: true,
                    // Boolean - Whether the scale should begin at zero
                    scaleBeginAtZero: true,
                    //String - Colour of the angle line
                    angleLineColor: "rgba(0,0,0,.1)",
                    //Number - Pixel width of the angle line
                    angleLineWidth: 1,
                    //String - Point label font declaration
                    pointLabelFontFamily: "'Arial'",
                    //String - Point label font weight
                    pointLabelFontStyle: "normal",
                    //Number - Point label font size in pixels
                    pointLabelFontSize: 10,
                    //String - Point label font colour
                    pointLabelFontColor: "#666",
                    //Boolean - Whether to show a dot for each point
                    pointDot: true,
                    //Number - Radius of each point dot in pixels
                    pointDotRadius: 3,
                    //Number - Pixel width of point dot stroke
                    pointDotStrokeWidth: 1,
                    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
                    pointHitDetectionRadius: 20,
                    //Boolean - Whether to show a stroke for datasets
                    datasetStroke: true,
                    //Number - Pixel width of dataset stroke
                    datasetStrokeWidth: 2,
                    //Boolean - Whether to fill the dataset with a colour
                    datasetFill: true
            };
            var ctx = document.getElementById('myChart').getContext('2d');
            var myRadarChart = new Chart(ctx).Radar(data, options);
        </script>

    </body>

</html>