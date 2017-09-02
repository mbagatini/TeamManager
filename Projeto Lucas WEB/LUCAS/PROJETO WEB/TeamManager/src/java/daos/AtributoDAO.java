/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Atributo;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class AtributoDAO implements IDAO {
    
    @Override
    public String salvar(Object o, char op) {
        Atributo atributo = (Atributo) o;

        int retorno = 0;
        try {
            String sql = "insert into atributo values ("
                    + "default,"
                    + "'" + atributo.getDescricao().toUpperCase() + "')";

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inserir atributo: " + e);
            return e.toString();
        }
        return null;

    }

    @Override
    public String atualizar(Object o) {
        Atributo atributo = (Atributo) o;

        int retorno = 0;

        try {
            String sql = "update atributo "
                    + "set descricao =  '" + atributo.getDescricao().toUpperCase() + "' "
                    + "where codigo = " + atributo.getCodigo();

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar atributo: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        int retorno = 0;

        try {
            String sql = "delete from atributo "
                    + "where codigo = " + id;

            retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir atributo: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList<Object> atributos = new ArrayList<>();

        try {
            String sql = "select * from atributo order by 2";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Atributo atributo = new Atributo();

                atributo.setCodigo(resultado.getInt("codigo"));
                atributo.setDescricao(resultado.getString("descricao"));

                atributos.add(atributo);
            }

        } catch (Exception e) {
            System.out.println("Erro consulta atributos: " + e);
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

            String sql = "SELECT * FROM atributo WHERE "
                    + "codigo = " + id + "";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Atributo atributo = new Atributo();
                atributo.setCodigo(resultado.getInt("codigo"));
                atributo.setDescricao(resultado.getString("descricao"));
                return atributo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Erro consultar atributo = " + ex);
            return ex.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
