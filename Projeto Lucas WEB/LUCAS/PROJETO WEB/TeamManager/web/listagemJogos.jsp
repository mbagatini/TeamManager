<%@page import="apoio.Formatacao"%>
<%@page import="entidades.*"%>
<%@page import="daos.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Listagem de Jogos</title>
    </head>
    <jsp:include page="navegacao.jsp" />
    <body>
        <p><br/></p>
        <h1 style="text-align:center;">Listagem de jogos</h1>
        <br>
        <div class="container">
            <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="tabela">
                <thead>
                    <tr>
                        <th>Adversario</th>
                        <th>Time</th>
                        <th>Adv.</th>
                        <th>Data</th>
                        <th>Competição  </th>
                        <th>Lugar</th>
                        <th>Cidade</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        ArrayList<Object> jogos = new JogoDAO().consultarTodos();
                        for (int i = 0; i < jogos.size(); i++) {
                            Jogo jogo = (Jogo) jogos.get(i);
                            String lugar;
                            if (jogo.getLugar() == 'L') {
                                lugar = "LOCAL";
                            } else {
                                lugar = "VISITANTE";
                            }
                    %>
                    <tr>
                        <td><%=jogo.getAdversario().getNome()%></td>
                        <td><%=jogo.getPontuacaotime()%></td>
                        <td><%=jogo.getPontuacaoadversario()%></td>
                        <td><%=Formatacao.retornaDataFormatada(jogo.getData())%></td>
                        <td><%=jogo.getCompeticao().getDescricao()%></td>
                        <td><%=lugar%></td>
                        <td><%=jogo.getCidade().getNome()%></td>
                        <td><a href="/TeamManager/acao?parametro=finalizarJogo&codigo=<%=jogo.getCodigo()%>">Finalizar</a></td>
                        <td><a href="/TeamManager/acao?parametro=editarJogo&codigo=<%=jogo.getCodigo()%>">Editar</a></td>
                        <td><a OnClick="return confirm('Confirma exclusão?')" 
                               href="/TeamManager/acao?parametro=excluirJogo&codigo=<%=jogo.getCodigo()%>">Excluir</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <script src="boots/js/jquery-1.12.3.js"></script>
        <script src="boots/js/dataTables.bootstrap.min.js"></script>
        <script src="boots/js/jquery.dataTables.min.js"></script>
        <script src="boots/js/dataTables.buttons.min.js"></script>
        <script src="boots/js/buttons.bootstrap.min.js"></script>
        <script src="boots/js/jszip.min.js"></script>
        <script src="boots/js/pdfmake.min.js"></script>
        <script src="boots/js/vfs_fonts.js"></script>
        <script src="boots/js/buttons.html5.min.js"></script>
        <script src="boots/js/buttons.print.min.js"></script>
        <script src="boots/js/buttons.colVis.min.js"></script>
        <script>
                            $(document).ready(function () {
                                $('#tabela').DataTable({
                                    dom: 'Bfrtip',
                                    buttons: [
                                        'csv', 'excel', 'pdf', 'print'
                                    ]
                                });
                            });
        </script>
    </body>
</html>
