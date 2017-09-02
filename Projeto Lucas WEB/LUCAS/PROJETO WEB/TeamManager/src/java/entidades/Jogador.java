/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lucas.motta
 */
public class Jogador {
    
    private int codigo;
    private String nome;
    private Date nascimento;
    private int numero;
    private String telefone;
    private double peso;
    private double altura;
    private Cidade cidade;
    private ArrayList<AtributoJogador> atributosJogador;
    private ArrayList<PosicaoJogador> posicoesJogador;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the nascimento
     */
    public Date getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
     * @return the atributosJogador
     */
    public ArrayList<AtributoJogador> getAtributosJogador() {
        return atributosJogador;
    }

    /**
     * @param atributosJogador the atributosJogador to set
     */
    public void setAtributosJogador(ArrayList<AtributoJogador> atributosJogador) {
        this.atributosJogador = atributosJogador;
    }

    /**
     * @return the posicoesJogador
     */
    public ArrayList<PosicaoJogador> getPosicoesJogador() {
        return posicoesJogador;
    }

    /**
     * @param posicoesJogador the posicoesJogador to set
     */
    public void setPosicoesJogador(ArrayList<PosicaoJogador> posicoesJogador) {
        this.posicoesJogador = posicoesJogador;
    }
    
    /**
     *
     * @return the nome
     */
    @Override
    public String toString(){
        return this.nome;
    }

}