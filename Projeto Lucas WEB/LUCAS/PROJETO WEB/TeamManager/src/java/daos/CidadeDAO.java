/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.*;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class CidadeDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Cidade cidade = (Cidade) o;

        int retorno = 0;
        try {
            String sql = "insert into cidade values ("
                    + "default, "
                    + "'" + cidade.getNome().toUpperCase() + "', "
                    + "'" + cidade.getUf() + "')";

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inserir cidade: " + e);
            return e.toString();
        }
        return null;

    }

    @Override
    public String atualizar(Object o) {
        Cidade cidade = (Cidade) o;

        int retorno = 0;

        try {
            String sql = "update cidade "
                    + "set nome =  '" + cidade.getNome().toUpperCase() + "', "
                    + " uf = '" + cidade.getUf()+ "' "
                    + "where codigo = " + cidade.getCodigo();

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar cidade: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        int retorno = 0;

        try {
            String sql = "delete from cidade "
                    + "where codigo = " + id;

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir cidade: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList<Object> cidades = new ArrayList<>();

        try {
            String sql = "select * from cidade order by 3,2";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Cidade cidade = new Cidade();

                cidade.setCodigo(resultado.getInt("codigo"));
                cidade.setNome(resultado.getString("nome"));
                cidade.setUf(resultado.getString("uf"));

                cidades.add(cidade);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta cidade: " + e);
        }
        return cidades;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String uf) {

        ArrayList<Object> cidades = new ArrayList<>();

        try {
            String sql = "select * from cidade where uf = '" + uf + "' order by 3,2";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Cidade cidade = new Cidade();

                cidade.setCodigo(resultado.getInt("codigo"));
                cidade.setNome(resultado.getString("nome"));
                cidade.setUf(resultado.getString("uf"));

                cidades.add(cidade);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta cidade: " + e);
        }
        return cidades;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM cidade WHERE "
                    + "codigo = " + id + "";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Cidade cidade = new Cidade();
                cidade.setCodigo(resultado.getInt("codigo"));
                cidade.setNome(resultado.getString("nome"));
                cidade.setUf(resultado.getString("uf"));
                return cidade;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Erro consultar cidade = " + ex);
            return ex.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
