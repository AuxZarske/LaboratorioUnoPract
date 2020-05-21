/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaservidor;

/**
 *
 * @author root
 */
public class Log {
    private String node;
    private String hash;
    private long time;
    private long bandwidth;
    

    public Log(String node, String hash, long time, long bandwidth) {
        this.node = node;
        this.hash = hash;
        this.time = time;
        this.bandwidth = bandwidth;
    }
    
    
}
