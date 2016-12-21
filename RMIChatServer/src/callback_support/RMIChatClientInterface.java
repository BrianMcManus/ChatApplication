/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callback_support;

import business.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RMIChatClientInterface extends Remote
{
    public void newLoginNotification(String newLogin) throws RemoteException;
    public void newLogoffNotification(String newLogoff) throws RemoteException;
    
    public ArrayList<Message> newMessageSent(ArrayList<Message> messages) throws RemoteException;
}