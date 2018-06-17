package models;

import java.util.ArrayList;

public class Token {
    
    // Armazena os Tokens adicionados.
    private ArrayList<String> listTokens;

    public Token() {
        listTokens = new ArrayList();
    }
    
    /**
     * Adiciona um novo Token.
     * @param classe
     * @param valor
     * @param linha 
     */
    public void addToken(String classe, String valor, int linha) {
        
        if(linha != 0) {
            this.listTokens.add("<"+classe+", "+valor+", "+linha+">");
        } else {
            this.listTokens.add("<"+classe+", "+valor+">");
        }    
    }

    /**
     * Retorna os tokens adicionados.
     * @return 
     */
    public ArrayList<String> getTokens() {
        return this.listTokens;
    }
}
