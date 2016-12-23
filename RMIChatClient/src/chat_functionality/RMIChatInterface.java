/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_functionality;


import business.Message;
import business.User;
import callback_support.RMIChatClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Megatronus
 */
public interface RMIChatInterface extends Remote
{
    public boolean addMessage(Message newMessage) throws RemoteException;
    public ArrayList<User> getAllUsers() throws RemoteException;
    public User getCurrentUser(RMIChatClientInterface client) throws RemoteException;
    public ArrayList<Message> getAllMessages() throws RemoteException;
    public ArrayList<Message> getAllForumMessages() throws RemoteException;
    public ArrayList<Message> getAllPrivateMessages(String username1, String username2) throws RemoteException;
    
    public boolean register(User newUser) throws RemoteException;
    public boolean login(User user) throws RemoteException;
    public boolean logoff(User user) throws RemoteException;
    
    public boolean registerForCallback(RMIChatClientInterface client) throws RemoteException;
    public boolean unregisterForCallback(RMIChatClientInterface client) throws RemoteException;

 
}

