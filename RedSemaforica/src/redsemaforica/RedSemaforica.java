/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redsemaforica;

import static java.lang.Math.random;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class RedSemaforica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutorService executorRedSemaforica = Executors.newFixedThreadPool(30);
        // TODO code application logic here
        
        ExecutorService executorSimulador = Executors.newFixedThreadPool(10);
        
        
        int r_v;
        int r_r;
        Random r = new Random();
        for (int i=0; i<10; i++){
            r_v= r.nextInt(10-1+1)+1;
            r_r= r.nextInt(10-1+1)+1;
            Runnable controlador = new Semaforo(Integer.toString(i), false, r_r, r_v);
            executorRedSemaforica.execute (controlador);
        }
        executorRedSemaforica.shutdown();
        
        
        
    }

    
}
