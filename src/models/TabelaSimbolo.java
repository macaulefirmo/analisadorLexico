package models;

import java.util.ArrayList;

public class TabelaSimbolo {

    private ArrayList<Simbolo> simbolos;
    private int contador;

    public TabelaSimbolo() {
        this.contador = 1;
        this.simbolos = new ArrayList();
    }
    
    public boolean contem(String valor) {
        
        if(this.simbolos.isEmpty()) {
            return false;
        }
        Simbolo atual;
        for(int i=0; i<this.simbolos.size(); i++) {
             atual = simbolos.get(i);
             if(atual.getValor().equals(valor)) {
                 return true;
             }
        }
        return false;
    }
    
    public String getNomeSimbolo(String valor) {
        if(this.simbolos.isEmpty()) {
            return null;
        }
        Simbolo atual;
        for(int i=0; i<this.simbolos.size(); i++) {
             atual = simbolos.get(i);
             if(atual.getValor().equals(valor)) {
                 return atual.getNome();
             }
        }
        return null;
    }
    
    public String addSimbolo(String valor) {
        String nome = "id_"+this.contador;
        this.contador++;
        this.simbolos.add(new Simbolo(nome, valor));
        return nome;
    }
}
