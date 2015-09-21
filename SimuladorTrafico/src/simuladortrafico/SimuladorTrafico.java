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

/**
 *
 * @author javier
 */
public class SimuladorTrafico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int hi, mi, si;
        hi=mi=si=0;
        String cadenaEntrada;
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca la hora de inicio de la simulacion (hh:mm:ss). Ejemplo: 08:30:00");
        //cadenaEntrada = sc.nextLine();
        cadenaEntrada = "10:08:23";
        StringTokenizer tokens = new StringTokenizer(cadenaEntrada,":");
        while (tokens.hasMoreTokens()){
            hi=Integer.parseInt(tokens.nextToken());
            mi=Integer.parseInt(tokens.nextToken());
            si=Integer.parseInt(tokens.nextToken());
        }
        System.out.print("Introduzca la cantidad de minutos de la simulacion. Ejemplo: 15");        
        //String nombreArchivo = GetFechaYHora();
        String nombreArchivo = GetFecha()+"."+hi+"."+mi+"."+si;
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
            for (int j = 0; j < 100; j++) {
                p.println("*******************************************");
                p.println("10:" + "20" + ":" + j * 10 + ":");
                for (int i = 0; i < 10; i++) {
                    p.println("M" + i + ":(10,20)(10,20)(10,20)(10,20)");
                }
            }
            escritor.close();
            p.close();
        } catch (IOException ex) {
            Logger.getLogger(SimuladorTrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
