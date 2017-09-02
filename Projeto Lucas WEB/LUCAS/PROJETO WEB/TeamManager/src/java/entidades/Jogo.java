/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lucas.motta
 */
public class Jogo {

    private int codigo;
    private Date data;
    private Time hora;
    private char lugar;
    private Cidade cidade;
    private Adversario adversario;
    private Competicao competicao;
    private ArrayList<JogadorJogo> jogadoresJogo;
    private int pontuacaotime;
    private int pontuacaoadversario;
    private char status;

    public int getPontuacaotime() {
        return pontuacaotime;
    }

    public void setPontuacaotime(int pontuacaotime) {
        this.pontuacaotime = pontuacaotime;
    }

    public int getPontuacaoadversario() {
        return pontuacaoadversario;
    }

    public void setPontuacaoadversario(int pontuacaoadversario) {
        this.pontuacaoadversario = pontuacaoadversario;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the hour
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hour the hour to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * @return the lugar
     */
    public char getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(char lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the cidade
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the adversario
     */
    public Adversario getAdversario() {
        return adversario;
    }

    /**
     * @param adversario the adversario to set
     */
    public void setAdversario(Adversario adversario) {
        this.adversario = adversario;
    }

    /**
     * @return the competicao
     */
    public Competicao getCompeticao() {
        return competicao;
    }

    /**
     * @param competicao the competicao to set
     */
    public void setCompeticao(Competicao competicao) {
        this.competicao = competicao;
    }

    /**
     * @return the jogadoresJogo
     */
    public ArrayList<JogadorJogo> getJogadoresJogo() {
        return jogadoresJogo;
    }

    /**
     * @param jogadoresJogo the jogadoresJogo to set
     */
    public void setJogadoresJogo(ArrayList<JogadorJogo> jogadoresJogo) {
        this.jogadoresJogo = jogadoresJogo;
    }
    
    /**
     *
     * @return the adiversario.getNome()
     */
    @Override
    public String toString(){
        return this.adversario.getNome();
    }

}
