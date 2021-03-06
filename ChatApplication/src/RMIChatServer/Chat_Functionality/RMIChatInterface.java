/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIChatServer.Chat_Functionality;

import DTO.Message;
import DTO.User;
import RMIChatClient.CallbackSupport.RMIChatClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Megatronus
 */
public interface RMIChatInterface extends Remote
{
    public boolean addMessage(Message newMessage) throws RemoteException;
    public Message getMessage() throws RemoteException;
    
    public boolean register(User newUser) throws RemoteException;
    public boolean login(User user) throws RemoteException;
    
    public boolean registerForCallback(RMIChatClientInterface client) throws RemoteException;
    public boolean unregisterForCallback(RMIChatClientInterface client) throws RemoteException;
}

