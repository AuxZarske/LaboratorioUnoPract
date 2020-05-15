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
/**
 *
 * @author Arnold Zarske
 */
public class TareaEjec implements Runnable{
    private final Socket soc;
        
        public TareaEjec(Socket c) {
            this.soc = c;
        }

        @Override
        public void run() {
         
            InputStream is = null;
            ObjectInput o = null;
            String nombreNodo = "";
            try {
                is = soc.getInputStream();              
                o = new ObjectInputStream(is);
                       
                nombreNodo = (String) o.readObject();
 
                System.out.println(System.currentTimeMillis()+" - "+ nombreNodo + " conectado" );
                          
                while(true){
                   
                    
                    long tiempo = (long) o.readObject(); 
                 
                    String hash = (String) o.readObject();            

                    System.out.println(tiempo + " " + nombreNodo + " " + hash );
                    
                  
                  
                }
                
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }finally{
                try{
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
