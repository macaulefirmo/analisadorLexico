package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import models.TabelaSintatica;
import models.Token;

public class ControllerAnalisadorSintatico {
    
    private HashMap<String, String> mapa_gramatica;
    private TabelaSintatica tabela_sintatica;
    private HashMap<String, ArrayList<String>> conjuntoPrimeiro;
    
    public ControllerAnalisadorSintatico() {
        this.mapa_gramatica = new HashMap();
        this.tabela_sintatica = new TabelaSintatica();
    }
        
    public boolean gerarTabela(String gramatica) {
        if(!this.gerarMapa(gramatica)) {
            return false;
        }
        //System.out.println(key + " to " + value)
        this.mapa_gramatica.forEach(
            (key, value) -> this.formarConjuntoPrimeiro(key, value)
        );
        
        return true;
    }
    
    public ArrayList<String> obterPrimeiro(String value) {       
        ArrayList<String> primeiroDe = new ArrayList();
        String[] producao = value.split("§");
        for(int i=0; i<producao.length; i++) {
            
            char atual = producao[i].charAt(0);            
            if(atual == '<') {
                // Nao terminal
                System.out.println("Não Terminal: "+producao[i]);
                primeiroDe.add(this.primeiroDoMapa(producao[i]));
            } else {
                // Terminal
                int j = 1;
                char atual2 = producao[i].charAt(j);
                String terminal = "";
                while(atual2 != '\'') {
                    terminal += atual2;
                    j++;
                    atual2 = producao[i].charAt(j);
                }
                primeiroDe.add(terminal);
            }
        }
        return primeiroDe;
    }
    
    public String primeiroDoMapa(String value) {
        
        
        
        
        String producao = this.mapa_gramatica.get(value);
        String armazena = "";
        if(producao.contains("§")) {
            String[] values = producao.split("§");
            for(int i=0; i<values.length; i++) {                
                char atual = values[i].charAt(0);            
                if(atual == '<') {
                    // Nao terminal
                    armazena += this.primeiroDoMapa(values[i]);
                    armazena += "~";
                } else {
                    // Terminal
                    int j = 1;
                    char atual2 = values[i].charAt(j);
                    String terminal = "";
                    while(atual2 != '\'') {
                        terminal += atual2;
                        j++;
                        atual2 = values[i].charAt(j);
                    }
                    armazena += terminal+"~";
                }
            }
        }        
        return armazena;
    }
    
    public void formarConjuntoPrimeiro(String key, String value) {
        
        ArrayList<String> val = this.obterPrimeiro(value);  
        this.conjuntoPrimeiro.put(key, val);
    }
    
    private boolean gerarMapa(String gramatica) {
        int i = 0;        
        char atual;                
        do {
            // Obtem o caractere da posicao atual.
            atual = gramatica.charAt(i);
            String linha = "";
            while(atual != '\n' && i < gramatica.length()) {
                if(atual == '|') {                    
                    char atual2 = gramatica.charAt(i+1);
                    if(atual2 == '|'){
                        linha += "||";
                        i++;
                    } else {
                        atual = '§';
                    }       
                } else {
                    linha += atual;                    
                }
                i++;
                if(i >= gramatica.length()) {
                    return false;
                }
                atual = gramatica.charAt(i);
            }
            String[] aux = linha.split("::=");
            this.mapa_gramatica.put(aux[0], aux[1]);
            i++;
        } while(i < gramatica.length()); 
        return true;
    }
    
    /**
     * Valida a sequencia de tokens de acordo com a gramatica definida.
     * @param tokens
     * @return 
     */
    public String analisar(Token tokens) {
        
        return "";
    }
    
    public void printTable() {
        
        //this.conjuntoPrimeiro.forEach((key, value) -> System.out.println(key + " to " + value));
        //this.mapa_gramatica.forEach((key, value) -> System.out.println(key + " to " + value));

    }
    
    
}
