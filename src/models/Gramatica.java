package models;

import java.util.*;

public class Gramatica extends Hashtable<SimboloSintatico, Regra.Array> {
    private Map<SimboloSintatico, SimboloSintatico.Set> followMapa;
    private SimboloSintatico simbInicial;
    
    public Gramatica() {
        super();
        followMapa = new Hashtable<>();
        simbInicial = null;
    }

    public void setInicialSimbolo(SimboloSintatico simbInicial) {
        this.simbInicial = simbInicial;
    }

    public SimboloSintatico getSimbInicial() {
        return this.simbInicial;
    }

    public Gramatica addRule(Regra r) {
        this.putIfAbsent(r.getNTerminal(), new Regra.Array());
        this.get(r.getNTerminal()).add(r);
        return this;
    }
    
        public void exibirRegra() {
        for (Map.Entry<SimboloSintatico, Regra.Array> entrada : this.entrySet()) {
            Regra.Array regra = entrada.getValue();
            System.out.println(regra);
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public SimboloSintatico.Set funcFirstUnicoSimbolo(SimboloSintatico s) {
        return funcFirstArraySimb(SimboloSintatico.Array.maisSimbolos(s));
    }

    /**
     *
     * @param arrayS
     * @return
     */
    public SimboloSintatico.Set funcFirstArraySimb(SimboloSintatico.Array arrayS) {
        SimboloSintatico.Set aux = new SimboloSintatico.Set();

        if (arrayS.possuiUnicoSimbolo()) {
            SimboloSintatico s = arrayS.getUnicoSimbolo();
            if (s.isTerminal()) return SimboloSintatico.Set.unicoSimbolo(s);
            else {
                for (Regra r : this.get(s)) {
                    aux.addAll(funcFirstArraySimb(r.getArraySimbolo()));
                }
            }
        } else {
            for (int i = 0; i < arrayS.size(); i++) {
                SimboloSintatico s = arrayS.get(i);

                SimboloSintatico.Set atual = funcFirstUnicoSimbolo(s);

                if (atual.cEmptyString()) {
                    if (i < arrayS.size() - 1) {
                        atual.removeEmptyString();
                    }
                    aux.addAll(atual);
                } else {
                    aux.addAll(atual);
                    break;
                }
            }
        }

        return aux;
    }
    
    public void exibirFirst() {
        for (Map.Entry<SimboloSintatico, Regra.Array> entrada : this.entrySet()) {
            SimboloSintatico st = entrada.getKey();
            System.out.println(st+" -> "+funcFirstUnicoSimbolo(st));
        }
    }
    
    
    public SimboloSintatico.Set funcFollow(SimboloSintatico sf) {
        if (!followMapa.containsKey(sf)) {
            followMapa = gerarFollowMapa();
        }
        return followMapa.get(sf);
    }

    public Map<SimboloSintatico, SimboloSintatico.Set> gerarFollowMapa() {

        Map<SimboloSintatico, SimboloSintatico.Set> mapa = new Hashtable<>();

        this.forEach((simbAtual, regraAtual) -> {
            SimboloSintatico.Set set = new SimboloSintatico.Set();
            if (simbAtual.equals(simbInicial)) set.add(SimboloSintatico.INPUT_END);
            mapa.putIfAbsent(simbAtual, set);
        });

        boolean bol;

        do {
            bol = false;
            for (Map.Entry<SimboloSintatico, Regra.Array> entrada : this.entrySet()) {
                SimboloSintatico aux = entrada.getKey();

                int tam = mapa.get(aux).size();

                for (Map.Entry<SimboloSintatico, Regra.Array> tempEntrada : this.entrySet()) {
                    Regra.Array ruleArr = tempEntrada.getValue();

                    for (Regra r : ruleArr) {

                        for (int i = 0; i < r.getArraySimbolo().size(); i++) {
                            SimboloSintatico sy = r.getArraySimbolo().get(i);

                            if (sy.equals(aux)) {

                                if (i == r.getArraySimbolo().size() - 1) {
                                    mapa.get(aux).addAll(mapa.get(tempEntrada.getKey()));
                                } else {
                                    SimboloSintatico.Array nextSymbols = new SimboloSintatico.Array(r.getArraySimbolo().subList(i + 1, r.getArraySimbolo().size()));

                                    mapa.get(aux).addAll(funcFirstArraySimb(nextSymbols));

                                    if (funcFirstArraySimb(nextSymbols).cEmptyString())
                                        mapa.get(aux).addAll(mapa.get(tempEntrada.getKey()));
                                }
                            }

                        }
                    }
                }
                mapa.get(aux).removeEmptyString();
                if (mapa.get(aux).size() > tam) bol = true;
            }
        } while(bol);


        return mapa;
    }

    public void exibirFollow() {
        for (Map.Entry<SimboloSintatico, Regra.Array> entrada : this.entrySet()) {
            SimboloSintatico st = entrada.getKey();
            System.out.println(st+" -> "+funcFollow(st));
        }
    }

}
