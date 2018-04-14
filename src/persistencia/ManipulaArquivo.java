package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManipulaArquivo {
    
    public String[] getArquivos() {
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".txt");
            }
        };
        File dir = new File("Arquivos");
        File[] files = dir.listFiles(filter); 
        String[] arquivos = new String[files.length];
        for(int i=0; i<files.length; i++) {
            
            arquivos[i] = files[i].toString();
        }
        return arquivos;
    }
    
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
    
    public boolean salvaArquivo(String tokens, String texto, String nomeArquivo) {
        
        try{
            File file = new File(nomeArquivo);       
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);        
            BufferedWriter bw = new BufferedWriter(fileWriter);
            
            bw.write("--- TOKENS ---");
            bw.newLine ();
            for(int i=0; i<tokens.length(); i++){
                if(tokens.charAt(i) == '\n'){
                    bw.newLine ();
                } else {
                    bw.write(tokens.charAt(i));
                }                
            }
            bw.write("--- ERROS ---");
            bw.newLine ();
            for(int i=0; i<texto.length(); i++){
                if(texto.charAt(i) == '\n'){
                    bw.newLine ();
                } else {
                    bw.write(texto.charAt(i));
                }                
            }
            
            bw.close();
            return true;
        } catch(IOException e) {
            e.getMessage();
            return false;
        }
    }
}
