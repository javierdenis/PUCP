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

    static Integer numIntersecciones = 10;
    static Integer tamPoblacion = 50; //que sea par->el porque esta en aqui1
    static ArrayList<Individuo> p = new ArrayList<Individuo>();
    static ArrayList<Individuo> np = new ArrayList<Individuo>();
    static ArrayList<Individuo> ps = new ArrayList<Individuo>();
    static Individuo hijo = new Individuo();
    static Individuo hija = new Individuo();

    public static class Individuo {

        double IR; //Indice de Rendimiento
        ArrayList<Interseccion> intersecciones = new ArrayList<Interseccion>();

//        public Individuo (ArrayList intersecciones){
//        }
        public Individuo() { //revisar si es posible crear un individuo nuevo sin interseccion

        }
    }

    public static void main(String[] args) {

        GenerarPoblacionInicial();
        while (EvaluarCondicionParada()) {
            Casamiento(); //FUNCIONA CORRECTAMENTE
            MutacionDeHijos(0.5); //FALTA DEFINIR QUÉ VA A MUTAR!
            CalcularIRNuevaPoblacion();
            SeleccionMejoresIndividuos();
            break;
        }
        /*SeleccionMejoresIndividuoiduos();*/
//            if (PoblacionConverge()){
//            }
//            break;
//        }
    }

    public static void CalcularIRNuevaPoblacion() {
        double MINIMO = 100000;
        Individuo optimo = new Individuo();
        for (int j = 0; j < tamPoblacion; j++) {
            np.get(j).IR = CalcularIndiceRendimientoxIndividuo(np.get(j)/*p*/); //Este calculo se esta haciendo de la poblacion inicial
            System.out.println("Nuevo individuo " + j + " con IR: " + np.get(j).IR);
            if (np.get(j).IR > 0 && np.get(j).IR <= MINIMO) {
                MINIMO = np.get(j).IR;
                optimo = np.get(j);
            }
        }
        System.out.println("Minimo de minimos = " + MINIMO);
        ImprimirIndividuo(optimo);
    }

    public static void ImprimirIndividuo(Individuo x) {

        for (int i = 0; i < x.intersecciones.size(); i++) {
            Interseccion aux = x.intersecciones.get(i);
            //System.out.println("["+i+":"+"<"+aux.isB()+","+aux.getC1()+","+aux.getC2()+","+aux.getC3()+">] ");
            //System.out.println("["+i+":"+"<"+aux.isB()+","+aux.getQ_EO()+","+aux.getQ_NS()+","+aux.getQ_OE()+","+aux.getQ_SN()+">] ");
            System.out.println("[" + i + ":" + "<" + aux.getName() + "," + aux.getC2() + ">] ");
        }
    }

    public static void ImprimirPoblacion(ArrayList<Individuo> poblacion) {
        System.out.println("POBLACION ================================");
        for (int i = 0; i < poblacion.size(); i++) {
            Individuo aux = poblacion.get(i);
            System.out.print("IR : (" + aux.IR + ") => ");
            for (int j = 0; j < aux.intersecciones.size(); j++) {
                System.out.println("Interseccion " + j + " with name = " + aux.intersecciones.get(j).getName());
            }
        }
        System.out.println("==========================================");
    }

    public static void SeleccionMejoresIndividuos() {
        for (int i = 0; i < tamPoblacion; i++) {
            if (p.get(i).IR < 0) {
                if (np.get(i).IR > 0) {
                    ps.add(np.get(i));
                } else {
                    ps.add(np.get(i)); // cualquiera en realidad
                }
            } else {
                if (np.get(i).IR > 0) {
                    if (p.get(i).IR <= np.get(i).IR) {
                        ps.add(p.get(i));
                    } else {
                        ps.add(np.get(i));
                    }
                } else{
                    ps.add(p.get(i));
                }
            }
        }
        p = new ArrayList<>();
        p=ps;
    }

    public static void MutacionDeHijos(double probabilidad) {
        Random r = new Random();
        double sorteo = r.nextDouble();
        System.out.println("probabilidad! : " + sorteo);
        if (sorteo < probabilidad) {
            System.out.println("HAY MUTACION!!!!!!!!!!!!!!!!!!");
            int max = np.size() - 1;
            int min = 0;
            int individuoAMutar = r.nextInt((max - min) + 1) + min;
            System.out.println("Individuo a mutar =" + individuoAMutar);
            MutarIndividuo(np.get(individuoAMutar));
        }
    }

    public static void MutarIndividuo(Individuo x) {
        System.out.println("Antes de Mutar =======");
        ImprimirIndividuo(x);
        Random r = new Random();
        int max = numIntersecciones - 1;
        int min = 0;
        int interseccionAMutar = r.nextInt((max - min) + 1) + min;
        //Aqui viene los datos más importantes de la mutacion!!!
        x.intersecciones.get(interseccionAMutar).setName("MUTADO");
        x.intersecciones.get(interseccionAMutar).setC2(interseccionAMutar * 2);
        System.out.println("Despues de Mutar =======");
        ImprimirIndividuo(x);
    }

    public static void Casamiento() {

        Individuo padre;
        Individuo madre;
        Random r = new Random();
        for (int i = 0; i < tamPoblacion / 2; i++) {
            padre = p.get(i);
            madre = p.get(tamPoblacion - i - 1);
            for (int m = 0; m < padre.intersecciones.size(); m++) {
                padre.intersecciones.get(m).setName("P" + m);
                madre.intersecciones.get(m).setName("M" + m);
            }
            int max = padre.intersecciones.size() - 1;
            int min = 0;
            int PuntoDeCorte = r.nextInt((max - min) + 1) + min;
            hijo = new Individuo();
            hija = new Individuo();
            System.out.println("Punto de corte = " + PuntoDeCorte);
            for (int j = 0; j < PuntoDeCorte; j++) {
                hijo.intersecciones.add(padre.intersecciones.get(j));
                hija.intersecciones.add(madre.intersecciones.get(j));
            }
            for (int k = PuntoDeCorte; k < padre.intersecciones.size(); k++) {
                hijo.intersecciones.add(madre.intersecciones.get(k));
                hija.intersecciones.add(padre.intersecciones.get(k));
            }
            np.add(hijo);
            np.add(hija);
            System.out.println("PADRE ====");
            ImprimirIndividuo(padre);
            System.out.println("MADRE ====");
            ImprimirIndividuo(madre);
            System.out.println("HIJO  ====");
            ImprimirIndividuo(hijo);
            System.out.println("HIJA  ====");
            ImprimirIndividuo(hija);
            System.out.println("");
            System.out.println("");
            System.out.println("");

        }
        if (tamPoblacion % 2 >= 1) {
            //SI EL PADRE ES SOLTERO, EL HIJO SE COPIA NO MÁS
            padre = p.get((tamPoblacion / 2));
            hijo = new Individuo();
            for (int m = 0; m < padre.intersecciones.size(); m++) {
                padre.intersecciones.get(m).setName("PS" + m);
                hijo.intersecciones.add(padre.intersecciones.get(m));
            }
            np.add(hijo);
            System.out.println("PADRE SOLTERO====");
            ImprimirIndividuo(padre);
            System.out.println("HIJO  UNICO ====");
            ImprimirIndividuo(hijo);
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }

    }

    public static boolean EvaluarCondicionParada() {
        if (PoblacionConverge() && true) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean PoblacionConverge() {
        return true;
    }

    public static void GenerarPoblacionInicial() {
        Individuo ind;
        Interseccion aux;
        for (int i = 0; i < tamPoblacion; i++) {
            //RMI: Obtener datos de volumenes en cada interseccion
            ind = new Individuo();
            ind.intersecciones = GenerarArregloDeInterseccionesFalsa();
            double calculo = CalcularIndiceRendimientoxIndividuo(ind);
            ind.IR = calculo;
            p.add(ind);
        }
    }

    private static ArrayList<Interseccion> GenerarArregloDeInterseccionesFalsa() {
        ArrayList<Interseccion> rpta = new ArrayList<>();
        Random r = new Random();
        int max = 180; //TIEMPO MÁXIMO VERDE
        int min = 10;  //TIEMPO MINIMO COLOR
        int tiempoCiclo, c1, c2, c3;
        for (int i = 0; i < numIntersecciones; i++) {
            c1 = r.nextInt((max - min) + 1) + min;
            c2 = r.nextInt((max - min) + 1) + min;
            //c2=50;
            //c2=28;
            c3 = r.nextInt((max - min) + 1) + min;

            Interseccion x = new Interseccion("Interseccion " + i, true, c1, c2, c3);
            x.setQ_EO(0.167);
            x.setQ_NS(7);
            x.setQ_OE(150);
            x.setQ_SN(14);

            x.setVm_EO(6);
            x.setVm_NS(8);
            x.setVm_OE(12);
            x.setVm_SN(15);
            rpta.add(x);
        }
        return rpta;
    }

    public static double CalcularIndiceRendimientoxIndividuo(Individuo ind) {
        double suma = 0;
        double valor = 0;
        //Evaluamos para cada interseccion INTERCALADA los datos que se obtienen alli
        for (int i = 0; i < numIntersecciones; i = i + 2) {
            valor = EvaluarInterseccion(ind.intersecciones.get(i));
            suma += valor;
        }
        return suma;
    }

    public static Double EvaluarInterseccion(Interseccion intersecccion) {
        double resultado = 0;

        //resultado += FObjetivo2(intersecccion, C, S , Tao);
        resultado += FObjetivo2(intersecccion, 60, 0.5, 720);

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
            double S/*flujo saturacion, dato fijo*/,
            int tao/*periodo de análisis 10-15-30 minutos*/
    ) {

        //ESTO SOLO SIRVE PARA EVALUAR UNA AVENIDA QUE ENTRA A LA INTERSECCION
        //FALTA EVALUAR EL OTRO SENTIDO;
        double q = i.getQ_EO();
        int ciclo_semaforo = i.getC1() + i.getC2() + i.getC3();
        int verde_efectivo;
        verde_efectivo = i.isB() ? i.getC2() : i.getC1() + i.getC3();

        double DU = q / ciclo_semaforo;//promedio de longitudes de cola en todos los intervalos;
        double Q = (verde_efectivo * S) / Ciclo_red;
        double x = q / Q;
        double x0 = 0.67 + ((S * verde_efectivo) / 600);
        double N = Q * tao / 4;
        System.out.println("VE =" + verde_efectivo);
        /*System.out.println("DU ="+DU);
         System.out.println("Q  ="+Q);
         System.out.println("x  ="+x);
         System.out.println("x0 ="+x0);
         */

        if (x > x0) {
            N = N * ((x - 1) + Math.sqrt(Math.pow(x - 1, 2) + (12 * (x - x0)) / (Q * tao)));
            return N;//i.getC2();
            //return N;
        } else {
            return -1;
        }
    }
}
