package models;

public class Token {
    
    private String tokens = "";
    
    public void criaToken(String classe, String valor, int linha) {
        
        if(linha != 0) {
            tokens += "<"+classe+", "+valor+", "+linha+">\n";
        } else {
            tokens += "<"+classe+", "+valor+">\n";
        }        
    }
    
    public String getTokens() {
        return this.tokens;
    }
}
