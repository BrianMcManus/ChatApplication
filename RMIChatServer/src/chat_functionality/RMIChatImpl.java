/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_functionality;

import business.Message;
import business.User;
import callback_support.RMIChatClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Megatronus
 */
public class RMIChatImpl extends UnicastRemoteObject implements RMIChatInterface {

    private final ArrayList<Message> messageList = new ArrayList();// maybe change to Vector
    private final ArrayList<User> userList = new ArrayList();
    private final ArrayList<RMIChatClientInterface> clientList = new ArrayList();

    public RMIChatImpl() throws RemoteException {

    }

    @Override
    public boolean addMessage(Message newMessage) throws RemoteException {
        boolean addMessage = false;
        synchronized (messageList) {
            addMessage = messageList.add(newMessage);
        }
        return addMessage;
    }

//    @Override
//    public Message getMessage() throws RemoteException {
//        
//    }
    
    @Override
    public boolean register(User newUser) throws RemoteException {
        synchronized (userList) {
            if (newUser != null) {
                for (User u : userList) {
                    if (u.getUserName().equalsIgnoreCase(newUser.getUserName()) && u.getPassword().equalsIgnoreCase(newUser.getPassword())) {
                        return false;
                    }
                }
                userList.add(newUser);
            }

            return true;
        }
    }

    @Override
    public boolean login(User user) throws RemoteException {
        synchronized (userList) {
            if (user != null && userList.contains(user)) {
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean registerForCallback(RMIChatClientInterface client) throws RemoteException {
        synchronized (clientList) {
            if (client != null && !clientList.contains(client)) {
                clientList.add(client);
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean unregisterForCallback(RMIChatClientInterface client) throws RemoteException {
        synchronized (clientList) {
            if (client != null && clientList.contains(client)) {
                clientList.remove(client);
                return true;
            }
            return false;
        }
    }

}
