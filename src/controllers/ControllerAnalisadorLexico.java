package controllers;

import models.TabelaSimbolo;
import models.Token;
import persistencia.ManipulaArquivo;

public class ControllerAnalisadorLexico {
           
    public void start() {
        ManipulaArquivo ma = new ManipulaArquivo();
        String[] arquivos = ma.getArquivos();
        for(int i=0; i<arquivos.length; i++) {
            String texto = ma.lerArquivo(arquivos[i]);
            String[] arrayS = arquivos[i].split("\\\\");
            System.out.println("Compilando arquivo \""+arrayS[1]+"\" ...");
            if(!texto.isEmpty()) {
                Token tokens = new Token();
                TabelaSimbolo simbolos = new TabelaSimbolo();
                String erros = lerCaracteres(texto, tokens, simbolos);                
                ma.salvaArquivo(tokens.getTokens(), erros, "Arquivos/Compilados/compilado_"+arrayS[1]);
                System.out.println("Arquivo compilado com sucesso!");
            }            
        }
    }
    public String lerCaracteres(String texto, Token tokens, TabelaSimbolo simbolos){

        int i = 0;
        int linha = 1;
        String erros = "";
        do {
            char atual = texto.charAt(i);                                 
            if(operadoresLogicos(Character.toString(atual))){
                int j = i+1;
                char atual2 = texto.charAt(j);
                if(atual == '&') {
                    if(atual2 == '&') {
                        // &&
                        tokens.addToken("Op.Logico", "&&", linha);
                        i++;
                    } else {
                        tokens.addToken("Op.Logico", Character.toString(atual), linha);
                    }
                } else if(atual == '|') {                    
                    if(atual2 == '|') {
                        tokens.addToken("Op.Logico", "||", linha);
                        i++;
                    } else {
                        tokens.addToken("Op.Logico", Character.toString(atual), linha);
                    }
                }
            } else if(operadoresRelacionais(Character.toString(atual))){
                int j = i+1;
                char atual2 = texto.charAt(j);                
                if(atual == '!') {
                    if(atual2 == '=') {                        
                        tokens.addToken("Op.Relacional", "!=", linha);
                        i++;
                    } else {
                        tokens.addToken("Op.Logico", Character.toString(atual), linha);
                    }
                } else if(atual == '=') {
                    if(atual2 == '=') {
                        tokens.addToken("Op.Relacional", "==", linha);
                        i++;
                    } else {
                        tokens.addToken("Op.Relacional", Character.toString(atual), linha);
                    }
                } else if(atual == '<') {
                    if(atual2 == '=') {
                        tokens.addToken("Op.Relacional", "<=", linha);
                        i++;
                    } else {
                        tokens.addToken("Op.Relacional", Character.toString(atual), linha);
                    }
                } else if(atual == '>') {
                    if(atual2 == '=') {
                        tokens.addToken("Op.Relacional", ">=", linha);
                        i++;
                    } else {
                        tokens.addToken("Op.Relacional", Character.toString(atual), linha);
                    }
                }
            } else if(delimitadores(Character.toString(atual))){
                tokens.addToken("Delimitador", Character.toString(atual), linha);
            } else if(operadoresAritmeticos(Character.toString(atual))) {
                int j = (i+1);
                char atual2 = texto.charAt(j);                                
                if(atual == '/') {                    
                    // Verifica se eh um comentario de linha
                    if(atual2 == '/') {
                        while(atual2 != '\n') { 
                            i++; 
                            // Verifica se chegou ao final da String
                            if(i >= texto.length()) {
                                break;
                            } 
                            atual2 = texto.charAt(i);                        
                        }    
                        // Incrementa a contagem de linha
                        linha++;  
                    // Verifica se eh um Comentario de Bloco    
                    } else if (atual2 == '*') {                        
                        char anterior = 'x';
                        boolean chave = true;
                        while(chave) { 
                            if(anterior == '*' && atual2 == '/'){
                                chave = false;                                
                            } else {
                                if(atual2 == '\n') {
                                    linha++;
                                }
                                i++; 
                                // Verifica se chegou ao final da String
                                if(i >= texto.length()) {
                                    erros += "Erro na linha "+linha+" - Fecha comentario não encontrado!\n";
                                    break;
                                } 
                                anterior = atual2;
                                atual2 = texto.charAt(i); 
                            }                                                   
                        }
                    } else {
                        // adiciona / ao token 
                        tokens.addToken("Op.Aritmetico", Character.toString(atual), linha);                        
                    }
                } else if(atual == '+') {
                    if(atual2 == '+') {
                        // adiciona ++ ao token
                        tokens.addToken("Op.Aritmetico", "++", linha); 
                        i++;
                    } else {
                        // adiciona + ao token
                        tokens.addToken("Op.Aritmetico", Character.toString(atual), linha);
                    }                    
                } else if(atual == '-') {
                    if(atual2 == '-') {
                        // adiciona -- ao token
                        tokens.addToken("Op.Aritmetico", "--", linha);  
                        i++;
                    } else {
                        if(digito(Character.toString(atual2)) || espaco(Character.toString(atual2)) ) {
                            while(espaco(Character.toString(atual2))){
                                if(atual2 == '\n') {
                                    linha++;
                                }
                                j++;
                                if(j >= texto.length()) {                                
                                    break;
                                } 
                                atual2 = texto.charAt(j);
                            }
                            if(digito(Character.toString(atual2))) {
                                String numero = "-";
                                while(digito(Character.toString(atual2))){                                
                                    numero += Character.toString(atual2);
                                    j++;
                                    if(j >= texto.length()) {                                
                                        break;
                                    } 
                                    atual2 = texto.charAt(j);
                                }
                                if(atual2 == '.') {
                                    numero += Character.toString(atual2);
                                    j++;
                                    if(j < texto.length()) {                                
                                        atual2 = texto.charAt(j);
                                        if(digito(Character.toString(atual2))) {
                                            while(digito(Character.toString(atual2))){                                
                                                numero += Character.toString(atual2);
                                                j++;
                                                if(j >= texto.length()) {                                
                                                    break;
                                                } 
                                                atual2 = texto.charAt(j);
                                            }
                                            tokens.addToken("Numero", numero, linha);  
                                            i = (j-1);
                                        } else {
                                            erros += "Erro na linha "+linha+" - Falta de dígito apos o '.'!\n";
                                            i = (j-1);
                                        } 
                                    } else {
                                        erros += "Erro na linha "+linha+" - Falta de dígito apos o '.'!\n";
                                    }                                                                    
                                } else {
                                    tokens.addToken("Numero", numero, linha);  
                                    i = (j-1);
                                }
                            } else {
                                // adiciona - ao token   
                                tokens.addToken("Op.Aritmetico", Character.toString(atual), linha);  
                                i = j-1;
                            }
                        } else {
                            // adiciona - ao token   
                            tokens.addToken("Op.Aritmetico", Character.toString(atual), linha);
                        }                          
                    }
                } else if(atual == '*') {
                    // Adiciona * ao token
                    tokens.addToken("Op.Aritmetico", Character.toString(atual), linha);       
                }
            } else if(letra(Character.toString(atual))) {                
                String palavra = Character.toString(atual);
                int j = (i+1);
                char atual2 = texto.charAt(j);               
                while(condicaoFinal(Character.toString(atual2))) { 
                    palavra += Character.toString(atual2);
                    j++;                    
                    if(j >= texto.length()) {
                        break;
                    } else {
                        atual2 = texto.charAt(j);
                    }                                             
                }
                if(palavrasReservadas(palavra)) {
                    tokens.addToken("Palavra_Reservada", palavra, linha);       
                } else {      
                    String nome;
                    if(simbolos.contem(palavra)) {
                        nome = simbolos.getNomeSimbolo(palavra);
                    } else {
                        nome = simbolos.addSimbolo(palavra);
                    }
                    tokens.addToken(nome, palavra, linha);                     
                }
                i = (j-1);
            } else if(atual == '"') {   
                // Inicio da cadeia de caracteres
                i++;
                char atual2 = texto.charAt(i);   
                int linhaInicial = linha;
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
                            i = (i+2);
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
                        if(linhaInicial == linha) {
                            erros += "Erro na linha "+linha+" - Fecha aspas não encontrado!\n";
                        } else {
                            erros += "Erro entre as linhas "+linhaInicial+" e "+linha+" - Fecha aspas não encontrado!\n";
                        }  
                        controleErro = false;
                        break;
                    } else {
                        atual2 = texto.charAt(i);
                    } 
                }
                if(controleErro) {
                   // Verifica a validade da cadeia de caracteres.
                    if(cadeiaDeCaracteres(cadeia)){                        
                        tokens.addToken("Cadeia_de_Caracteres", cadeia, linha); 
                    } else {
                        if(linhaInicial == linha) {
                            erros += "Erro na linha "+linha+" - Cadeia de caracteres inválida!\n";
                        } else {
                            erros += "Erro entre as linhas "+linhaInicial+" e "+linha+" - Cadeia de caracteres inválida!\n";
                        } 
                    }   
                }                                       
            } else if(digito(Character.toString(atual))) {
                String numero = Character.toString(atual);
                int j = (i+1);
                char atual2 = texto.charAt(j); 
                while(digito(Character.toString(atual2))){                                
                    numero += Character.toString(atual2);
                    j++;
                    if(j >= texto.length()) {                                
                        break;
                    } 
                    atual2 = texto.charAt(j);
                }
                if(atual2 == '.') {
                    numero += Character.toString(atual2);
                    j++;
                    if(j < texto.length()) {                                
                        atual2 = texto.charAt(j);
                        if(digito(Character.toString(atual2))) {
                            while(digito(Character.toString(atual2))){                                
                                numero += Character.toString(atual2);
                                j++;
                                if(j >= texto.length()) {                                
                                    break;
                                } 
                                atual2 = texto.charAt(j);
                            }
                            tokens.addToken("Numero", numero, linha);  
                            i = (j-1);
                        } else {
                            erros += "Erro na linha "+linha+" - Falta de dígito apos o '.'!\n";
                            i = (j-1);
                        } 
                    } else {
                        erros += "Erro na linha "+linha+" - Falta de dígito apos o '.'!\n";
                    }                                                                    
                } else {
                    tokens.addToken("Numero", numero, linha);  
                    i = (j-1);
                }
            } else if(atual == '\n'){
                // Incrementa o numero da linhas. 
                linha++;
            } else if(atual != ' ' && atual != '\t') {
                erros += "Erro na linha "+linha+" - Caractere inválido\n";
            }
            i++;        
        } while(i < texto.length());     
        return erros;
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
    
    public boolean letra(String caractere){
        return caractere.matches("[a-z|A-Z]"); 
    }
    
    public boolean espaco(String caractere) {
        return caractere.matches("[\t|\n|\r| ]"); 
    }
    
    public boolean digito(String caractere) {
        return caractere.matches("[0-9]"); 
    }
}
