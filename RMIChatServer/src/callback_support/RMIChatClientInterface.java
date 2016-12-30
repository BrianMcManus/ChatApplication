package callback_support;

import business.Message;
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
     * @throws RemoteException, this exception must be thrown if we wish to run this method remotely.
     */
    public void newLoginNotification(String newLogin) throws RemoteException;
    
    /**
     * This method pushes a notification to all users currently logged in telling them
     * that a user has logged out of the chatroom, It takes in a string with the name of
     * the user that just logged out.
     * @param newLogoff is the string containing the message with the users name attached.
     * @throws RemoteException, this exception must be thrown if we wish to run this method remotely.
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
}
