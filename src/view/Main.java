package view;

import controllers.ControllerAnalisadorLexico;
import controllers.ControllerAnalisadorSintatico;
import models.TabelaSimbolo;
import models.Token;
import persistencia.ManipulaArquivo;

public class Main {
    
    public static void main(String args[]) {
                
        System.out.println("Compilador Maroto Iniciado ... XD \n");
        
        ControllerAnalisadorLexico lexico = new ControllerAnalisadorLexico();
        ControllerAnalisadorSintatico sintatico = new ControllerAnalisadorSintatico();
        
        ManipulaArquivo ma = new ManipulaArquivo();
        
        String gramatica = ma.carregarGramatica();
        gramatica = gramatica.replaceAll(" ","");   
        //System.out.println(gramatica+"\n");
        if(!sintatico.gerarTabela(gramatica)) {
            System.err.println("Erro ao gerar Tabela!");
        } else {
            sintatico.printTable();
            System.out.println("");
        }
        

        // Obtem o nome do(s) arquivo(s).
        String[] arquivos = ma.getArquivos();
        
        // Obtem os arquivos da pasta Arquivo e compila um por vez.
        for(int i=0; i<arquivos.length; i++) {
            
            // Obtem o conteudo do arquivo.
            String texto = ma.lerArquivo(arquivos[i]);
            // Divide o nome do diretorio pela \ (Arquivos\nomeDoArquivo.txt)
            String[] arrayS = arquivos[i].split("\\\\");
            
            System.out.println("Compilando arquivo \""+arrayS[1]+"\" ...");
            
            // Se texto for diferente de vazio, inicia a compilacao do arquivo.
            if(!texto.isEmpty()) {
                
                Token tokens = new Token();
                TabelaSimbolo simbolos = new TabelaSimbolo();
                
                // Percorre os caracteres do texto e retorna os erros encontrados.
                String errosLexicos = lexico.analisar(texto, tokens, simbolos); 
                
                String errosSintaticos = sintatico.analisar(tokens);

                // Salva o resultado da compilacao em um Arquivo de mesmo nome e prefixo compilado_ na pasta Arquivos/Compilados.
                ma.salvaArquivo(errosLexicos, errosSintaticos, "Arquivos/Compilados/compilado_"+arrayS[1]);
                System.out.println("Arquivo compilado com sucesso!\n");
            }            
        }     
    }
}
