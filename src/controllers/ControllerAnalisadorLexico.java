package controllers;

public class ControllerAnalisadorLexico {
    
    public String lerIdentificador(String texto){
            
        int i = 0;
        do {
            char atual = texto.charAt(i);
            String id = Character.toString(atual);
            if(letra(id)== true) {                
                //System.out.println("Inicio do identif"); 
                i++;
                char atual2 = texto.charAt(i);
                String identif = "";
                boolean controleErro = true;
                while(atual2 != ';') {                      
                    identif += Character.toString(atual2);
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
                    System.out.println("Cadeia: "+identif);
                    if(cadeiaDeCaracteres(identif)){                        
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
        // Adicionar a regex
        return cadeia.matches("[a-z|A-Z|0-9]*");
    }
    public boolean identificador(String id){
    
        return id.matches("[a-z|A-Z|0-9|\\_]*");
    }
    public boolean letra(String letra){
    
        return letra.matches("[a-z|A-Z]");
    
    }
    public boolean separadorId(String separador){
    
        return separador.matches("[\32|\\,|\\.|\\;]");
    }
}
