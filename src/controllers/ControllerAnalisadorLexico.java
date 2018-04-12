package controllers;

import persistencia.ManipulaArquivo;

public class ControllerAnalisadorLexico {
     
    public void start() {
        ManipulaArquivo ma = new ManipulaArquivo();
        String[] arquivos = ma.getArquivos();
        for(int i=0; i<arquivos.length; i++) {
            String texto = ma.lerArquivo(arquivos[i]);
            if(!texto.isEmpty()) {
                System.out.println(arquivos[i]);
                System.out.println(texto);
                lerCaracteres(texto);
                System.out.println("--------------------------------------------");
            }            
        }
    }
    public void lerCaracteres(String texto){

        int i = 0;
        int linha = 1;
        do {
            char atual = texto.charAt(i);                                 
            if(operadoresLogicos(Character.toString(atual))){
                int j = i+1;
                char atual2 = texto.charAt(j);
                if(atual == '&') {
                    if(atual2 == '&') {
                        System.out.println("O.L.: &&");
                        i++;
                    } else {
                        System.out.println("O.L.: "+atual);
                    }
                } else if(atual == '|') {                    
                    if(atual2 == '|') {
                        System.out.println("O.L.: ||");
                        i++;
                    } else {
                        System.out.println("O.L.: "+atual);
                    }
                }
            } else if(operadoresRelacionais(Character.toString(atual))){
                int j = i+1;
                char atual2 = texto.charAt(j);                
                if(atual == '!') {
                    if(atual2 == '=') {
                        System.out.println("O.R.: !=");
                        i++;
                    } else {
                        System.out.println("O.L.: "+atual);
                    }
                } else if(atual == '=') {
                    if(atual2 == '=') {
                        System.out.println("O.R.: ==");
                        i++;
                    } else {
                        System.out.println("O.R.: "+atual);
                    }
                } else if(atual == '<') {
                    if(atual2 == '=') {
                        System.out.println("O.R.: <=");
                        i++;
                    } else {
                        System.out.println("O.R.: "+atual);
                    }
                } else if(atual == '>') {
                    if(atual2 == '=') {
                        System.out.println("O.R.: >=");
                        i++;
                    } else {
                        System.out.println("O.R.: "+atual);
                    }
                }
            } else if(delimitadores(Character.toString(atual))){
                System.out.println("Delimitador: "+atual);
            } else if(operadoresAritmeticos(Character.toString(atual))) {
                int j = i+1;
                char atual2 = texto.charAt(j);                                
                if(atual == '/'){                    
                    // Verifica se eh um comentario de linha
                    if(atual2 == '/') {
                        while(atual2 != '\n') { 
                            i++; 
                            // Verifica se chegou ao final da String
                            if(i >= texto.length()) {
                                System.out.println("Fim de Texto!");
                                break;
                            } 
                            atual2 = texto.charAt(i);                        
                        }    
                        System.out.println("Comentario de linha ignorado!");
                        // Incrementa a contagem de linha
                        linha++;  
                    // Verifica se eh um Comentario de Bloco    
                    } else if (atual2 == '*') {                        
                        char anterior = 'x';
                        boolean chave = true;
                        while(chave) { 
                            if(anterior == '*' && atual2 == '/'){
                                chave = false;                                
                            }
                            if(atual2 == '\n') {
                                linha++;
                            }
                            i++; 
                            // Verifica se chegou ao final da String
                            if(i >= texto.length()) {
                                System.out.println("Fim de Texto!");
                                break;
                            } 
                            anterior = atual2;
                            atual2 = texto.charAt(i);                        
                        }
                        System.out.println("Comentario de bloco ignorado!");
                    } else {
                        // adiciona / ao token 
                        System.out.println("OP.A.: /");
                    }
                } else if(atual == '+') {
                    if(atual2 == '+') {
                        i++;
                        // adiciona ++ ao token
                        System.out.println("OP.A.: ++");
                    } else {
                        // adiciona + ao token
                        System.out.println("OP.A.: +");
                    }                    
                } else if(atual == '-') {
                    if(atual2 == '-') {
                        i++;
                        // adiciona -- ao token
                        System.out.println("OP.A.: --");
                    } else {
                        // adiciona - ao token
                        System.out.println("OP.A.: -");
                    }
                } else if(atual == '*') {
                    // Adiciona * ao token
                    System.out.println("OP.A.: *");
                }
            } else if(letra(Character.toString(atual))) {                
                String palavra = "";
                char atual2 = texto.charAt(i);
                boolean controleErro = true;                  
                while(condicaoFinal(Character.toString(atual2))) { 
                    palavra += Character.toString(atual2);
                    i++;                    
                    if(i >= texto.length()) {
                        // Erro - Fim de Texto.
                        System.out.println("Erro na linha "+linha+" - Fim de Texto!");
                        controleErro = false;
                        break;
                    } else {
                        atual2 = texto.charAt(i);
                    }                                             
                }
                if(controleErro) {
                    if(palavrasReservadas(palavra)) {
                        System.out.println("Palavra Reservada: "+palavra);
                    } else {
                        System.out.println("Identificador: "+palavra);
                    }
                }
            } else if(atual == '"') {   
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
                        } else {
                            cadeia += Character.toString(atual2);
                            i++; 
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
            
    public boolean palavrasReservadas(String palavra) {
        
        if(palavra.equals("const")) {
            return true;
        } else if(palavra.equals("var")) {
            return true;
        } else if(palavra.equals("struct")) {
            return true;
        } else if(palavra.equals("typedef")) {
            return true;
        } else if(palavra.equals("procedure")) {
            return true;
        } else if(palavra.equals("function")) {
            return true;
        } else if(palavra.equals("return")) {
            return true;
        } else if(palavra.equals("start")) {
            return true;
        } else if(palavra.equals("if")) {
            return true;
        } else if(palavra.equals("then")) {
            return true;
        } else if(palavra.equals("else")) {
            return true;
        } else if(palavra.equals("while")) {
            return true;
        } else if(palavra.equals("scan")) {
            return true;
        } else if(palavra.equals("print")) {
            return true;
        } else if(palavra.equals("int")) {
            return true;
        } else if(palavra.equals("float")) {
            return true;
        } else if(palavra.equals("bool")) {
            return true;
        } else if(palavra.equals("string")) {
            return true;
        } else if(palavra.equals("true")) {
            return true;
        } else if(palavra.equals("false")) {
            return true;
        } else if(palavra.equals("extends")) {
            return true;
        }
        return false;
    }
    
    public boolean operadoresAritmeticos(String caractere) {
        return caractere.matches("[\\+|\\-|\\*|/]");
    }
    
    public boolean operadoresRelacionais(String caractere) {
        return caractere.matches("[!|=|<|>|]");
    }
    
    public boolean operadoresLogicos(String caractere) {
        if(caractere.equals("|")) {
            return true;
        } else {
            return caractere.matches("[&]");
        }
    }
    
    public boolean delimitadores(String caractere) {
        return caractere.matches("[;|,|\\(|\\)|\\[|\\]|\\{|\\}|\\.]");
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
    
    public boolean condicaoFinal(String caractere) {
        return caractere.matches("[a-z|A-Z|0-9|_]*");
    }
    
    public boolean letra(String letra){
        return letra.matches("[a-z|A-Z]"); 
    }
}
