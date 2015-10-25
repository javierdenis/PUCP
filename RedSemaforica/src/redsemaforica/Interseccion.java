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
    
    private int q_EO, q_OE, q_NS, q_SN;
    private int vm_EO, vm_OE, vm_NS, vm_SN;

    public Interseccion(String name, boolean estado, int c1, int c2, int c3) {
        this.name = name;
        this.b = estado;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        
        this.q_EO = this.q_OE = this.q_NS = this.q_SN =100;
        this.vm_EO= this.vm_OE= this.vm_NS= this.vm_SN=20;
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

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public int getQ_EO() {
        return q_EO;
    }

    public void setQ_EO(int q_EO) {
        this.q_EO = q_EO;
    }

    public int getQ_OE() {
        return q_OE;
    }

    public void setQ_OE(int q_OE) {
        this.q_OE = q_OE;
    }

    public int getQ_NS() {
        return q_NS;
    }

    public void setQ_NS(int q_NS) {
        this.q_NS = q_NS;
    }

    public int getQ_SN() {
        return q_SN;
    }

    public void setQ_SN(int q_SN) {
        this.q_SN = q_SN;
    }

    public int getVm_EO() {
        return vm_EO;
    }

    public void setVm_EO(int vm_EO) {
        this.vm_EO = vm_EO;
    }

    public int getVm_OE() {
        return vm_OE;
    }

    public void setVm_OE(int vm_OE) {
        this.vm_OE = vm_OE;
    }

    public int getVm_NS() {
        return vm_NS;
    }

    public void setVm_NS(int vm_NS) {
        this.vm_NS = vm_NS;
    }

    public int getVm_SN() {
        return vm_SN;
    }

    public void setVm_SN(int vm_SN) {
        this.vm_SN = vm_SN;
    }
    
    
}
