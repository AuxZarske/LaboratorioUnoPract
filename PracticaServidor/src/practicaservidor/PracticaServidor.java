/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaservidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Arnold Zarske
 */
public class PracticaServidor {

    public static void main(String args[]) {
     
        
        
        ServerSocket s = null;
        try {            
            // puerto
            int puerto = Integer.parseInt(args[0]);
            // defino socket
            s = new ServerSocket(puerto);
            while (true) {
                // espero y acepto una conex.
                Socket soc = s.accept();
                //creo tarea, pongo en hilo, inicio hilo
                Runnable tareaServer = new TareaEjec(soc);
                Thread hilo1 = new Thread(tareaServer);
                hilo1.start();

                
            }
        // manejo de errores
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
    } 
    
}
