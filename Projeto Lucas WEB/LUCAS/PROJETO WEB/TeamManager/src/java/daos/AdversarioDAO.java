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
public class AdversarioDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Adversario adversario = (Adversario) o;

        int retorno = 0;
        try {
            String sql = "insert into adversario values ("
                    + "default, "
                    + "'" + adversario.getNome().toUpperCase() + "', "
                    + "'" + adversario.getTelefone() + "', "
                    + "" + adversario.getCidade().getCodigo() + ")";

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inserir adversario: " + e);
            return e.toString();
        }
        return null;

    }

    @Override
    public String atualizar(Object o) {
        Adversario adversario = (Adversario) o;

        int retorno = 0;

        try {
            String sql = "update adversario "
                    + "set nome =  '" + adversario.getNome().toUpperCase() + "', "
                    + " telefone = '" + adversario.getTelefone() + "', "
                    + " cidades_codigo = " + adversario.getCidade().getCodigo() + " "
                    + "where codigo = " + adversario.getCodigo();

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar adversario: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        int retorno = 0;

        try {
            String sql = "delete from adversario "
                    + "where codigo = " + id;

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir adversario: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList<Object> adversarios = new ArrayList<>();

        try {
            String sql = "select * from adversario";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Adversario adversario = new Adversario();
                Cidade cidade = new Cidade();
                

                adversario.setCodigo(resultado.getInt("codigo"));
                adversario.setNome(resultado.getString("nome"));
                adversario.setTelefone(resultado.getString("telefone"));
                
                cidade = (Cidade) new CidadeDAO().consultarId(resultado.getInt("cidades_codigo"));
                adversario.setCidade(cidade);

                adversarios.add(adversario);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta adversario: " + e);
        }
        return adversarios;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String uf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM adversario WHERE "
                    + "codigo = " + id + "";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Cidade cidade = new Cidade();
                
                Adversario adversario = new Adversario();
                adversario.setCodigo(resultado.getInt("codigo"));
                adversario.setNome(resultado.getString("nome"));
                adversario.setTelefone(resultado.getString("telefone"));
                cidade = (Cidade) new CidadeDAO().consultarId(resultado.getInt("cidades_codigo"));
                adversario.setCidade(cidade);
                return adversario;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Erro consultar adversario = " + ex);
            return ex.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
