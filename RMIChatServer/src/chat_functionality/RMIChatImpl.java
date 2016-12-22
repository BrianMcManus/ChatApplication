/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_functionality;

import DAO.MessageDAO;
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
    private MessageDAO mDAO = new MessageDAO();

    private final ArrayList<Message> messageList = mDAO.getAllMessages();// maybe change to Vector
    private ArrayList<Message> forumMessages = new ArrayList<Message>();
    private final ArrayList<User> userList = uDAO.getAllUsers();
    private ArrayList<User> loggedOnUsers = new ArrayList<User>();
    private final ArrayList<RMIChatClientInterface> clientList = new ArrayList();
    private User u = new User();
    
    public RMIChatImpl() throws RemoteException {

    }

    @Override
    public boolean addMessage(Message newMessage) throws RemoteException {
        boolean addMessage = false;
        synchronized (messageList) {
            addMessage = messageList.add(newMessage);
        }
        
        synchronized (clientList) {
                    for (RMIChatClientInterface client : clientList) {
                        client.newMessageSent(messageList);
                    }
                }
        return addMessage;
    }

    @Override
    public ArrayList<User> getAllUsers() throws RemoteException {
        synchronized (userList) {
            if (userList != null) {
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
                        if (u.getUserName().equalsIgnoreCase(newUser.getUserName())) {
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
            if (user != null && userList.contains(user)) {
                uDAO = new UserDAO();
                u = uDAO.login(user.getUserName(), user.getPassword());
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).equals(u)) {
                        userList.get(i).setLoggedIn(true);
                        u.setLoggedIn(true);
                synchronized(loggedOnUsers){
                        loggedOnUsers.add(u);
                }
                    }
                }
                synchronized (clientList) {
                    if(clientList.size()>0){
                    for (RMIChatClientInterface client : clientList) {
                        client.newLoginNotification(user.getUserName());
                    }}
                }
                return true;
            }
            return false;
        }
    }
    
    @Override
    public boolean logoff(User user) throws RemoteException {
        synchronized (userList) {
            if (user != null && userList.contains(user)) {
                user.setLoggedIn(false);
                synchronized(loggedOnUsers){
                loggedOnUsers.remove(user);
                }
            } else {
                return false;
            }
        }

        synchronized (clientList) {
            for (RMIChatClientInterface client : clientList) {
                client.newLogoffNotification(user.getUserName());
            }
        }
        return true;
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

    @Override
    public ArrayList<Message> getAllMessages() throws RemoteException {
        synchronized (messageList) {
            if (messageList != null && messageList.size() > 0) {
                return messageList;
            }
        }
        return new ArrayList();
    }
    
    @Override
    public ArrayList<Message> getAllForumMessages() throws RemoteException {
        synchronized (messageList) {
            if (messageList != null && messageList.size() > 0) {
                
                for(Message m: messageList)
                {
                    if(m.isInForum())
                    {
                        forumMessages.add(m);
                    }
                }
                return forumMessages;
            }
        }
        return new ArrayList();
    }

    @Override
    public User getCurrentUser(RMIChatClientInterface client) throws RemoteException {
        if (client!=null && clientList.size()>0) {
            synchronized(clientList){
                for(int i = 0; i<clientList.size(); i++){
                    System.out.println("Parameter: " + client.toString());
                    System.out.println("List item: " + clientList.get(i).toString());
                    if(clientList.get(i).equals(client)){
                        u = loggedOnUsers.get(i);
                        return u;
                    }
                }
            }
        }
        return null;
    }
}
