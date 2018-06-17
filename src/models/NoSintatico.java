package models;

public class NoSintatico {

    private String simbolo;
    private String producao;

    public NoSintatico(String simbolo, String producao) {
        this.simbolo = simbolo;
        this.producao = producao;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getProducao() {
        return producao;
    }
}
