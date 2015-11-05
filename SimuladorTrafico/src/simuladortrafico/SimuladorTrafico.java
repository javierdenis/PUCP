/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladortrafico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

/**
 *
 * @author javier
 */
public class SimuladorTrafico {
    static int numIntersecciones;
    static int segundosDeSimulacion;
    static int rangoMaximoXMinuto;
    static int rangoMinimoXMinuto;
    static int hi, mi, si;

    public static void main(String[] args) {
        // TODO code application logic here
        Properties prop = new Propiedades().getProperties();
        
        hi=mi=si=0;
        String cadenaEntrada;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Introduzca la hora de inicio de la simulacion (hh:mm:ss). Ejemplo: 08:30:00");
        //cadenaEntrada = sc.nextLine();
        cadenaEntrada = prop.getProperty("hora_simulacion");
        StringTokenizer tokens = new StringTokenizer(cadenaEntrada,":");
        while (tokens.hasMoreTokens()){
            hi=Integer.parseInt(tokens.nextToken());
            mi=Integer.parseInt(tokens.nextToken());
            si=Integer.parseInt(tokens.nextToken());
        }
        //String nombreArchivo = GetFechaYHora();
        String nombreArchivo = GetFecha()+"."+hi+"."+mi+"."+si;
        segundosDeSimulacion = Integer.parseInt(prop.getProperty("minutos"))*60;
        numIntersecciones = Integer.parseInt(prop.getProperty("NumeroIntersecciones"));
        
        System.out.println("Nombre de archivo = "+nombreArchivo);
        System.out.println("Segundos = "+segundosDeSimulacion);
        System.out.println("Numero de Intersecciones = "+numIntersecciones);
        ImprimirSimulacion(nombreArchivo);
    }
    
    public static String GetFechaYHora() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String respuesta = año + "." + (mes + 1) + "." + dia + "." + hora + "." + minuto + "." + segundo;
        return respuesta;
    }
    public static String GetFecha() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String respuesta = año + "." + (mes + 1) + "." + dia ;
        return respuesta;
    }
    public static void ImprimirSimulacion(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        FileWriter escritor = null;
        try {
            escritor = new FileWriter(archivo);
            PrintWriter p = new PrintWriter(escritor);
            int ch = hi;
            int cm = mi;
            int cs = si;
            for (int j = 0; j < segundosDeSimulacion; j++) {
                p.println("*********************************");
                p.println(ch+":" + cm+ ":" +cs );
                for (int i = 0; i < numIntersecciones; i++) {
                    p.println("M" + i + ":(NV,VP)(NV,VP)(NV,VP)(NV,VP)");
                }
                cs++;
                if (cs>59){
                    cs=00;
                    cm++;
                }
                if (cm>59){
                    cm=00;
                    ch++;
                }
                if (ch>23){
                    ch=00;
                }
            }
            escritor.close();
            p.close();
        } catch (IOException ex) {
            Logger.getLogger(SimuladorTrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
