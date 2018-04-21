package models;

public class Token {
    
    // Armazena os Tokens adicionados.
    private String tokens = "";

    /**
     * Adiciona um novo Token.
     * @param classe
     * @param valor
     * @param linha 
     */
    public void addToken(String classe, String valor, int linha) {
        
        if(linha != 0) {
            this.tokens += "<"+classe+", "+valor+", "+linha+">\n";
        } else {
            this.tokens += "<"+classe+", "+valor+">\n";
        }    
    }

    /**
     * Retorna os tokens adicionados.
     * @return 
     */
    public String getTokens() {
        return tokens;
    }
}
