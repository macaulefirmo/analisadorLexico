package models;

import java.util.*;

public class SimboloSintatico {

    public static SimboloSintatico EMPTY_STRING = new SimboloSintatico("");
    public static SimboloSintatico INPUT_END = new SimboloSintatico("$");
    
    
    static class Set extends HashSet<SimboloSintatico> {

        public static Set unicoSimbolo(SimboloSintatico symbol) {
            Set set = new Set();
            set.add(symbol);
            return set;
        }
        
        public boolean soTerminais() {
            for (SimboloSintatico st : this) {
                if (!st.isTerminal()) return false;
            }
            return true;
        }
        
        public void removeEmptyString() {
            this.remove(EMPTY_STRING);
        }
        
        public boolean cEmptyString() {
            return this.contains(EMPTY_STRING);
        }

        
    }

    static class Array extends ArrayList<SimboloSintatico> {

        public Array() {
            super();
        }

        public Array(List<SimboloSintatico> listaSimbolo) {
            super (listaSimbolo);
        }

        public Array(String... arraySimboloNome) {
            super();

            for (String nomeSimbolo : arraySimboloNome) {
                this.add(new SimboloSintatico(nomeSimbolo));
            }
        }

        public boolean possuiUnicoSimbolo() {
            return this.size() == 1;
        }

        public SimboloSintatico getUnicoSimbolo() {
            assert possuiUnicoSimbolo();

            return this.get(0);
        }

        public static Array maisSimbolos(SimboloSintatico simbolo) {
            Array arraySimbolo = new Array();
            arraySimbolo.add(simbolo);
            return arraySimbolo;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (SimboloSintatico s : this) sb.append(s+" ");
            return sb.toString();
        }
    }

    private String name;

    public SimboloSintatico (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEmptyString() {
        return name.equals("");
    }

    public boolean isNTerminal() {
        return name.startsWith("<") && name.endsWith(">");
    }

    public boolean isTerminal() {
        return !isNTerminal();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SimboloSintatico symbol = (SimboloSintatico) obj;
        return Objects.equals(name, symbol.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
