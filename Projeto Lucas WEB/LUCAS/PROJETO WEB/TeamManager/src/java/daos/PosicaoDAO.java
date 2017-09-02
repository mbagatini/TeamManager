/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Posicao;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class PosicaoDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Posicao posicao = (Posicao) o;

        int retorno = 0;
        try {
            String sql = "insert into posicao values ("
                    + "default, "
                    + "'" + posicao.getDescricao().toUpperCase() + "')";

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inserir posicao: " + e);
            return e.toString();
        }
        return null;

    }

    @Override
    public String atualizar(Object o) {
        Posicao posicao = (Posicao) o;

        int retorno = 0;

        try {
            String sql = "update posicao "
                    + "set descricao =  '" + posicao.getDescricao().toUpperCase() + "' "
                    + "where codigo = " + posicao.getCodigo();

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar posicao: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        int retorno = 0;

        try {
            String sql = "delete from posicao "
                    + "where codigo = " + id;

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir posicao: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList<Object> atributos = new ArrayList<>();

        try {
            String sql = "select * from posicao order by 2";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Posicao posicao = new Posicao();

                posicao.setCodigo(resultado.getInt("codigo"));
                posicao.setDescricao(resultado.getString("descricao"));

                atributos.add(posicao);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta posicao: " + e);
        }
        return atributos;
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

            String sql = "SELECT * FROM posicao WHERE "
                    + "codigo = " + id + "";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Posicao posicao = new Posicao();
                posicao.setCodigo(resultado.getInt("codigo"));
                posicao.setDescricao(resultado.getString("descricao"));
                return posicao;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Erro consultar posicao = " + ex);
            return ex.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
