/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_functionality;

import DAO.UserDAO;
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

    private UserDAO uDAO = new UserDAO();
    private final ArrayList<Message> messageList = new ArrayList();// maybe change to Vector
    private final ArrayList<User> userList = uDAO.getAllUsers();
    private final ArrayList<RMIChatClientInterface> clientList = new ArrayList();
    private User u;
    
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
    @Override
    public ArrayList<User> getAllUsers() throws RemoteException{
        synchronized(userList){
            if(userList!=null){
                return userList;
            }
        }
        return null;
    }
    
    @Override
    public boolean register(User newUser) throws RemoteException {
        synchronized (userList) {
            if (newUser != null) {
                if (userList.size() > 0) {
                    for (User u : userList) {
                        if (u.getUserName().equalsIgnoreCase(newUser.getUserName()) && u.getPassword().equalsIgnoreCase(newUser.getPassword())) {
                            return false;
                        }
                    }
                    
                }
                uDAO.register(newUser);
                userList.add(newUser);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean login(User user) throws RemoteException {
        synchronized (userList) {
            if(user != null && userList.contains(user)) {
                uDAO = new UserDAO();
                u = uDAO.login(user.getUserName(), user.getPassword());
                for(int i = 0; i<userList.size();i++){
                    if(userList.get(i).equals(u)){
                        userList.get(i).setLoggedIn(true);
                    }
                }
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
