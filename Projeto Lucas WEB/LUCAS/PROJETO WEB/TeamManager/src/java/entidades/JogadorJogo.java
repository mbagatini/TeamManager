/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Lucas
 */
public class JogadorJogo {

    private Jogador jogador;
    private Jogo jogo;
    private Boolean titular;

    /**
     * @return the jogador
     */
    public Jogador getJogador() {
        return jogador;
    }

    /**
     * @param jogador the jogador to set
     */
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     * @return the jogo
     */
    public Jogo getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    /**
     * @return the titular
     */
    public Boolean getTitular() {
        return titular;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(Boolean titular) {
        this.titular = titular;
    }
    
    /**
     *
     * @return the jogador.getNome()
     */
    @Override
    public String toString(){
        return this.jogador.getNome();
    }
    
}
