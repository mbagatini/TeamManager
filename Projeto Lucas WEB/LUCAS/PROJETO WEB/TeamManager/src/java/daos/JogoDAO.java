/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.*;
import interfaces.IDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class JogoDAO implements IDAO {

    Connection conn = null;
    Statement st = null;
    CidadeDAO cidadedao = new CidadeDAO();
    AdversarioDAO adversariodao = new AdversarioDAO();
    CompeticaoDAO competicaodao = new CompeticaoDAO();
    JogadorDAO jogadordao = new JogadorDAO();

    @Override
    public String salvar(Object o, char op) {
        Jogo jogo = (Jogo) o;
        int retorno = 0;
        try {
            // BLOQUEAR O AUTO COMMIT
            conn = ConexaoBD.getInstance().getConnection();
            conn.setAutoCommit(false);

            int codigojogo;

            String sql = "insert into jogo values ("
                    + "default, "
                    + "'" + jogo.getData() + "', "
                    + "'" + jogo.getHora()+ "', "
                    + "'" + jogo.getLugar() + "', "
                    + " " + jogo.getCidade().getCodigo() + ", "
                    + " " + jogo.getAdversario().getCodigo() + ", "
                    + " " + jogo.getCompeticao().getCodigo() + ", "
                    + " " + jogo.getPontuacaotime() + ", "
                    + " " + jogo.getPontuacaoadversario() + ", "
                    + "'" + jogo.getStatus() + "' "
                    + ") returning codigo";

            System.out.println(sql);

            ResultSet resul = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            resul.next();

            codigojogo = resul.getInt(1);

            st = ConexaoBD.getInstance().getConnection().createStatement();
            for (JogadorJogo jogadorjogo : jogo.getJogadoresJogo()) {
                int resultado;
                    sql = "INSERT INTO jogadores_do_jogo VALUES ("
                        + " " + jogadorjogo.getJogador().getCodigo() + ", "
                        + " " + codigojogo + ", "
                        + " " + jogadorjogo.getTitular() + ") ";
                System.out.println("sql: " + sql);
                resultado = st.executeUpdate(sql);
            }

            conn.commit();
            return null;
        } catch (SQLException ex) {
            System.out.println("Erro salvar jogo = " + ex);
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            return ex.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Jogo jogo = (Jogo) o;

        int retorno = 0;

        try {
            // BLOQUEAR O AUTO COMMIT
            conn = ConexaoBD.getInstance().getConnection();
            conn.setAutoCommit(false);

            // FAZ O UPDATE DO JOGADOR
            String sql = " update jogo set "
                    + " data =  '" + jogo.getData() + "', "
                    + " hora = '" + jogo.getHora() + "', "
                    + " lugar = '" + jogo.getLugar() + "', "
                    + " cidades_codigo = " + jogo.getCidade().getCodigo() + ", "
                    + " adversarios_codigo = " + jogo.getAdversario().getCodigo() + ", "
                    + " competicoes_codigo = " + jogo.getCompeticao().getCodigo() + ", "
                    + " pontos_time = " + jogo.getPontuacaotime()+ ", "
                    + " pontos_adversario = " + jogo.getPontuacaoadversario()+ ", "
                    + " status = '" + jogo.getStatus()+ "' "
                    + " where codigo = " + jogo.getCodigo();


            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            // DELETA TODOS OS ATRIBUTOS
            sql = "delete from jogadores_do_jogo where jogos_codigo = " + jogo.getCodigo();
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            // INSERE NOVAMENTO OS ATRIBUTOS NOVOS
            st = ConexaoBD.getInstance().getConnection().createStatement();
            for (JogadorJogo jogadorjogo : jogo.getJogadoresJogo()) {
                int resultado;
                    sql = "INSERT INTO jogadores_do_jogo VALUES ("
                        + " " + jogadorjogo.getJogador().getCodigo() + ", "
                        + " " + jogo.getCodigo() + ", "
                        + " " + jogadorjogo.getTitular() + ") ";
                System.out.println("sql: " + sql);
                resultado = st.executeUpdate(sql);
            }
            conn.commit();
            return null;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar jogo = " + ex);
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            return ex.toString();
        }
    }

    @Override
    public String excluir(int id) {
        int retorno = 0;

        try {
            // BLOQUEAR O AUTO COMMIT
            conn = ConexaoBD.getInstance().getConnection();
            conn.setAutoCommit(false);
            
            String sql = "";

            // DELETA TODOS OS JOGADORES DO JOGO
            sql = "delete from jogadores_do_jogo where jogos_codigo = " + id;
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
            
            sql = "delete from jogo where codigo = " + id;
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            conn.commit();
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir jogo: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList<Object> jogos = new ArrayList<>();

        try {
            String sql = "select * from jogo";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                Jogo jogo = (Jogo) consultarId(codigo);
                jogos.add(jogo);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta jogo: " + e);
        }
        return jogos;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM jogo WHERE codigo = " + id + " ";

            System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            Jogo jogo = null;
            if (resultado.next()) {
                jogo = new Jogo();
                Cidade cidade = new Cidade();
                Adversario adversario = new Adversario();
                Competicao competicao = new Competicao();
                
                jogo.setCodigo(resultado.getInt("codigo"));
                jogo.setData(resultado.getDate("data"));
                jogo.setHora(resultado.getTime("hora"));
                jogo.setLugar(resultado.getString("lugar").charAt(0));
                jogo.setPontuacaotime(resultado.getInt("pontos_time"));
                jogo.setPontuacaoadversario(resultado.getInt("pontos_adversario"));
                jogo.setStatus(resultado.getString("status").charAt(0));
                
                cidade = (Cidade) cidadedao.consultarId(resultado.getInt("cidades_codigo"));
                jogo.setCidade(cidade);
                
                adversario = (Adversario) adversariodao.consultarId(resultado.getInt("adversarios_codigo"));
                jogo.setAdversario(adversario);
                
                competicao = (Competicao) competicaodao.consultarId(resultado.getInt("competicoes_codigo"));
                jogo.setCompeticao(competicao);
            }

            sql = "SELECT * FROM jogadores_do_jogo WHERE jogos_codigo = " + id + "";
            System.out.println("sql: " + sql);
            resultado = st.executeQuery(sql);

            ArrayList<JogadorJogo> jogadoresjogo = new ArrayList<>();
            while (resultado.next()) {
                JogadorJogo jogadorjogo = new JogadorJogo();
                
                Jogador jogador = new Jogador();
                jogador = (Jogador) jogadordao.consultarId(resultado.getInt("jogadores_codigo"));
                jogadorjogo.setJogador(jogador);
                jogadorjogo.setJogo(jogo);
                jogadorjogo.setTitular(resultado.getBoolean("titular"));
                jogadoresjogo.add(jogadorjogo);
            }

            jogo.setJogadoresJogo(jogadoresjogo);

            return jogo;
        } catch (Exception ex) {
            System.out.println("Erro consultar jogo = " + ex);
            return ex.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
