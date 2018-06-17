package models;

import java.util.HashMap;

public class TabelaSintatica {
 
    private HashMap<String, HashMap<String, String>> tabela_sintatica;

    public TabelaSintatica() {
        this.tabela_sintatica = new HashMap();
    }

    public HashMap<String, HashMap<String, String>> getTabela_sintatica() {
        return tabela_sintatica;
    }
    
    public void addTabela(String naoTerminal, String simbolo, String producao) {
        
        if(this.tabela_sintatica.containsKey(naoTerminal)) {
            HashMap<String, String> map2 = this.tabela_sintatica.get(naoTerminal);            
            if(!map2.containsKey(simbolo)){
                map2.put(simbolo, producao);
            }
        } else {
            HashMap<String, String> map = new HashMap();
            map.put(simbolo, producao);
            this.tabela_sintatica.put(naoTerminal, map);
        }
        
        
    }
}
