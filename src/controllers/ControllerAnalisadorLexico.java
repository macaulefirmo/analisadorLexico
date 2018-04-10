package controllers;

public class ControllerAnalisadorLexico {
    
    public String lerIdentificador(String texto){
            
        int i = 0;
        int linha = 0;
        do {
            char atual = texto.charAt(i);
            String id = Character.toString(atual);
            if(letra(id)== true) {//verifica se inicia em uma letra                
                //System.out.println("Inicio do identif"); 
                
                char atual2 = texto.charAt(i);
                i++;//incrementa depois já que a primeira letra compoe o nome do identificador.
                if(atual2=='\n'){ //incrementa a linha
                linha++;
                }
                String identif = "";
                boolean controleErro = true;
                String id2 = Character.toString(atual2);
                while(!separadorId(id2)) {//enquanto os caracteres lidos forem diferentes desse conjunto o laço continua  
                    
                    
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
            //CRIA UMA LISTA E ADICIONA AQUI O PRIMEIRO IDENTIFICADOR NA LISTA  - IDEIA - 
                if(controleErro) {
                    System.out.println("Identificadores: "+identif);
                    if(cadeiaDeCaracteres(identif)){                        
                        System.out.println("Cadeia aceita!");
                    }else {
                      System.out.println("Erro na linha:"+linha+" - Cadeia rejeitada");  
                    }                      
                }                           
            }
            i++;        
        } while(i < texto.length());   
        return null;
    }
    
    public void lerCaracteres(String texto){

        
        int i = 0;
        int linha = 0;
        do {
            char atual = texto.charAt(i);             
            if(atual == '"') {   
                // Inicio da cadeia de caracteres
                i++;
                char atual2 = texto.charAt(i);                
                String cadeia = "";
                boolean controleErro = true;
                // Cadeia de caracteres - tudo que esta entre "aspas"
                while(atual2 != '"') { 
                    // Incrementa a contagem de linha
                    if(atual2 == '\n'){
                        linha++;
                    }                     
                    // Verifica o \"
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
                    // Verifica se chegou ao final da String
                    if(i >= texto.length()) {
                        // Erro - Fecha aspas não encontrado.
                        System.out.println("Erro na linha "+linha+" - Fecha aspas não encontrado!");
                        controleErro = false;
                        break;
                    } else {
                        atual2 = texto.charAt(i);
                    } 
                }
                // Verifica se houve erro.
                if(controleErro) {
                    System.out.println("Cadeia: "+cadeia);
                    // Verifica a validade da cadeia de caracteres.
                    if(cadeiaDeCaracteres(cadeia)){                        
                        System.out.println("Cadeia aceita!");
                    } else {
                        // Erro - Caractere invalido.
                        System.out.println("Erro na linha "+linha+" - Caractere inválido!");  
                    }                      
                }                           
            } else if(atual == '\n'){
                // Incrementa o numero da linha. 
                linha++;
            }
            i++;        
        } while(i < texto.length());     
    }
    
    /**
     * Verifica se a cadeia recebida eh valida.
     * @param cadeia String com a cadeia de caracteres
     * @return 
     */
    public boolean cadeiaDeCaracteres(String cadeia) {
        // Caracteres especiais - Os caracteres utilizados pela expressao regular.
        String especiais = "\\]|\\[|\\+|\\$|\\^|\\{|\\}|\\|\\?|\\.|\\(|\\)|\\*|\\-|\\\\|\"";
        // Outros - Letras e numeros.
        String outros = "a-z|A-Z|0-9|";
        // Asc - Os caracteres da tabela ascII que nao foram encluidos acima.
        String asc = " |#|!|%|´|`|@|/|~|_|<|>|=|:|;|,|'|&";
        return cadeia.matches("["+especiais+outros+asc+"]*");
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
