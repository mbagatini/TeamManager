/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author lucas.motta
 */
public class PosicaoJogador {
    
    private Jogador jogador;
    private Posicao posicao;
    private Boolean principal;

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
     * @return the posicao
     */
    public Posicao getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    /**
     * @return the principal
     */
    public Boolean getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
    
    /**
     *
     * @return the posicao
     */
    @Override
    public String toString(){
        return this.posicao.toString();
    }
}
