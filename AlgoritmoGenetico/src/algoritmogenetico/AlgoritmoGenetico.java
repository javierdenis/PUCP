/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author javier
 */
public class AlgoritmoGenetico {
    static Integer numIntersecciones = 5;
    static Integer tamPoblacion = 5; //que sea par->el porque esta en aqui1
    static ArrayList<Individuo> p = new ArrayList<Individuo>();
    static ArrayList<Individuo> np = new ArrayList<Individuo>();
    static ArrayList<Individuo> ps = new ArrayList<Individuo>();
    static Individuo hijo = new Individuo();
    static Individuo hija = new Individuo();

    

    
    public static class Individuo{
        double IR; //Indice de Rendimiento
        ArrayList <Interseccion> intersecciones = new ArrayList<Interseccion>();
        
//        public Individuo (ArrayList intersecciones){
//        }
        public Individuo (){ //revisar si es posible crear un individuo nuevo sin interseccion
            
        }
    }
  
    

    public static void main(String[] args) {
        
        /*p=*/GenerarPoblacionInicial();
        /*ImprimirPoblacion(p);*/
        
        for (int j=0; j<tamPoblacion;j++){
            CalcularIndiceRendimientoxIndividuo(p.get(j)/*p*/); //Este calculo se esta haciendo de la poblacion inicial
            System.out.println("Individuo "+j+" con IR: "+p.get(j).IR);
        }
        while(EvaluarCondicionParada()){
            for (int i=0;i<tamPoblacion;i++){ //aqui1
                System.out.println("***********************************"+i);
                hijo=new Individuo();
                hija=new Individuo();
                if (i<tamPoblacion/2)Casamiento(p.get(i), p.get(tamPoblacion-i-1));//Aqui el error esta en las parejitas
                System.out.println("");
                System.out.println("");
                System.out.println("");
                
                //Casamiento(p[i],p[tamPoblacion-i]);
                /*
                MutacionDeHijos(0.3);
                CalcularIndiceRendimientoxIndividuo(hijo);
                CalcularIndiceRendimientoxIndividuo(hija);
                np.add(hijo);
                np.add(hija);*/
                
            }
            /*SeleccionMejoresIndividuoiduos();*/
//            if (PoblacionConverge()){
//            }
            break;
        }
    }
    
    public static void ImprimirIndividuo(Individuo x){
        
        for (int i=0; i<x.intersecciones.size();i++){
            Interseccion aux = x.intersecciones.get(i);
            //System.out.println("["+i+":"+"<"+aux.isB()+","+aux.getC1()+","+aux.getC2()+","+aux.getC3()+">] ");
            //System.out.println("["+i+":"+"<"+aux.isB()+","+aux.getQ_EO()+","+aux.getQ_NS()+","+aux.getQ_OE()+","+aux.getQ_SN()+">] ");
            System.out.println("["+i+":"+"<"+aux.getName()+">] ");
        }
    }
    
    public static void ImprimirPoblacion(ArrayList<Individuo> poblacion){
        System.out.println("POBLACION ================================");
        for (int i=0 ; i<poblacion.size(); i++){
            Individuo aux = poblacion.get(i);
            System.out.print("IR : ("+aux.IR+") => ");
            for (int j=0; j<aux.intersecciones.size(); j++){
                System.out.println("Interseccion "+j+" with name = "+aux.intersecciones.get(j).getName());
            }
        }
        System.out.println("==========================================");
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
        /**********************************/
        for (int i=0; i<padre.intersecciones.size(); i++){
            padre.intersecciones.get(i).setName("P"+i);
            madre.intersecciones.get(i).setName("M"+i);
        }
        /**********************************/    
        Random r = new Random(); 
        int max=padre.intersecciones.size()-1;
        int min=0;
        int PuntoDeCorte= r.nextInt((max - min) + 1) + min;
        System.out.println("Punto de corte = "+PuntoDeCorte);
        for (int i=0; i<PuntoDeCorte; i++){
            hijo.intersecciones.add(padre.intersecciones.get(i));
            hija.intersecciones.add(madre.intersecciones.get(i));
        }
        for (int i=PuntoDeCorte; i<padre.intersecciones.size(); i++){
            hijo.intersecciones.add(madre.intersecciones.get(i));
            hija.intersecciones.add(padre.intersecciones.get(i));
        }
        System.out.println("PADRE ====");   ImprimirIndividuo(padre);
        System.out.println("MADRE ====");   ImprimirIndividuo(madre);
        System.out.println("HIJO  ====");   ImprimirIndividuo(hijo);
        System.out.println("HIJA  ====");   ImprimirIndividuo(hija);
        //*ind_hija=*/ObtenerIndividuo();
        //continuar con el casamiento;
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
        Interseccion aux;
        for (int i=0; i<tamPoblacion; i++){
            //RMI: Obtener datos de volumenes en cada interseccion
            ind = new Individuo();
            ind.intersecciones = GenerarArregloDeInterseccionesFalsa();
            double calculo=CalcularIndiceRendimientoxIndividuo(ind);
            ind.IR=calculo;
            p.add(ind);
        }
    }
    private static ArrayList<Interseccion> GenerarArregloDeInterseccionesFalsa() {
        ArrayList <Interseccion> rpta = new ArrayList<>();
        for (int i=0; i<numIntersecciones; i++){
            Interseccion x= new Interseccion("Interseccion "+i, true, 100, 100, 100);
            x.setQ_EO(5);
            x.setQ_NS(7);
            x.setQ_OE(11);
            x.setQ_SN(14);
            
            x.setVm_EO(6);
            x.setVm_NS(8);
            x.setVm_OE(12);
            x.setVm_SN(15);
            rpta.add(x);
        }
        return rpta;
    }
    public static double CalcularIndiceRendimientoxIndividuo(Individuo ind){
        double suma=0;
        double valor=0;
        //Evaluamos para cada interseccion INTERCALADA los datos que se obtienen alli
        for (int i = 0; i < numIntersecciones; i=i+2) {
            valor+=EvaluarInterseccion(ind.intersecciones.get(i));
            suma+=valor;
        }
        return suma;
    }
    public static Double  EvaluarInterseccion(Interseccion intersecccion){
        double resultado=0;
        
        resultado+=FObjetivo2(intersecccion, 60, 20,90);
        
        return resultado;
    }
    /*
    public static double FObjetivo(
            int q, //flujo del arco
            int verde_efectivo, 
            int Ciclo_red, //dato fijo
            int S, //flujo saturacion, dato fijo
            int tao,//periodo de análisis 10-15-30 minutos
            int ciclo_semaforo) 
    {
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
    
    */
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
        double Q = verde_efectivo / (Ciclo_red * S);
        double x = q / Q;
        double x0 = 0.67 + (S * verde_efectivo) / 600;
        double N = Q * tao / 4;

        if (x > x0) {
            N = N * ((x - 1) + Math.sqrt(Math.pow(x - 1, 2) + (12 * (x - x0)) / (Q * tao)));
            return 100;
            //return N;
        } else {
            return -1;
        }
    }
}
