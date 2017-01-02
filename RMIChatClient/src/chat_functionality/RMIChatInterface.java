package chat_functionality;

import business.Message;
import business.User;
import callback_support.RMIChatClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class is used to define what methods must be implemented into the
 * RMIChat class, it extends the Remote class to inherit from it but doing so
 * all methods must throw a RemoteException if we wish to run the methods
 * remotely, It represents the remote objects class for the Client.
 *
 * @author Megatronus
 *
 */
public interface RMIChatInterface extends Remote {

    /**
     * This method allows users to add a message to the Forum, it takes in a
     * message object as a parameter that is the object that must be sent.
     *
     * @param newMessage is the message object to be sent
     * @return returns true or false depending if the message was sent
     * successfully or not
     * @throws RemoteException must be thrown so the method can be accessed
     * remotely
     */
    public boolean addMessage(Message newMessage, User user) throws RemoteException;

    /**
     * This method is used to retrieve an ArrayList of User objects representing
     * all users currently registered to the application and returns it.
     *
     * @return returns an ArrayList of User objects
     * @throws RemoteException must be thrown so this method can be accessed
     * remotely
     */
    public ArrayList<User> getAllUsers() throws RemoteException;

    /**
     * This method is used to get all the messages that have been sent by any
     * user in any form, it takes no parameters but returns an ArrayList of
     * Message objects which represent all the messages that have been sent to
     * date.
     *
     * @return returns an ArrayList of Message objects
     * @throws RemoteException must be thrown in order to call this method
     * remotely
     */
    public ArrayList<Message> getAllMessages() throws RemoteException;

    /**
     * This method is used to get all the messages that have been sent to the
     * forum to date, it takes no parameters but returns an ArrayList of Message
     * objects which represent all the messages that have been posted to the
     * forum.
     *
     * @return returns an ArrayList of Message objects
     * @throws RemoteException must be thrown to be able to call this method
     * remotely
     */
    public ArrayList<Message> getAllForumMessages() throws RemoteException;

    /**
     * This method is used to get all the private messages sent between two
     * users, it takes in both users usernames and returns an ArrayList of all
     * the messages that have been sent between the two users in the form of
     * Message objects
     *
     * @param username1 is the first users username
     * @param username2 is the second users username
     * @return returns an ArrayList of message objects
     * @throws RemoteException must be thrown in order to access this method
     * remotely
     */
    public ArrayList<Message> getAllPrivateMessages(String username1, String username2) throws RemoteException;

    /**
     * This method is used to send a private message between two users, it takes
     * in a user id which represents the user that is sending the message, it
     * also takes in a Message object containing all the information about the
     * message such as content and intended recipient, it then returns a true or
     * false value depending on if the message was successfully sent or not
     *
     * @param userId is the id of the user that wishes to send the message
     * @param message is the Message object that is to be sent
     * @return returns a true or false value
     * @throws RemoteException must be thrown in order to call this method
     * remotely.
     */
    public boolean sendPrivateMessage(int userId, Message message) throws RemoteException;

    /**
     * This method is used to register a user to the application, it takes in a
     * User object containing all the new users information, it then uses this
     * information to verify the username is available and that the email is
     * valid, once both of these conditions pass the method returns a value of
     * true, otherwise it will return a value of false stating that the user was
     * not registered.
     *
     * @param newUser is the User object containing the users information for
     * registration
     * @return returns a true or false value stating if the user was registered
     * or not.
     * @throws RemoteException must be thrown in order to access the method
     * remotely
     */
    public boolean register(User newUser) throws RemoteException;

    /**
     * This method is used to log a user into the application, it takes in a
     * User object containing the users username and password, it then tries to
     * log the user in using this information and if successful it returns a
     * value of true, otherwise it returns a value of false stating that the
     * login has failed.
     *
     * @param user is the user object containing the user information
     * @return returns a true or false value representing if the user was
     * successfully logged in or not
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    public boolean login(User user) throws RemoteException;

    /**
     * This method is used to log a user out of the application, it takes in a
     * User object containing all the users information, it then uses this
     * information to log the user out of the system and returns a value of true
     * if successful and false otherwise
     *
     * @param user is a User object containing all the users information
     * @return returns a true or false value stating if the user was logged out
     * successfully or not
     * @throws RemoteException must be thrown in order to access this method
     * remotely
     */
    public boolean logoff(User user) throws RemoteException;

    /**
     * This method is used to get the details of a user and store them in a User
     * object, it takes in a string containing the users username, it then uses
     * this username to get the users details and stores them in a user object
     * and returns that user object.
     *
     * @param username is the username of the user we wish to get information
     * for
     * @return returns a User object containing all the information on the user
     * @throws RemoteException must be thrown in order to access this method
     * remotely
     */
    public User getUser(String username) throws RemoteException;

    /**
     * This method is used to register a user for the call back services
     * allowing the user to receive notifications and access to other methods,
     * it takes in a RMIChatClientInterface object and add this object to an
     * ArrayList of already formed 'Clients', it then returns a value of true if
     * the method was successful or false otherwise.
     *
     * @param client is the RMIChatClientInterface object to be added
     * @return returns a true or false value stating if the object was added or
     * not
     * @throws RemoteException must be thrown in order to access the method
     * remotely
     */
    public boolean registerForCallback(RMIChatClientInterface client) throws RemoteException;

    /**
     * This method is used to remove a 'Client' from the callback services list,
     * it takes in the RMIChatClientInterface object that is to be removed or
     * 'unregistered', it then returns a value of true if the method was
     * successful in unregistering the client or false otherwise.
     *
     * @param client is the RMIChatClientInterface object to be removed.
     * @return returns a true or false value depending if the method was
     * successful in removing the client.
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    public boolean unregisterForCallback(RMIChatClientInterface client) throws RemoteException;

    /**
     * This method is used to get all the messages that have not been read by a
     * specific user, the method takes a single parameter, an int representing a
     * user id, it uses this id to find all the messages from that user and
     * stores the unread ones and returns them
     *
     * @param userId is the id of the user we wish to get unread messages for
     * @return returns an ArrayList of Message objects
     */
    public ArrayList<Message> getUnreadMessages(int userId) throws RemoteException;

    /**
     * This method is used to get all the senders of the unread messages of a
     * particular user, it takes in an an ArrayList of Message objects
     * representing this users unread messages, it then cycles through this list
     * and uses the userMessageDAO to get the user that sent each message and
     * stores them in a list and then returns this list
     *
     * @param messages is the messages that the user has not read yet
     * @return returns an ArrayList of User objects who sent said messages
     */
    public ArrayList<User> getAllMessageSenders(ArrayList<Message> messages) throws RemoteException;

    /**
     * This method is used to supply a string arraylist of usernames that
     * correspond in order to the messages submitted to the forum chat. With 
     * this list we are able to display the names beside the messages in the 
     * chat with the names of the users being the name of the user that sent the 
     * message
     *
     * @return ArrayList<String> collection of strings that are usernames
     * @throws RemoteException This thrown exception is used when we wish to 
     * have applications use resources across networks non-locally
     */
    public ArrayList<String> getAllForumSenderNames() throws RemoteException;
    
    public boolean setMessagesAsRead(ArrayList<Message> messages, String username) throws RemoteException;

}
