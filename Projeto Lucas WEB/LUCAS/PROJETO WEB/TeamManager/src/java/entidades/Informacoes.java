/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import apoio.ConexaoBD;
import daos.JogoDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Informacoes {

    public int getVitorias() {

        int retorno = 0;

        try {

            String sql = "SELECT count(*) from jogo "
                    + "where jogo.pontos_time > jogo.pontos_adversario "
                    + " and jogo.status = 'F' ";

            ResultSet resul = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resul.next();
            retorno = resul.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

    public int getEmpates() {
        int retorno = 0;

        try {

            String sql = "SELECT count(*) from jogo "
                    + "where jogo.pontos_time = jogo.pontos_adversario "
                    + " and jogo.status = 'F' ";

            ResultSet resul = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resul.next();
            retorno = resul.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public int getDerrotas() {
        int retorno = 0;

        try {

            String sql = "SELECT count(*) from jogo "
                    + "where jogo.pontos_time < jogo.pontos_adversario "
                    + " and jogo.status = 'F' ";

            ResultSet resul = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resul.next();
            retorno = resul.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public int getJogadores() {
        int retorno = 0;

        try {

            String sql = "SELECT count(*) from jogador ";

            ResultSet resul = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resul.next();
            retorno = resul.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public Jogo getJogo() {
        Jogo ultimojogo;
        int retorno = 0;

        try {

            String sql = "Select codigo "
                    + "from jogo "
                    + "where jogo.status = 'F' "
                    + "order by data desc "
                    + "limit 1 ";

            ResultSet resul = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resul.next();
            retorno = resul.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ultimojogo = (Jogo) new JogoDAO().consultarId(retorno);
        
        return ultimojogo;

    }

}
