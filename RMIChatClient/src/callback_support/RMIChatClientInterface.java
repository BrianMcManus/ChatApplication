package callback_support;

import business.Message;
import business.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 
 * @author Brian
 * 
 * This class is used to define the methods that must be implemented by thoses
 * classes that implement this interface, it extends the Remote class to inherit
 * from it but doing so all methods must throw a RemoteException if we wish to run 
 * the methods remotely. It represents the remote objects class for the server.
 */
public interface RMIChatClientInterface extends Remote
{
    /**
     * This method pushes a notification to all users currently logged in telling them
     * that a user has logged into the chatroom, It takes in a string with the name of
     * the user that just logged in.
     * @param newLogin is the string containing the message with the users name attached.
     * @throws RemoteException this exception allows us to use the method remotely. i.e. from non-local virtual machines
     */
    public void newLoginNotification(String newLogin) throws RemoteException;
    
    /**
     * This method pushes a notification to all users currently logged in telling them
     * that a user has logged out of the chatroom, It takes in a string with the name of
     * the user that just logged out.
     * @param newLogoff is the string containing the message with the users name attached.
     * @throws RemoteException this exception must be thrown if we wish to run this method remotely.
     */
    public void newLogoffNotification(String newLogoff) throws RemoteException;
    
    /**
     * This method allows every user to have a list of messages that where posted to the forum,
     * it takes an ArrayList of Message objects that is then returned to all users.
     * @param messages is an ArrayList of Message objects.
     * @return an ArrayList of Message objects.
     * @throws RemoteException ,this exception must be thrown if we wish to run this method remotely.
     */
    public ArrayList<Message> newMessageSent(ArrayList<Message> messages) throws RemoteException;
    
     /**
     * This method is used to re-populate all clients user lists to encompass any changes in users online status,
     * it takes in an ArrayList of User objects which represents all the users registered to the application,
     * it does this by first checking if the passed arrayList of users is null, if its not then it uses the chatrooms
     * method of setting up the user list, it then returns this ArrayList of users
     * @param userList is an ArrayList of User objects
     * @return returns the ArrayList of user objects
     * @throws RemoteException must be thrown so that this method can be accessed remotely
     */
    public ArrayList<User> newUserLoggedIn(ArrayList<User> userList) throws RemoteException;
    
    /**
     * This method is used to refresh the unread messages list in the chat room gui form,
     * it firstly checks to see if there is a chatroom object available by checking if it is null, 
     * if not it uses the chat room method setUnreadMessageSenders to refresh the list of unread 
     * mail on all clients.
     * @throws RemoteException must be thrown so this method can be accessed remotely
     */
    public void repopulateUnreadMessages() throws RemoteException;
    
    /**
     * This method is used to refresh the private messages list in the chatWindow gui form,
     * it firstly checks to see if there is a chatWindow object available by checking if it is null, 
     * if not it uses the chat window method populateMessageList to refresh the list of private 
     * mail on all clients chat windows.
     * @throws RemoteException must be thrown so this method can be accessed remotely
     */
    public void repopulatePrivateMessages() throws RemoteException;
}
