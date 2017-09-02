<%@page import="entidades.*"%>
<%@page import="daos.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Listagem de jogadores</title>
    </head>
    <jsp:include page="navegacao.jsp" />
    <body>
        <p><br/></p>
        <h1 style="text-align:center;">Listagem de jogadores</h1>
        <br>
        <div class="container">
            <%--
            <a href="/TeamManager/acao?parametro=gerarCSV&tipoCSV=jogador" target="_blank">Gerar CSV</a>
            <br>
            <a href="/TeamManager/reportJogadores.jsp" target="_blank">Gerar PDF</a>
            --%>
            <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="tabelajogadores">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th>Cidade</th>
                        <th>Estado</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Object> jogadores = new JogadorDAO().consultarTodos();
                        for (int i = 0; i < jogadores.size(); i++) {
                            Jogador jogador = (Jogador) jogadores.get(i);
                    %>
                    <tr>
                        <td><%=jogador.getCodigo()%></td>
                        <td><%=jogador.getNome()%></td>
                        <td><%=jogador.getTelefone()%></td>
                        <td><%=jogador.getCidade().getNome()%></td>
                        <td><%=jogador.getCidade().getUf()%></td>
                        <td><a href="/TeamManager/acao?parametro=exibirJogador&codigo=<%=jogador.getCodigo()%>">Exibir</a></td>
                        <td><a href="/TeamManager/acao?parametro=editarJogador&codigo=<%=jogador.getCodigo()%>">Editar</a></td>
                        <td><a OnClick="return confirm('Confirma exclusão?')" 
                               href="/TeamManager/acao?parametro=excluirJogador&codigo=<%=jogador.getCodigo()%>">Excluir</a></td>
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
                                $('#tabelajogadores').DataTable({
                                    dom: 'Bfrtip',
                                    buttons: [
                                        'csv', 'excel', 'pdf', 'print'
                                    ]
                                });
                            });
        </script>
    </body>
</html>
