package servlets;

import daos.*;
import entidades.*;
import apoio.Constantes;
import apoio.Formatacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

public class acao extends HttpServlet {

    HttpServletRequest requisicao;
    HttpServletResponse resposta;

    CidadeDAO cidadedao = new CidadeDAO();
    PosicaoDAO posicaodao = new PosicaoDAO();
    AtributoDAO atributodao = new AtributoDAO();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        requisicao = request;
        resposta = response;

        if (request.getParameter("parametro").equals("logout")) {
            logout();
        } else if (request.getParameter("parametro").equals("gerarCSV")) {
            gerarCSV();
        } else if (request.getParameter("parametro").equals("editarAtributo")) {
            editarAtributo();
        } else if (request.getParameter("parametro").equals("excluirAtributo")) {
            excluirAtributo();
        } else if (request.getParameter("parametro").equals("editarPosicao")) {
            editarPosicao();
        } else if (request.getParameter("parametro").equals("excluirPosicao")) {
            excluirPosicao();
        } else if (request.getParameter("parametro").equals("editarCidade")) {
            editarCidade();
        } else if (request.getParameter("parametro").equals("excluirCidade")) {
            excluirCidade();
        } else if (request.getParameter("parametro").equals("editarAdversario")) {
            editarAdversario();
        } else if (request.getParameter("parametro").equals("excluirAdversario")) {
            excluirAdversario();
        } else if (request.getParameter("parametro").equals("editarCompeticao")) {
            editarCompeticao();
        } else if (request.getParameter("parametro").equals("excluirCompeticao")) {
            excluirCompeticao();
        } else if (request.getParameter("parametro").equals("exibirJogador")) {
            exibirJogador();
        } else if (request.getParameter("parametro").equals("editarJogador")) {
            editarJogador();
        } else if (request.getParameter("parametro").equals("excluirJogador")) {
            excluirJogador();
        } else if (request.getParameter("parametro").equals("editarJogo")) {
            editarJogo();
        } else if (request.getParameter("parametro").equals("finalizarJogo")) {
            finalizarJogo();
        } else if (request.getParameter("parametro").equals("excluirJogo")) {
            excluirJogo();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requisicao = request;
        resposta = response;
        PrintWriter out = response.getWriter();

        if (request.getParameter("parametro").equals("login")) {
            validarLogin();
        } else if (request.getParameter("parametro").equals("cadastrarAtributo")) {
            cadastrarAtributo();
        } else if (request.getParameter("parametro").equals("cadastrarPosicao")) {
            cadastrarPosicao();
        } else if (request.getParameter("parametro").equals("cadastroCidade")) {
            cadastrarCidade();
        } else if (request.getParameter("parametro").equals("cadastroAdversario")) {
            cadastrarAdversario();
        } else if (request.getParameter("parametro").equals("cadastroCompeticao")) {
            cadastrarCompeticao();
        } else if (request.getParameter("parametro").equals("cadastroJogador")) {
            cadastrarJogador();
        } else if (request.getParameter("parametro").equals("cadastroJogo")) {
            cadastrarJogo();
        } else if (request.getParameter("parametro").equals("atualizarJogo")) {
            atualizarJogo();
        } else if (request.getParameter("parametro").equals("popularCidade")) {
            String valor = request.getParameter("uf");
            carregarCidades(out, valor);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // ATRIBUTOS
    private void cadastrarAtributo() {
        int id = Integer.parseInt(requisicao.getParameter("codigo"));
        String nome = requisicao.getParameter("descricao");

        Atributo _atributo = new Atributo();
        _atributo.setCodigo(id);
        _atributo.setDescricao(nome);

        String retorno = null;
        if (_atributo.getCodigo() == 0) { // é uma inserção
            retorno = new AtributoDAO().salvar(_atributo, 'i');
        } else {
            retorno = new AtributoDAO().atualizar(_atributo);
        }

        requisicao.setAttribute("paginaRetorno", Constantes.CADASTRO_ATRIBUTO);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void editarAtributo() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Atributo l = (Atributo) new AtributoDAO().consultarId(codigo);

        if (l != null) {
            requisicao.setAttribute("atributo", l);
            encaminharPagina(Constantes.CADASTRO_ATRIBUTO);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_ATRIBUTO);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }

    }

    private void excluirAtributo() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));
        String retorno = new AtributoDAO().excluir(codigo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_ATRIBUTO);
        if (retorno == null) {
            encaminharPagina(Constantes.LISTAGEM_ATRIBUTO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    // POSICOES
    private void cadastrarPosicao() {
        int id = Integer.parseInt(requisicao.getParameter("codigo"));
        String nome = requisicao.getParameter("descricao");

        Posicao _posicao = new Posicao();
        _posicao.setCodigo(id);
        _posicao.setDescricao(nome);

        String retorno = null;
        if (_posicao.getCodigo() == 0) { // é uma inserção
            retorno = new PosicaoDAO().salvar(_posicao, 'i');
        } else {
            retorno = new PosicaoDAO().atualizar(_posicao);
        }

        requisicao.setAttribute("paginaRetorno", Constantes.CADASTRO_POSICAO);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void editarPosicao() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Posicao _posicao = (Posicao) new PosicaoDAO().consultarId(codigo);

        if (_posicao != null) {
            requisicao.setAttribute("posicao", _posicao);
            encaminharPagina(Constantes.CADASTRO_POSICAO);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_POSICAO);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }

    }

    private void excluirPosicao() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        String retorno = new PosicaoDAO().excluir(codigo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_POSICAO);
        if (retorno == null) {
            encaminharPagina(Constantes.LISTAGEM_POSICAO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    // CIDADES
    private void cadastrarCidade() {
        int id = Integer.parseInt(requisicao.getParameter("codigo"));
        String nome = requisicao.getParameter("nome");
        String uf = requisicao.getParameter("uf");

        Cidade _cidade = new Cidade();
        _cidade.setCodigo(id);
        _cidade.setNome(nome);
        _cidade.setUf(uf);

        String retorno = null;
        if (_cidade.getCodigo() == 0) { // é uma inserção
            retorno = new CidadeDAO().salvar(_cidade, 'i');
        } else {
            retorno = new CidadeDAO().atualizar(_cidade);
        }

        requisicao.setAttribute("paginaRetorno", Constantes.CADASTRO_CIDADE);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void editarCidade() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Cidade _cidade = (Cidade) new CidadeDAO().consultarId(codigo);

        if (_cidade != null) {
            requisicao.setAttribute("cidade", _cidade);
            encaminharPagina(Constantes.CADASTRO_CIDADE);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_CIDADE);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }

    }

    private void excluirCidade() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        String retorno = new CidadeDAO().excluir(codigo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_CIDADE);
        if (retorno == null) {
            encaminharPagina(Constantes.LISTAGEM_CIDADE);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    // ADVERSARIOS
    private void cadastrarAdversario() {
        int id = Integer.parseInt(requisicao.getParameter("codigo"));
        String nome = requisicao.getParameter("nome");
        String telefone = requisicao.getParameter("telefone");
        String uf = requisicao.getParameter("uf");
        int codigocidade = Integer.parseInt(requisicao.getParameter("cidade"));

        Cidade cidade = new Cidade();
        cidade = (Cidade) new CidadeDAO().consultarId(codigocidade);

        Adversario _adversario = new Adversario();
        _adversario.setCodigo(id);
        _adversario.setNome(nome);
        _adversario.setTelefone(telefone);
        _adversario.setCidade(cidade);

        String retorno = null;
        if (_adversario.getCodigo() == 0) { // é uma inserção
            retorno = new AdversarioDAO().salvar(_adversario, 'i');
        } else {
            retorno = new AdversarioDAO().atualizar(_adversario);
        }

        requisicao.setAttribute("paginaRetorno", Constantes.CADASTRO_ADVERSARIO);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void editarAdversario() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Adversario _adversario = (Adversario) new AdversarioDAO().consultarId(codigo);

        if (_adversario != null) {
            requisicao.setAttribute("adversario", _adversario);
            encaminharPagina(Constantes.CADASTRO_ADVERSARIO);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_ADVERSARIO);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }

    }

    private void excluirAdversario() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        String retorno = new AdversarioDAO().excluir(codigo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_ADVERSARIO);
        if (retorno == null) {
            encaminharPagina(Constantes.LISTAGEM_ADVERSARIO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    // COMPETICOES
    private void cadastrarCompeticao() {
        int id = Integer.parseInt(requisicao.getParameter("codigo"));
        String descricao = requisicao.getParameter("descricao");
        String tipo = requisicao.getParameter("tipo");

        TipoCompeticao tipocompeticao = new TipoCompeticao(tipo.charAt(0));

        Competicao _competicao = new Competicao();
        _competicao.setCodigo(id);
        _competicao.setDescricao(descricao);
        _competicao.setTipo(tipocompeticao);

        String retorno = null;
        if (_competicao.getCodigo() == 0) { // é uma inserção
            retorno = new CompeticaoDAO().salvar(_competicao, 'i');
        } else {
            retorno = new CompeticaoDAO().atualizar(_competicao);
        }

        requisicao.setAttribute("paginaRetorno", Constantes.CADASTRO_COMPETICAO);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void editarCompeticao() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Competicao _competicao = (Competicao) new CompeticaoDAO().consultarId(codigo);

        if (_competicao != null) {
            requisicao.setAttribute("competicao", _competicao);
            encaminharPagina(Constantes.CADASTRO_COMPETICAO);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_COMPETICAO);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }

    }

    private void excluirCompeticao() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        String retorno = new CompeticaoDAO().excluir(codigo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_COMPETICAO);
        if (retorno == null) {
            encaminharPagina(Constantes.LISTAGEM_COMPETICAO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    // JOGADORES
    private void cadastrarJogador() {

        int id = Integer.parseInt(requisicao.getParameter("codigo"));
        String nome = requisicao.getParameter("nome");
        String nascimento = requisicao.getParameter("nascimento");
        String telefone = requisicao.getParameter("telefone");
        int numero = Integer.parseInt(requisicao.getParameter("numero"));
        double peso = Double.parseDouble(requisicao.getParameter("peso"));
        double altura = Double.parseDouble(requisicao.getParameter("altura"));
        int codigocidade = Integer.parseInt(requisicao.getParameter("cidade"));
        Cidade cidade = (Cidade) cidadedao.consultarId(codigocidade);

        Date datanascimento = null;
        try {
            datanascimento = new Date(format.parse(nascimento).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
        }

        Jogador _jogador = new Jogador();
        _jogador.setCodigo(id);
        _jogador.setNome(nome);
        _jogador.setNascimento(datanascimento);
        _jogador.setTelefone(telefone);
        _jogador.setNumero(numero);
        _jogador.setCidade(cidade);
        _jogador.setAltura(altura);
        _jogador.setPeso(peso);

        String[] posicoes = requisicao.getParameterValues("posicao");
        int posicaoprincipal = Integer.parseInt(requisicao.getParameter("principal"));
        ArrayList<PosicaoJogador> posicoesjogador = new ArrayList<PosicaoJogador>();
        for (int i = 0; i < posicoes.length; i++) {
            Posicao posicao = (Posicao) posicaodao.consultarId(Integer.parseInt(posicoes[i]));
            PosicaoJogador posicaojogador = new PosicaoJogador();
            posicaojogador.setJogador(_jogador);
            posicaojogador.setPosicao(posicao);
            if (posicao.getCodigo() == posicaoprincipal) {
                posicaojogador.setPrincipal(true);
            } else {
                posicaojogador.setPrincipal(false);
            }
            posicoesjogador.add(posicaojogador);
        }
        _jogador.setPosicoesJogador(posicoesjogador);

        ArrayList<Object> atributos = new AtributoDAO().consultarTodos();
        ArrayList<AtributoJogador> atributosjogador = new ArrayList<AtributoJogador>();
        for (int i = 0; i < atributos.size(); i++) {
            Atributo atributo = (Atributo) atributos.get(i);
            AtributoJogador atributojogador = new AtributoJogador();
            atributojogador.setAtributo(atributo);
            atributojogador.setJogador(_jogador);
            int pontuacao = Integer.parseInt(requisicao.getParameter("atributo" + atributo.getCodigo()));
            atributojogador.setPontuacao(pontuacao);
            atributosjogador.add(atributojogador);
        }
        _jogador.setAtributosJogador(atributosjogador);

        String retorno = null;
        if (_jogador.getCodigo() == 0) { // é uma inserção
            retorno = new JogadorDAO().salvar(_jogador, 'i');
        } else {
            retorno = new JogadorDAO().atualizar(_jogador);
        }

        requisicao.setAttribute("paginaRetorno", Constantes.CADASTRO_JOGADOR);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void exibirJogador() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Jogador _jogador = (Jogador) new JogadorDAO().consultarId(codigo);

        if (_jogador != null) {
            requisicao.setAttribute("jogador", _jogador);
            encaminharPagina(Constantes.EXIBIR_JOGADOR);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_JOGADOR);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }

    }

    private void editarJogador() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Jogador _jogador = (Jogador) new JogadorDAO().consultarId(codigo);

        if (_jogador != null) {
            requisicao.setAttribute("jogador", _jogador);
            encaminharPagina(Constantes.CADASTRO_JOGADOR);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_JOGADOR);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }

    }

    private void excluirJogador() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        String retorno = new JogadorDAO().excluir(codigo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_JOGADOR);
        if (retorno == null) {
            encaminharPagina(Constantes.LISTAGEM_JOGADOR);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    // JOGO
    private void cadastrarJogo() {

        int id = Integer.parseInt(requisicao.getParameter("codigo"));

        Date data = null;
        Time hora = null;

        try {
            data = Formatacao.retornaDataString(Formatacao.ajustaDataDMA(requisicao.getParameter("data")));
            hora = Formatacao.retornaHoraString(requisicao.getParameter("hora"));
        } catch (ParseException ex) {
            Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
        }

        char lugar = requisicao.getParameter("lugar").charAt(0);
        Cidade cidade = (Cidade) new CidadeDAO().consultarId(Integer.parseInt(requisicao.getParameter("cidade")));
        Adversario adversario = (Adversario) new AdversarioDAO().consultarId(Integer.parseInt(requisicao.getParameter("adversario")));
        Competicao competicao = (Competicao) new CompeticaoDAO().consultarId(Integer.parseInt(requisicao.getParameter("competicao")));
        int pontostime = Integer.parseInt(requisicao.getParameter("pontostime"));
        int pontosadversario = Integer.parseInt(requisicao.getParameter("pontosadversario"));

        Jogo _jogo = new Jogo();
        _jogo.setCodigo(id);
        _jogo.setData(data);
        _jogo.setHora(hora);
        _jogo.setLugar(lugar);
        _jogo.setCidade(cidade);
        _jogo.setAdversario(adversario);
        _jogo.setCompeticao(competicao);
        _jogo.setPontuacaotime(pontostime);
        _jogo.setPontuacaoadversario(pontosadversario);

        if (pontosadversario + pontostime == 0) {
            _jogo.setStatus('E');
        } else {
            _jogo.setStatus('F');
        }

        String[] jogadores = requisicao.getParameterValues("escalacao");
        String[] titulares = requisicao.getParameterValues("titular");

        ArrayList<JogadorJogo> jogadoresjogo = new ArrayList<JogadorJogo>();
        for (int i = 0; i < jogadores.length; i++) {
            JogadorJogo jogadorjogo = new JogadorJogo();
            Jogador jogador = (Jogador) new JogadorDAO().consultarId(Integer.parseInt(jogadores[i]));
            Boolean titular = false;

            for (int j = 0; j < titulares.length; j++) {
                int codigotitular = Integer.parseInt(titulares[j]);
                if (codigotitular == jogador.getCodigo()) {
                    titular = true;
                    break;
                }
            }

            jogadorjogo.setJogador(jogador);
            jogadorjogo.setJogo(_jogo);
            jogadorjogo.setTitular(titular);

            jogadoresjogo.add(jogadorjogo);
        }
        _jogo.setJogadoresJogo(jogadoresjogo);

        String retorno = null;
        if (_jogo.getCodigo() == 0) { // é uma inserção
            retorno = new JogoDAO().salvar(_jogo, 'i');
        } else {
            retorno = new JogoDAO().atualizar(_jogo);
        }

        requisicao.setAttribute("paginaRetorno", Constantes.CADASTRO_JOGO);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void atualizarJogo() {

        int id = Integer.parseInt(requisicao.getParameter("codigo"));

        int pontostime = Integer.parseInt(requisicao.getParameter("pontostime"));
        int pontosadversario = Integer.parseInt(requisicao.getParameter("pontosadversario"));

        Jogo _jogo = (Jogo) new JogoDAO().consultarId(id);

        _jogo.setPontuacaotime(pontostime);
        _jogo.setPontuacaoadversario(pontosadversario);

        _jogo.setStatus('F');

        String retorno = new JogoDAO().atualizar(_jogo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_JOGO);
        if (retorno == null) {
            encaminharPagina(Constantes.PAGINA_SUCESSO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void editarJogo() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Jogo _jogo = (Jogo) new JogoDAO().consultarId(codigo);

        if (_jogo != null) {
            requisicao.setAttribute("jogo", _jogo);
            encaminharPagina(Constantes.CADASTRO_JOGO);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_JOGO);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void finalizarJogo() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        Jogo _jogo = (Jogo) new JogoDAO().consultarId(codigo);

        if (_jogo != null) {
            requisicao.setAttribute("jogo", _jogo);
            encaminharPagina(Constantes.FINALIZAR_JOGO);
        } else {
            requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_JOGO);
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void excluirJogo() {
        int codigo = Integer.parseInt(requisicao.getParameter("codigo"));

        String retorno = new JogoDAO().excluir(codigo);

        requisicao.setAttribute("paginaRetorno", Constantes.LISTAGEM_JOGO);
        if (retorno == null) {
            encaminharPagina(Constantes.LISTAGEM_JOGO);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    // OUTROS
    private void encaminharPagina(String pagina) {
        try {
            RequestDispatcher rd = requisicao.getRequestDispatcher(pagina);
            rd.forward(requisicao, resposta);
        } catch (Exception e) {
            System.out.println("erro ao encaminhar página");
        }
    }

    private void carregarCidades(PrintWriter out, String valor) {
        try {
            int i = 0;
            String uf = valor;
            ArrayList<Object> cidades = null;
            cidades = new CidadeDAO().consultar(uf);
            JSONArray arrayJsonCidades = new JSONArray();
            // Primeira cidade que aparece na lista
            JSONObject primeiraopcao = new JSONObject();
            primeiraopcao.put("nome", "SELECIONE");
            primeiraopcao.put("id", "0");
            arrayJsonCidades.put(primeiraopcao);

            while (i < cidades.size()) {
                // Cidades que estão no banco de dados
                JSONObject jsonCidade3 = new JSONObject();
                Cidade cidadejson = (Cidade) cidades.get(i);
                jsonCidade3.put("nome", cidadejson.getNome());
                jsonCidade3.put("id", cidadejson.getCodigo());
                arrayJsonCidades.put(jsonCidade3);
                i++;
            }
            //JSONObject retorno = new JSONObject();
            //retorno.put("cidades", arrayJsonCidades);
            out.print(arrayJsonCidades);//retorno
        } catch (Exception ex) {
        } finally {
            out.close();
        }
    }

    private void validarLogin() {
        String email = requisicao.getParameter("emailUsuario");
        String senha = requisicao.getParameter("senha");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        Informacoes informacoes = new Informacoes();

        if (new UsuarioDAO().consultar(usuario)) {
            // usuario validado: cria coloca seu nome na sessao
            HttpSession sessao = requisicao.getSession();
            // setando um atributo da sessao
            sessao.setAttribute("usuarioLogado", usuario);
            sessao.setAttribute("informcaoes", informacoes);
            encaminharPagina(Constantes.PAGINA_MENU);
        } else {
            encaminharPagina(Constantes.PAGINA_ERRO);
        }
    }

    private void logout() {
        HttpSession sessao = requisicao.getSession();
        sessao.invalidate();
        encaminharPagina("index.jsp");
    }

    private void gerarCSV() {
        String tipoCSV = String.valueOf(requisicao.getParameter("tipoCSV"));

        if (tipoCSV.equals("jogador")) {
            new JogadorDAO().gerarCSV();

            try {
                resposta.sendRedirect("csv/jogadores.csv");
            } catch (Exception e) {
                System.out.println("erro sendRedirect: " + e);
            }

        }
    }

}
