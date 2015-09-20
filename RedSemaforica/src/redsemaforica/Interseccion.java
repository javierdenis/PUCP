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
public class Interseccion implements Runnable {
    
    //La duracion del primer color sera = c1+c3;
    //La duracion del segundo color sera = c2;
    //La duracion total del ciclo sera = c1+c2+c3+2*IGP(3segundos)

    private String name;
    private boolean b; // PRIMER COLOR: La interseccion inicia en verde = 1 orojo =0 para la vía principal
    private int c1;
    private int c2;
    private int c3;
    
    private int contador;

    public Interseccion(String name, boolean estado, int c1, int c2, int c3) {
        this.name = name;
        this.b = estado;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    @Override
    public void run() {

        while (true) {

            contador = b ? c2 : c1;
            while (contador >= 0) {
                contador--;
                try {
                    Thread.sleep(1000); //duerme un segundo
                } catch (InterruptedException ex) {
                    Logger.getLogger(Interseccion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            b=!b;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    
    

}
