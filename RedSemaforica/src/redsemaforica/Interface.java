/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redsemaforica;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author CONSULTOR
 */
public interface Interface extends Remote {
    
    void dayHello() throws RemoteException;
}
