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
     * This addMessage method is used to submit messages to the forum chatroom.
     * It does this by accepting two variables, them being a message object and
     * a user object. The message object contains all information necessary to
     * the message with the exception of the time the message was submitted.
     * This value is only assigned to the message once it reaches the server as
     * this allows the list to maintain integrity in it's order as the time they
     * were sent will always correspond with the time they reached the server
     * and therefore the servers time-zone.
     *
     * @param newMessage the message to be added to the forum list
     * @param user the user that submitted the message to the list. This is
     * necessary for when we wish to add the message to the database as we also
     * require the id of the user that has sent the message so that we may
     * insert their id into the usermessage table.
     *
     * @return An boolean value of true/false dependant on whether the message
     * was successfully added to the list and database.
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    public boolean addMessage(Message newMessage, User user) throws RemoteException;

    /**
     * This method gets all the users that are registered with the application,
     * it takes no parameters but returns an ArrayList of User objects,
     * containing all users details, as long as the userList is not empty
     * otherwise it returns a null value, it uses the synchronized method to
     * ensure that the data that is being read is the correct data
     *
     * @return returns an ArrayList of User objects
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    public ArrayList<User> getAllUsers() throws RemoteException;

    /**
     * This method is used to get all messages that have been sent through the
     * application to date, it takes no parameters but returns an ArrayList of
     * Message objects that represents all messages that have been sent to date,
     * It uses the synchronized method to hold locks on this list of messages so
     * it only reads correct data and avoid the dirty data problem
     *
     * @return returns an ArrayList of Message objects
     * @throws RemoteException must be thrown so the method can be accessed
     * remotely
     */
    public ArrayList<Message> getAllMessages() throws RemoteException;

    /**
     * This method gets all messages that have been posted through the forum, it
     * takes no parameters but returns an ArrayList of Message objects, it
     * cycles through the list of all messages sent through the application and
     * filters out the ones that where sent to the forum and stores them in a
     * new list, it then returns this list
     *
     * @return returns an ArrayList of Message objects
     * @throws RemoteException must be thrown in order to access this method
     * remotely
     */
    public ArrayList<Message> getAllForumMessages() throws RemoteException;

    /**
     * This method is used to get all messages that have been privately sent
     * between two users, it takes two String parameters, two usernames, it uses
     * these usernames to get all the messages sent by each user and searches
     * through these messages to see if the recipient matched that of the other
     * username taken in, if it does then we store it in a list, after doing
     * this for each user we are then left with a list of messages that where
     * sent privately between these two users in the form of an ArrayList of
     * Message objects which is then returned.
     *
     * @param username1 is the username of the user who wishes to use private
     * messaging
     * @param username2 is the username of the second user who wishes to use
     * private messaging
     * @return returns an ArrayList of Message objects
     * @throws RemoteException must be thrown so that the method can be called
     * remotely
     */
    public ArrayList<Message> getAllPrivateMessages(String username1, String username2) throws RemoteException;

    /**
     * This method allows a user to send a private message to another user, it
     * takes in two parameters, a user id which represents the person who is
     * sending the message, and a Message object that contains all the
     * information relevant that is needed to send the message such as the
     * message content itself and the intended recipient, it then uses the
     * MessageDAO object created at the start of the class to send the message
     * using the information taken in to this method, if the message is sent
     * successfully then the method will return a value of true otherwise it
     * will return a value of false.
     *
     * @param userId is the id of the user that wishes to send the private
     * message
     * @param message is the Message object contain all the message information.
     * @return returns a true or false value depending on if the message was
     * sent successfully or not
     * @throws RemoteException must be thrown in order to access this method
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
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
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
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     * @return returns an ArrayList of User objects who sent said messages
     */
    public ArrayList<User> getAllMessageSenders(ArrayList<Message> messages) throws RemoteException;

    /**
     * Reruns the current forumSenderList. This is an arraylist of strings. This
     * method is used to supply a list of usernames to the order of
     * forumMessages so that they can accurately be displayed beside the
     * username of the user that submitted the message
     *
     * @return Return an ArrayList of strings thats stores the names of users as
     * they submit messages to the forum chat
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    public ArrayList<String> getAllForumSenderNames() throws RemoteException;

    /**
     * This method is used to set all this users messages as read as they are
     * viewing them, it takes in an ArrayList of Message objects representing
     * the messages sent privately between two users and if the username is the
     * same as the intended receiver then it marks them as read in the database.
     *
     * @param messages an ArrayList of Message objects that represent the
     * messages sent between two users privately
     * @param username is the username of the user looking at the messages
     * @return returns true or false depending if the method was successful or
     * not
     * @throws RemoteException must be thrown or order to access this method
     * remotely
     */
    public boolean setMessagesAsRead(ArrayList<Message> messages, String username) throws RemoteException;

}
