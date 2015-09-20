/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redsemaforica;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class Semaforo implements Runnable {

    private String name;
    private boolean estado; //verde = 1; R  rojo =0;
    private int t_rojo;
    private int t_verde;

    public Semaforo(String name, boolean estado, int t_rojo, int t_verde) {
        this.name = name;
        this.estado = estado;
        this.t_rojo = t_rojo;
        this.t_verde = t_verde;
    }

    @Override
    public void run() {

        int counter;
        while (true) {

            counter = estado ? t_verde : t_rojo;
            while (counter >= 0) {
                System.out.println(name + "(" + estado + "):" + counter--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            estado=!estado;
        }

    }

}
