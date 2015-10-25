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
    static ArrayList<Individuo> p = new ArrayList<Individuo>();
    static ArrayList<Individuo> np = new ArrayList<Individuo>();
    static ArrayList<Individuo> ps = new ArrayList<Individuo>();
    static Individuo hijo = new Individuo();
    static Individuo hija = new Individuo();

    
    public static class Individuo{
        int IR; //Indice de Rendimiento
        ArrayList <Interseccion> intersecciones = new ArrayList<Interseccion>();
    
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
            SeleccionMejoresIndividuoiduos();
//            if (PoblacionConverge()){
//            }
        }
    }
    public static void SeleccionMejoresIndividuoiduos(){
        ps=new ArrayList<Individuo>();
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
    public static void Casamiento(Individuo padre, Individuo madre){
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
        Individuo ind ;
        for (int i=0; i<tamPoblacion; i++){
            ind = new Individuo();
            p.add(ind);
        }
    }
    public static double CalcularIndiceRendimientoxIndividuo(Individuo ind){
        double suma=0;
        double valor=0;
        //Evaluamos para cada interseccion INTERCALADA los datos que se obtienen alli
        for (int i = 0; i < numIntersecciones; i++) {
            valor+=EvaluarInterseccion(ind.intersecciones.get(i));
            System.out.println("Resultado FO = " + valor);
            suma+=valor;
        }
        return suma;
    }
    public static Double  EvaluarInterseccion(Interseccion intersecccion){
        double resultado=0;
        
        resultado+=FObjetivo2(intersecccion, 200, 120,90);
        
        return resultado;
    }
    
//    public static double FObjetivo(
//            int q, /*flujo del arco*/
//            int verde_efectivo, 
//            int Ciclo_red /*dato fijo*/, 
//            int S/*flujo saturacion, dato fijo*/,
//            int tao,/*periodo de análisis 10-15-30 minutos*/ 
//            int ciclo_semaforo) 
//    {
//        double DU = q/ciclo_semaforo;//promedio de longitudes de cola en todos los intervalos;
//        int Q = verde_efectivo / Ciclo_red * S;
//        int x = q / Q;
//        double x0 = 0.67 + (S * verde_efectivo) / 600;
//        double N = Q * tao / 4;
//
//        if (x > x0) {
//            N = N * ((x - 1) + Math.sqrt(Math.pow(x - 1, 2) + (12 * (x - x0)) / (Q * tao)));
//            return N;
//        } else {
//            return -1;
//        }
//    }
    
    
    public static double FObjetivo2(
            Interseccion i,
            int Ciclo_red /*dato fijo*/, 
            int S/*flujo saturacion, dato fijo*/,
            int tao/*periodo de análisis 10-15-30 minutos*/ 
            ) 
    {
        
        //ESTO SOLO SIRVE PARA EVALUAR UNA AVENIDA QUE ENTRA A LA INTERSECCION
        //FALTA EVALUAR EL OTRO SENTIDO;
        int q=i.getQ_EO();
        int ciclo_semaforo=i.getC1()+i.getC2()+i.getC3();
        int verde_efectivo;
        verde_efectivo=i.isB()?i.getC2():i.getC1()+i.getC3();
        
        double DU = q/ciclo_semaforo;//promedio de longitudes de cola en todos los intervalos;
        int Q = verde_efectivo / Ciclo_red * S;
        int x = q / Q;
        double x0 = 0.67 + (S * verde_efectivo) / 600;
        double N = Q * tao / 4;

        if (x > x0) {
            N = N * ((x - 1) + Math.sqrt(Math.pow(x - 1, 2) + (12 * (x - x0)) / (Q * tao)));
            return N;
        } else {
            return -1;
        }
    }
}
