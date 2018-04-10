package view;

import persistencia.ManipulaArquivo;
import controllers.ControllerAnalisadorLexico;

public class Main {
    
    public static void main(String args[]) {
        
        ManipulaArquivo ma = new ManipulaArquivo();
        String texto = ma.lerArquivo("arquivo2.txt");
        //System.out.println("Texto a ser lido: "+texto);
        
        ControllerAnalisadorLexico ctr = new ControllerAnalisadorLexico();
        ctr.lerCaracteres(texto);
        
        
        //String texto2 = texto + "Texto alterado!";
        /*if(ma.salvaArquivo(texto, "arquivo.txt")){
            System.out.println("Texto salvo com sucesso!");
        }*/
    }
}
