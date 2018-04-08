package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManipulaArquivo {
    
    public String lerArquivo(String nomeArquivo) {
        
        try {
            
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader br = new BufferedReader(fileReader);            
            
            String texto = ""; 
            String linha = br.readLine();           
            while(linha != null){                
                texto += linha+"\n"; 
                linha = br.readLine();
            }   
            
            br.close();            
            return texto;
        } catch(IOException e) {
            return e.getMessage();
        }        
    } 
    
    public boolean salvaArquivo(String texto, String nomeArquivo) {
        
        try{
            FileWriter fileWriter = new FileWriter(nomeArquivo);        
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write(texto);

            bw.close();
            return true;
        } catch(IOException e) {
            e.getMessage();
            return false;
        }
    }
}
