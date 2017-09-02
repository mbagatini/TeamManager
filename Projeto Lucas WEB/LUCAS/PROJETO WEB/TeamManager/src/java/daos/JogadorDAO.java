/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.Arquivo;
import apoio.ConexaoBD;
import entidades.*;
import interfaces.IDAO;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Lucas
 */
public class JogadorDAO implements IDAO {

    Connection conn = null;
    Statement st = null;
    CidadeDAO cidadedao = new CidadeDAO();
    AtributoDAO atributodao = new AtributoDAO();
    PosicaoDAO posicaodao = new PosicaoDAO();

    @Override
    public String salvar(Object o, char op) {
        Jogador jogador = (Jogador) o;
        int retorno = 0;
        try {
            // BLOQUEAR O AUTO COMMIT
            conn = ConexaoBD.getInstance().getConnection();
            conn.setAutoCommit(false);

            int codigojogador;

            String sql = "insert into jogador values ("
                    + "default, "
                    + "'" + jogador.getNome().toUpperCase() + "', "
                    + "'" + jogador.getNascimento() + "', "
                    + " " + jogador.getNumero() + ", "
                    + "'" + jogador.getTelefone() + "', "
                    + " " + jogador.getCidade().getCodigo() + ", "
                    + " " + jogador.getAltura() + ", "
                    + " " + jogador.getPeso() + " "
                    + ") returning codigo";

            System.out.println(sql);

            ResultSet resul = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            resul.next();

            codigojogador = resul.getInt(1);

            st = ConexaoBD.getInstance().getConnection().createStatement();
            for (AtributoJogador atributojogador : jogador.getAtributosJogador()) {
                int resultado;
                sql = "INSERT INTO atributos_dos_jogadores VALUES ("
                        + " " + atributojogador.getAtributo().getCodigo() + ", "
                        + " " + codigojogador + ", "
                        + " " + atributojogador.getPontuacao() + ") ";
                System.out.println("sql: " + sql);
                resultado = st.executeUpdate(sql);
            }

            st = ConexaoBD.getInstance().getConnection().createStatement();
            for (PosicaoJogador posicaojogador : jogador.getPosicoesJogador()) {
                int resultado;
                sql = "INSERT INTO posicoes_do_jogador VALUES ("
                        + " " + codigojogador + ", "
                        + " " + posicaojogador.getPosicao().getCodigo() + ", "
                        + " " + posicaojogador.getPrincipal() + ") ";
                System.out.println("sql: " + sql);
                resultado = st.executeUpdate(sql);
            }

            conn.commit();
            return null;
        } catch (SQLException ex) {
            System.out.println("Erro salvar jogador = " + ex);
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException se2) {
            }
            return ex.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Jogador jogador = (Jogador) o;

        int retorno = 0;

        try {
            // BLOQUEAR O AUTO COMMIT
            conn = ConexaoBD.getInstance().getConnection();
            conn.setAutoCommit(false);

            // FAZ O UPDATE DO JOGADOR
            String sql = " update jogador "
                    + " set nome =  '" + jogador.getNome() + "', "
                    + " data_nascimento = '" + jogador.getNascimento() + "', "
                    + " numero = " + jogador.getNumero() + ", "
                    + " telefone = '" + jogador.getTelefone() + "', "
                    + " cidade = " + jogador.getCidade().getCodigo() + ", "
                    + " altura = " + jogador.getAltura() + ", "
                    + " peso = " + jogador.getPeso() + " "
                    + " where codigo = " + jogador.getCodigo();

            System.out.println(sql);
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            // DELETA TODOS OS ATRIBUTOS
            sql = "delete from atributos_dos_jogadores where codigo_jogador = " + jogador.getCodigo();
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            // INSERE NOVAMENTO OS ATRIBUTOS NOVOS
            st = ConexaoBD.getInstance().getConnection().createStatement();
            for (AtributoJogador atributojogador : jogador.getAtributosJogador()) {
                int resultado;
                sql = "INSERT INTO atributos_dos_jogadores VALUES ("
                        + " " + atributojogador.getAtributo().getCodigo() + ", "
                        + " " + jogador.getCodigo() + ", "
                        + " " + atributojogador.getPontuacao() + ") ";
                System.out.println("sql: " + sql);
                resultado = st.executeUpdate(sql);
            }

            // DELETA TODOS AS POSICOES
            sql = "delete from posicoes_do_jogador where codigo_jogador = " + jogador.getCodigo();
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            // INSERE NOVAMENTE AS POSICOES
            st = ConexaoBD.getInstance().getConnection().createStatement();
            for (PosicaoJogador posicaojogador : jogador.getPosicoesJogador()) {
                int resultado;
                sql = "INSERT INTO posicoes_do_jogador VALUES ("
                        + " " + jogador.getCodigo() + ", "
                        + " " + posicaojogador.getPosicao().getCodigo() + ", "
                        + " " + posicaojogador.getPrincipal() + ") ";
                System.out.println("sql: " + sql);
                resultado = st.executeUpdate(sql);
            }

            conn.commit();
            return null;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar jogador = " + ex);
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException se2) {
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

            String sql;

            // DELETA TODOS OS ATRIBUTOS
            sql = "delete from atributos_dos_jogadores where codigo_jogador = " + id;
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            // DELETA TODOS AS POSICOES
            sql = "delete from posicoes_do_jogador where codigo_jogador = " + id;
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            sql = "delete from jogador where codigo = " + id;
            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            conn.commit();
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir jogador: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList<Object> jogadores = new ArrayList<>();

        try {
            String sql = "select * from jogador order by 2";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                Jogador jogador = (Jogador) consultarId(codigo);
                jogadores.add(jogador);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta cidade: " + e);
        }
        return jogadores;
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
            String sql = "SELECT * FROM jogador WHERE codigo = " + id + "";

            System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            Jogador jogador = null;
            if (resultado.next()) {
                jogador = new Jogador();
                Cidade cidade;
                jogador.setCodigo(resultado.getInt("codigo"));
                jogador.setNome(resultado.getString("nome"));
                jogador.setNascimento(resultado.getDate("data_nascimento"));
                jogador.setNumero(resultado.getInt("numero"));
                jogador.setTelefone(resultado.getString("telefone"));
                jogador.setAltura(resultado.getDouble("altura"));
                jogador.setPeso(resultado.getDouble("peso"));

                cidade = (Cidade) cidadedao.consultarId(resultado.getInt("cidade"));
                jogador.setCidade(cidade);
            }

            sql = "SELECT * FROM atributos_dos_jogadores WHERE codigo_jogador = " + id + "";
            System.out.println("sql: " + sql);
            resultado = st.executeQuery(sql);

            ArrayList<AtributoJogador> atributosjogador = new ArrayList<>();
            while (resultado.next()) {
                AtributoJogador atributojogador = new AtributoJogador();

                Atributo atributo = (Atributo) atributodao.consultarId(resultado.getInt("codigo_atributo"));
                atributojogador.setAtributo(atributo);
                atributojogador.setJogador(jogador);
                atributojogador.setPontuacao(resultado.getInt("pontuacao"));

                atributosjogador.add(atributojogador);
            }

            jogador.setAtributosJogador(atributosjogador);

            String sql_posicoes = "SELECT * FROM posicoes_do_jogador WHERE codigo_jogador = " + id + "";
            resultado = st.executeQuery(sql_posicoes);

            ArrayList<PosicaoJogador> posicoesjogador = new ArrayList<>();
            while (resultado.next()) {
                PosicaoJogador posicaojogador = new PosicaoJogador();

                Posicao posicao = (Posicao) posicaodao.consultarId(resultado.getInt("codigo_posicao"));
                posicaojogador.setPosicao(posicao);
                posicaojogador.setJogador(jogador);
                posicaojogador.setPrincipal(resultado.getBoolean("principal"));

                posicoesjogador.add(posicaojogador);
            }
            jogador.setPosicoesJogador(posicoesjogador);

            return jogador;
        } catch (Exception ex) {
            System.out.println("Erro consultar jogador = " + ex);
            return ex.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void gerarCSV() {
        // deixei fixo para o caminho da minha aplicacao
        Arquivo arquivo = new Arquivo("C:\\Users\\Lucas\\Desktop\\Univates\\SEMESTRE 2016 - B\\1 - SEGUNDA - PROGRAMAÇÃO PARA INTERNET\\PROJETO PROGRAMAÇÃO INTERNET\\LUCAS\\PROJETO WEB\\TeamManager\\web\\csv\\jogadores.csv");
        String saida;

        if (arquivo.abrirEscrita(false)) {
            for (Object o : consultarTodos()) {
                Jogador jogador = (Jogador) o;
                saida = jogador.getNome() + ";"
                        + jogador.getTelefone() + ";"
                        + jogador.getNascimento() + ";"
                        + jogador.getNumero() + ";"
                        + jogador.getPeso() + ";"
                        + jogador.getAltura() + ";"
                        + jogador.getCidade().getNome() + ";"
                        + jogador.getCidade().getUf();

                arquivo.escreverLinha(saida);
            }
        }
        arquivo.fecharArquivo();
    }

        public byte[] gerarRelatorio() {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();
            File reportFile = new File("C:\\Users\\Lucas\\Desktop\\Univates\\SEMESTRE 2016 - B\\1 - SEGUNDA - PROGRAMAÇÃO PARA INTERNET\\PROJETO PROGRAMAÇÃO INTERNET\\LUCAS\\PROJETO WEB\\TeamManager\\src\\java\\reports\\reportJogador.jasper");
            Map parameters = new HashMap();
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
    
}
