package models;

import java.util.ArrayList;

public class Regra {

    static class Array extends ArrayList<Regra> {

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (Regra r : this) {
                s.append(r+"\n");
            }
            return s.toString();
        }
    }

    private SimboloSintatico nTerminal;
    private SimboloSintatico.Array arraySimbolo;
    private boolean sincRegra;
    
    public SimboloSintatico getNTerminal() {
        return nTerminal;
    }

    public SimboloSintatico.Array getArraySimbolo() {
        return arraySimbolo;
    }
    
    public Regra(boolean sincRegra) {
        this.sincRegra = sincRegra;
    }
    public boolean isSincRegra() {
        return sincRegra;
    }

    public Regra(String nTerminal, SimboloSintatico.Array arraySimbolo) {
        this.nTerminal = new SimboloSintatico(nTerminal);
        
        this.arraySimbolo = arraySimbolo;
        sincRegra = false;
    }

    @Override
    public String toString() {
        if (isSincRegra()) return "Sinc";
        return String.format("%s ::= %s", nTerminal, arraySimbolo);
    }
}
