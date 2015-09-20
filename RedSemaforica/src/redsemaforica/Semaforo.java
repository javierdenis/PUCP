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
    
    private int contador;

    public Semaforo(String name, boolean estado, int t_rojo, int t_verde) {
        this.name = name;
        this.estado = estado;
        this.t_rojo = t_rojo;
        this.t_verde = t_verde;
    }

    @Override
    public void run() {

        while (true) {

            contador = estado ? t_verde : t_rojo;
            while (contador >= 0) {
//                System.out.println(name + "(" + estado + "):" + contador--);
                contador--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            estado=!estado;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getT_rojo() {
        return t_rojo;
    }

    public void setT_rojo(int t_rojo) {
        this.t_rojo = t_rojo;
    }

    public int getT_verde() {
        return t_verde;
    }

    public void setT_verde(int t_verde) {
        this.t_verde = t_verde;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    
    

}
