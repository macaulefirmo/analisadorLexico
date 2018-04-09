package controllers;

public class ControllerAnalisadorLexico {
    
    public String lerCaracteres(String texto){
        
        int i = 0;
        do {
            char atual = texto.charAt(i);
            if(atual == '"') {                
                //System.out.println("Inicio de cadeia encontrado"); 
                i++;
                char atual2 = texto.charAt(i);
                String cadeia = "";
                boolean controleErro = true;
                while(atual2 != '"') {                      
                    cadeia += Character.toString(atual2);
                    i++;                    
                    if(i >= texto.length()) {
                        // Erro - Fecha aspas não encontrado.
                        System.out.println("Erro - Fecha aspas não encontrado!");
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
                      System.out.println("Erro - Cadeia rejeitada");  
                    }                      
                }                           
            }
            i++;        
        } while(i < texto.length());   
        return null;
    }
    
    public boolean cadeiaDeCaracteres(String cadeia) {
        
        String especiais = "\\]|\\[|\\+|\\$|\\^|\\{|\\}|\\|\\?|\\.|\\(|\\)|\\*|\\-|\\\\|";
        String outros = "a-z|A-Z|0-9|";
        String asc = " |#|!|%|´|`|@|/|~|_|<|>|=|:|;|,|'|&";
        return cadeia.matches("["+especiais+outros+asc+"]*");
    }
}
