package controllers;

public class ControllerAnalisadorLexico {
    
    public String lerCaracteres(String texto){
        
        int i = 0;
        do {
            char atual = texto.charAt(i);
            if(atual == '"') {                
                System.out.println("Inicio de cadeia encontrado"); 
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
                    }
                    System.out.println("Cadeia rejeitada");  
                }                           
            }
            i++;        
        } while(i < texto.length());   
        return null;
    }
    
    
    public boolean cadeiaDeCaracteres(String cadeia) {
        // Adicionar a regex
        return cadeia.matches("[a-zA-Z|0-9|_]*");
    }
}
