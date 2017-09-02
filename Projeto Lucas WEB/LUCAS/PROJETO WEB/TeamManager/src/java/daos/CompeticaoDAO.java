package daos;

import apoio.ConexaoBD;
import entidades.*;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompeticaoDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Competicao competicao = (Competicao) o;

        int retorno = 0;
        try {
            String sql = "insert into competicao values ("
                    + "default, "
                    + "'" + competicao.getDescricao().toUpperCase() + "', "
                    + "'" + competicao.getTipo().getCodigo() + "')";

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inserir competição: " + e);
            return e.toString();
        }
        return null;

    }

    @Override
    public String atualizar(Object o) {
        Competicao competicao = (Competicao) o;

        int retorno = 0;

        try {
            String sql = "update competicao "
                    + "set descricao =  '" + competicao.getDescricao().toUpperCase() + "', "
                    + " tipo = '" + competicao.getTipo().getCodigo() + "' "
                    + "where codigo = " + competicao.getCodigo();

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar competição: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        int retorno = 0;

        try {
            String sql = "delete from competicao "
                    + "where codigo = " + id;

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir competicao: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList<Object> competicoes = new ArrayList<>();

        try {
            String sql = "select * from competicao order by 2";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Competicao competicao = new Competicao();

                competicao.setCodigo(resultado.getInt("codigo"));
                competicao.setDescricao(resultado.getString("descricao"));
                TipoCompeticao tipo = new TipoCompeticao(resultado.getString("tipo").charAt(0));
                competicao.setTipo(tipo);

                competicoes.add(competicao);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta competição: " + e);
        }
        return competicoes;
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

            String sql = "SELECT * FROM competicao WHERE "
                    + "codigo = " + id + "";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Competicao competicao = new Competicao();
                competicao.setCodigo(resultado.getInt("codigo"));
                competicao.setDescricao(resultado.getString("descricao"));
                TipoCompeticao tipo = new TipoCompeticao(resultado.getString("tipo").charAt(0));
                competicao.setTipo(tipo);               
                return competicao;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Erro consultar competicao = " + ex);
            return ex.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
