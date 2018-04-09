package controllers;

public class ControllerAnalisadorLexico {
    
    public String lerCaracteres(String texto){
        
        int i = 0;
        int linha = 0;
        do {
            char atual = texto.charAt(i);           
            
            if(atual == '"') {                
                i++;
                char atual2 = texto.charAt(i);                
                String cadeia = "";
                boolean controleErro = true;
                while(atual2 != '"') {
                    
                    if(atual2 == '\n'){
                        linha++;
                    }
                    
                    if(atual2 == '\\'){
                        char verifica = texto.charAt(i+1);
                        if(verifica == '"') {
                            cadeia += '\"';
                            i = i+2;
                        } 
                    } else {
                        cadeia += Character.toString(atual2);
                        i++;   
                    }    
                    if(i >= texto.length()) {
                        // Erro - Fecha aspas não encontrado.
                        System.out.println("Erro na linha "+linha+" - Fecha aspas não encontrado!");
                        controleErro = false;
                        break;
                    } else {
                        atual2 = texto.charAt(i);
                    } 
                }
                
                if(controleErro) {
                    System.out.println("Cadeia: "+cadeia);
                    if(cadeiaDeCaracteres(cadeia)){                        
                        System.out.println("Cadeia aceita!");
                    }else {
                        System.out.println("Erro na linha "+linha+" - Caractere inválido!");  
                    }                      
                }                           
            } else if(atual == '\n'){
                    linha++;
            }
            i++;        
        } while(i < texto.length());   
        return null;
    }
    
    public boolean cadeiaDeCaracteres(String cadeia) {
        
        String especiais = "\\]|\\[|\\+|\\$|\\^|\\{|\\}|\\|\\?|\\.|\\(|\\)|\\*|\\-|\\\\|\"";
        String outros = "a-z|A-Z|0-9|";
        String asc = " |#|!|%|´|`|@|/|~|_|<|>|=|:|;|,|'|&";
        return cadeia.matches("["+especiais+outros+asc+"]*");
    }
}
