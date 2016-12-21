package callback_support;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import business.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RMIChatClientImpl extends UnicastRemoteObject implements RMIChatClientInterface
{
    public RMIChatClientImpl() throws RemoteException{
    }
    
    @Override
    public void newLoginNotification(String newLogin) throws RemoteException {
        System.out.println(newLogin + " has logged into the chat room");
    }
    
    @Override
    public void newLogoffNotification(String newLogoff) throws RemoteException {
        System.out.println(newLogoff + " has left into the chat room");
    }
    
    @Override
    public ArrayList<Message> newMessageSent(ArrayList<Message> messages) throws RemoteException
    {
        return messages;
    }
}
