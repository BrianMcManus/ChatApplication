/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIChatClient.CallbackSupport;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMIChatClientInterface extends Remote
{
    public void newLoginNotification(String newLogin) throws RemoteException;
    public void newLogoffNotification(String newLogoff) throws RemoteException; 
}
