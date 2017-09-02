<%@page import="entidades.Competicao"%>
<%@page import="daos.CompeticaoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Listagem de competições</title>
    </head>
    <jsp:include page="navegacao.jsp" />
    <body>
        <p><br/></p>
        <h1 style="text-align:center;">Listagem de competições</h1>
        <br>
        <div class="container">
            <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="tabelacompeticoes">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Descrição</th>
                        <th>Tipo</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Object> competicoes = new CompeticaoDAO().consultarTodos();
                        for (int i = 0; i < competicoes.size(); i++) {
                            Competicao competicao = (Competicao) competicoes.get(i);
                    %>
                    <tr>
                        <td><%=competicao.getCodigo()%></td>
                        <td style="text-transform: uppercase;" ><%=competicao.getDescricao()%></td>
                        <td style="text-transform: uppercase;" ><%=competicao.getTipo().getDescricao()%></td>
                        <td><a href="/TeamManager/acao?parametro=editarCompeticao&codigo=<%=competicao.getCodigo()%>">Editar</a></td>
                        <td><a OnClick="return confirm('Confirma exclusão?')" 
                               href="/TeamManager/acao?parametro=excluirCompeticao&codigo=<%=competicao.getCodigo()%>">Excluir</a></td>
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
                                $('#tabelacompeticoes').DataTable({
                                    dom: 'Bfrtip',
                                    buttons: [
                                        'csv', 'excel', 'pdf', 'print'
                                    ]
                                });
                            });
        </script>
    </body>
</html>
