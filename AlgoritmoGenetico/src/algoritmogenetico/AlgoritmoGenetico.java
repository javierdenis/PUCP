/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class AlgoritmoGenetico {
    static Integer numIntersecciones = 10;
    static Integer tamPoblacion = 100; //que sea par->el porque esta en aqui1
    static ArrayList<Indiv> p = new ArrayList<Indiv>();
    static ArrayList<Indiv> np = new ArrayList<Indiv>();
    static ArrayList<Indiv> ps = new ArrayList<Indiv>();
    static Indiv hijo = new Indiv();
    static Indiv hija = new Indiv();

    
    public static class Indiv{
        int IR;
        ArrayList <Interseccion> intersecciones = new ArrayList<Interseccion>();
    
    }
    public static class Interseccion{
    
    }
    

    public static void main(String[] args) {
        
        /*p=*/GenerarPoblacionInicial();
        for (int j=0; j<tamPoblacion;j++){
            CalcularIndiceRendimientoxIndividuo(p.get(j)/*p*/);
        }
        
        while(EvaluarCondicionParada()){
            for (int i=0;i<tamPoblacion;i++){ //aqui1
                Casamiento(p.get(i), p.get(tamPoblacion-i));
                //Casamiento(p[i],p[tamPoblacion-i]);
                MutacionDeHijos(0.3);
                CalcularIndiceRendimientoxIndividuo(hijo);
                CalcularIndiceRendimientoxIndividuo(hija);
                np.add(hijo);
                np.add(hija);
            }
            SeleccionMejoresIndividuos();
//            if (PoblacionConverge()){
//            }
        }
    }
    public static void SeleccionMejoresIndividuos(){
        ps=new ArrayList<Indiv>();
        for (int i=0; i<tamPoblacion;i++){
            if(p.get(i).IR<=np.get(i).IR){
                ps.add(p.get(i));
            } else {
                ps.add(np.get(i));
            }
        }
    }
    public static void MutacionDeHijos(double probabilidad){
        
    }
    public static void Casamiento(Indiv padre, Indiv madre){
        /*ind_hijo=*/ObtenerIndividuo();
        /*ind_hija=*/ObtenerIndividuo();
        //continuar con el casamiento;
    }
    public static void ObtenerIndividuo(){
        
    }
    public static boolean EvaluarCondicionParada(){
        if (PoblacionConverge()&& true){
            return true;
        }else{
            return false;
        }
    }
    public static boolean PoblacionConverge(){
        return true;
    }
    public static void GenerarPoblacionInicial(){
        Indiv ind ;
        for (int i=0; i<tamPoblacion; i++){
            ind = new Indiv();
            p.add(ind);
        }
    }
    public static double CalcularIndiceRendimientoxIndividuo(Indiv ind){
        double suma=0;
        double valor=0;
        //Evaluamos para cada interseccion los datos que se obtienen alli
        for (int i = 1; i <= numIntersecciones; i++) {
            valor+=EvaluarInterseccion(ind.intersecciones.get(i));
            System.out.println("Resultado FO = " + valor);
            suma+=valor;
        }
        return suma;
    }
    public static Double  EvaluarInterseccion(Interseccion intersecccion){
        return FObjetivo(900, 10, 10, 200, 120,90);
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
