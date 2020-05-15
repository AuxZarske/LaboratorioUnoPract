/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenodop1;



import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Arnold Zarske
 */
public class ClienteNodoP1 {

    public static void main(String[] args) throws IOException {
        
        //tomar los argumentos

       
        var nombre = args[0];
        var velocidad = Float.parseFloat(args[1]);
        var ip= args[2];
        var puerto = Integer.parseInt(args[3]);
        
       
        
        try {
            Socket soc = new Socket("localhost", puerto); //cambiar localhost por la variable ip
            OutputStream os = soc.getOutputStream();
            ObjectOutput o = new ObjectOutputStream(os);
            //enviar conectado con nombre
            
            o.writeObject(nombre);
            o.flush();
            
            
            try {    
                Generador generador = new Generador(velocidad, nombre, o);
                generador.iniciar();
            } catch (NoSuchAlgorithmException | InterruptedException ex) {
                System.err.print("Error!!");
            }finally {
                //enviar desconectado con nombre
                o.close();
                soc.close();
            }
            
            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        
        
        
    }
    
}
