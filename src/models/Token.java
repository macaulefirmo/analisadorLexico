package models;

public class Token {
    
    private String tokens = "";

    public void addToken(String classe, String valor, int linha) {
        
        if(linha != 0) {
            this.tokens += "<"+classe+", "+valor+", "+linha+">\n";
        } else {
            this.tokens += "<"+classe+", "+valor+">\n";
        }    
    }

    public String getTokens() {
        return tokens;
    }
}
