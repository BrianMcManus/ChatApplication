package callback_support;

import GUI.Chatroom;
import business.Message;
import business.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * This class is used to implement those methods defined in the RMIChatClientInterface by
 * using the 'implements' keyword to enforce it, it extends UnicastRemoteObject to handle being able to 
 * export the object to make it available within the registry.
 * @author Brian
 */
public class RMIChatClientImpl extends UnicastRemoteObject implements RMIChatClientInterface
{
    //Define a new chatroom form
    private Chatroom chatroom;
    
    /**
     * Empty constructor
     * @throws RemoteException, must be thrown if we want to call this constructor remotely
     */
    public RMIChatClientImpl() throws RemoteException{
    }

    /**
     * This constructor constructs a new instance of the RMIChatClientImpl class
     * and takes in a instance of the chatroom as a parameter and sets the chatroom
     * form as the one taken in as a parameter.
     * @param chatroom is the new chatroom GUI form 
     * @throws RemoteException, must be thrown if we want to call this constructor remotely 
     */
    public RMIChatClientImpl(Chatroom chatroom)  throws RemoteException {
      this.chatroom = chatroom;
    }
    
    /**
     * This method sends a notification to all users letting them know that a user has
     * logged into the chatroom, it takes in a string containing the name of the user that
     * has just logged in and outputs a message telling all users currently in the forum that 
     * this user has entered
     * @param newLogin is the username of the user that just logged in
     * @throws RemoteException, this exception must be thrown to call this method remotely
     */
    @Override
    public void newLoginNotification(String newLogin) throws RemoteException {
        System.out.println(newLogin + " has logged into the chat room");
    }
    
    /**
     * This method sends a notification to all users letting them know that a user has just logged out
     * of the chatroom, it takes in a string containing the username of the user that just left and outputs
     * a message to all users in the forum that the user has left
     * @param newLogoff is the username of the user that has left
     * @throws RemoteException must be thrown in order to call this method remotely
     */
    @Override
    public void newLogoffNotification(String newLogoff) throws RemoteException {
        System.out.println(newLogoff + " has left the chat room");
    }
    
    /**
     * This method returns all messages that have been posted to the forum, it takes in an ArrayList of Message
     * objects and uses the chatroom form to pass this ArrayList to the chatroom by using the chatrooms
     * 'setMessages' method, it also returns the ArrayList for further use.
     * @param messages is an ArrayList of messages that have currently been sent to the forum
     * @return returns an ArrayList of Message objects
     * @throws RemoteException must be thrown in order to call this method remotely
     */
    @Override
    public ArrayList<Message> newMessageSent(ArrayList<Message> messages) throws RemoteException
    {
        chatroom.setMessages(messages);
        
        return messages;
    }
    
    
    @Override
    public ArrayList<User> newUserLoggedIn(ArrayList<User> userList) throws RemoteException
    {
        chatroom.setUsers(userList);
        
        return userList;
    }
    
    @Override
    public void repopulateUnreadMessages() throws RemoteException
    {
        chatroom.populateUnreadMessageList();
    }
}
