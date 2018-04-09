package controllers;

public class ControllerAnalisadorLexico {
    
    /**
     * Le caractere por caractere de uma String.
     * @param texto String a ser lida
     * @return 
     */
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
}
