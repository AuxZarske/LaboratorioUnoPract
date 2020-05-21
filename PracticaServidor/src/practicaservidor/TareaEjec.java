/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaservidor;


import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
/**
 *
 * @author Arnold Zarske
 */
public class TareaEjec implements Runnable{
    private final Socket soc;
    private ArrayList<Log> logs = new ArrayList<>();    
        public TareaEjec(Socket c) {
            this.soc = c;
        }

        @Override
        public void run() {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
            InputStream is = null;
            ObjectInput o = null;
            String nombreNodo = "";
            File file = new File("log.json");
            Writer filelogs = null;
            
            try {
                if(!file.exists()){
                    System.out.println("No hay archivo de logs");
                    filelogs = new FileWriter("log.json", false);
                }else{
                    System.out.println("Ya hay archivo de logs");
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("log.json"));
                    this.logs = gson.fromJson(bufferedReader, new TypeToken<ArrayList<Log>>() {}.getType());
                    filelogs = new FileWriter("log.json", false);
                }
                
                
                is = soc.getInputStream();              
                o = new ObjectInputStream(is);
                       
                nombreNodo = (String) o.readObject();
 
                System.out.println(System.currentTimeMillis()+" - "+ nombreNodo + " conectado" );
                          
                while(true){
                   
                    
                    long tiempo = (long) o.readObject(); 
                    
                    //Suponiendo que es UTF-8
                    String hash = (String) o.readObject();
                    final byte[] utf8hash = hash.getBytes("UTF-8");
                    long peso = Long.BYTES + utf8hash.length;
                    logs.add(new Log(nombreNodo, hash, System.currentTimeMillis()- tiempo, peso));
                    
                    
                    
                    
                    System.out.println(tiempo + " " + nombreNodo + " " + hash );
                    System.out.println("Tardo: "+ (System.currentTimeMillis()-tiempo));
                    
                  
                  
                }             
                
                
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                
                
            }finally{
                
                try{
                    gson.toJson(logs, filelogs);
                    filelogs.close();
                    o.close();
                    is.close();
                    soc.close(); 
                    
                    
            
                    System.out.println(System.currentTimeMillis()+" "+ nombreNodo + " desconectado" );
                    
                
                }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
           
            }
                 
        }   
    
}
