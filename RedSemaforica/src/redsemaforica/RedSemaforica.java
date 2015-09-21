/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redsemaforica;

import static java.lang.Math.random;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
public class RedSemaforica implements Interface{

    /**
     * @param args the command line arguments
     */
    @Override
    public void  dayHello() {
        ExecutorService executorRedSemaforica = Executors.newFixedThreadPool(30);
        ArrayList semaforos = new ArrayList();

        int c1;
        int c2;
        int c3;
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            c1 = r.nextInt(10 - 1 + 1) + 1;
            c2 = r.nextInt(10 - 1 + 1) + 1;
            c3 = r.nextInt(10 - 1 + 1) + 1;
//            Runnable controlador = new Interseccion(Integer.toString(i), false, c2, c1);
//            executorRedSemaforica.execute (controlador);
            Interseccion s = new Interseccion(Integer.toString(i), false, c1, c2, c3);
            semaforos.add(s);
            executorRedSemaforica.execute((Runnable) semaforos.get(i));
        }
        executorRedSemaforica.shutdown();
        Interseccion aux;
        while (true) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            for (int j = 0; j < 10; j++) {
                aux = (Interseccion) semaforos.get(j);
//                System.out.println("Semaforo " + aux.getName() + "(" + aux.isB() + "):" + aux.getContador());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RedSemaforica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
         try {
            RedSemaforica obj = new RedSemaforica();
            Interface stub = (Interface) UnicastRemoteObject.exportObject(obj, 0);
            
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            registry.bind("Hello", stub);

            System.err.println("Server ready");
         } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
        //***********************************************************************************************
//        
//        ExecutorService executorRedSemaforica = Executors.newFixedThreadPool(30);
//        ArrayList semaforos = new ArrayList();
//
//        int c1;
//        int c2;
//        int c3;
//        Random r = new Random();
//        for (int i = 0; i < 10; i++) {
//            c1 = r.nextInt(10 - 1 + 1) + 1;
//            c2 = r.nextInt(10 - 1 + 1) + 1;
//            c3 = r.nextInt(10 - 1 + 1) + 1;
////            Runnable controlador = new Interseccion(Integer.toString(i), false, c2, c1);
////            executorRedSemaforica.execute (controlador);
//            Interseccion s = new Interseccion(Integer.toString(i), false, c1, c2, c3);
//            semaforos.add(s);
//            executorRedSemaforica.execute((Runnable) semaforos.get(i));
//        }
//        executorRedSemaforica.shutdown();
//        Interseccion aux;
//        while (true) {
//            System.out.println("");
//            System.out.println("");
//            System.out.println("");
//            for (int j = 0; j < 10; j++) {
//                aux = (Interseccion) semaforos.get(j);
//                System.out.println("Semaforo " + aux.getName() + "(" + aux.isB() + "):" + aux.getContador());
//
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(RedSemaforica.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//***********************************************************************************************
    }

}
