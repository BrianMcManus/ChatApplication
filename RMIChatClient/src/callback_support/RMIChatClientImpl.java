package callback_support;

import GUI.ChatWindow;
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
    private ChatWindow chatwindow;
    
    /**
     * Empty constructor
     * @throws RemoteException must be thrown if we want to call this constructor remotely
     */
    public RMIChatClientImpl() throws RemoteException{
    }

    /**
     * This constructor constructs a new instance of the RMIChatClientImpl class
     * and takes in a instance of the chatroom as a parameter and sets the chatroom
     * form as the one taken in as a parameter.
     * @param chatroom is the new chatroom GUI form 
     * @throws RemoteException must be thrown if we want to call this constructor remotely 
     */
    public RMIChatClientImpl(Chatroom chatroom)  throws RemoteException {
      this.chatroom = chatroom;
    }

    
    /**
     * This constructor constructs a new instance of the RMIChatClientImpl class and 
     * takes in an instance of a chatWindow as a parameter and sets this classes chat
     * window instance equal to that of the one passed.
     * @param chatwindow is a new chat window form
     * @throws RemoteException must be thrown so that this method can be accessed remotely
     */
    public RMIChatClientImpl(ChatWindow chatwindow) throws RemoteException
    {
        this.chatwindow = chatwindow;
    }
    
    /**
     * This method sends a notification to all users letting them know that a user has
     * logged into the chatroom, it takes in a string containing the name of the user that
     * has just logged in and outputs a message telling all users currently in the forum that 
     * this user has entered
     * @param newLogin is the username of the user that just logged in
     * @throws RemoteException this exception must be thrown to call this method remotely
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
        if(chatroom != null)
        {
        chatroom.setMessages(messages);
        }
        
        return messages;
    }
    
    
    /**
     * This method is used to re-populate all clients user lists to encompass any changes in users online status,
     * it takes in an ArrayList of User objects which represents all the users registered to the application,
     * it does this by first checking if the passed arrayList of users is null, if its not then it uses the chatrooms
     * method of setting up the user list, it then returns this ArrayList of users
     * @param userList is an ArrayList of User objects
     * @return returns the ArrayList of user objects
     * @throws RemoteException must be thrown so that this method can be accessed remotely
     */
    @Override
    public ArrayList<User> newUserLoggedIn(ArrayList<User> userList) throws RemoteException
    {
        if(chatroom != null)
        {
        chatroom.setUsers(userList);
        }
        
        return userList;
    }
    
    /**
     * This method is used to refresh the unread messages list in the chat room gui form,
     * it firstly checks to see if there is a chatroom object available by checking if it is null, 
     * if not it uses the chat room method setUnreadMessageSenders to refresh the list of unread 
     * mail on all clients.
     * @throws RemoteException must be thrown so this method can be accessed remotely
     */
    @Override
    public void repopulateUnreadMessages() throws RemoteException
    {
        if(chatroom != null)
        {
        chatroom.setUnreadMessageSenders();
        }
    }
    
    /**
     * This method is used to refresh the private messages list in the chatWindow gui form,
     * it firstly checks to see if there is a chatWindow object available by checking if it is null, 
     * if not it uses the chat window method populateMessageList to refresh the list of private 
     * mail on all clients chat windows.
     * @throws RemoteException must be thrown so this method can be accessed remotely
     */
    @Override
    public void repopulatePrivateMessages() throws RemoteException
    {
        if(chatwindow != null)
        {
        chatwindow.populateMessageList();
        }
    }
}
