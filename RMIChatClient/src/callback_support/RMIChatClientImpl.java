package callback_support;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import GUI.Chatroom;
import business.Message;
import business.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RMIChatClientImpl extends UnicastRemoteObject implements RMIChatClientInterface
{
    private Chatroom chatroom;
    public RMIChatClientImpl() throws RemoteException{
    }

    public RMIChatClientImpl(Chatroom chatroom)  throws RemoteException {
      this.chatroom = chatroom;
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
        chatroom.setMessages(messages);
        
        return messages;
    }
}
