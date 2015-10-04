/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

/**
 *
 * @author javier
 */
public class AlgoritmoGenetico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println("Solution = " + FObjetivo(900, 10, 10, 200, 120,90));
        }

    }
    
    
    

    public static double FObjetivo(int q, int verde, int Ciclo_red, int S, int tao, int ciclo_semaforo) {
        double DU = q/ciclo_semaforo;//promedio de longitudes de cola en todos los intervalos;
        int Q = verde / Ciclo_red * S;
        int x = q / Q;
        double x0 = 0.67 + (S * verde) / 600;
        double N = Q * tao / 4;

        if (x > x0) {
            N = N * ((x - 1) + Math.sqrt(Math.pow(x - 1, 2) + (12 * (x - x0)) / (Q * tao)));
            return N;
        } else {
            return -1;
        }
    }

}
