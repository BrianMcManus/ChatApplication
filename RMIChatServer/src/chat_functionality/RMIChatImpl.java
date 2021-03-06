package chat_functionality;

import DAO.MessageDAO;
import DAO.UserDAO;
import DAO.UserMessageDAO;
import business.Message;
import business.User;
import business.UserMessage;
import callback_support.RMIChatClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class is used to implement those methods defined in the RMIChatInterface
 * by using the 'implements' keyword to enforce it, it extends
 * UnicastRemoteObject to handle being able to export the object to make it
 * available within the registry.
 *
 * @author Megatronus
 */
public class RMIChatImpl extends UnicastRemoteObject implements RMIChatInterface {

    //Create a new UserDAO object
    private UserDAO uDAO = new UserDAO();
    //Create a new MessageDAO object
    private MessageDAO mDAO = new MessageDAO();
    //Create a new MessageDAO object
    private UserMessageDAO umDAO = new UserMessageDAO();

    //Create a list of all the messages posted to the application
    private final ArrayList<Message> messageList = mDAO.getAllMessages();
    //Create a list to hold the messages that where posted to the forum
    private ArrayList<Message> forumMessages = mDAO.getAllForumMessages();
    //Create a list of all the users registered to the application
    private final ArrayList<User> userList = uDAO.getAllUsers();
    //Create an arrayList to hold all the users that are currently logged into the system
    private ArrayList<User> loggedOnUsers = new ArrayList<User>();
    //Create an arrayList to hold all of the UserMessage objects for messages sent
    private ArrayList<UserMessage> userMessageList = new ArrayList<UserMessage>();
    //Create an arrayList to hold all of the 'Clients'
    private final ArrayList<RMIChatClientInterface> clientList = new ArrayList();
    //Create an arraylist to hold all names in the order in which they submit messages to the forum 
    private ArrayList<String> forumSenderList = new ArrayList();
    //Create a new user instance
    private User u = new User();

    /**
     * Empty constructor
     *
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    public RMIChatImpl() throws RemoteException {

    }

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
    @Override
    public boolean addMessage(Message newMessage, User user) throws RemoteException {
        boolean addMessage = false;

        synchronized (forumMessages) {
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timeSent = new java.sql.Timestamp(calendar.getTimeInMillis());
            newMessage.setTimeSent(timeSent);
            addMessage = mDAO.addForumMessage(user.getUserId(), newMessage);
            newMessage.setMessageId(mDAO.getForumMessageId(newMessage));
            forumMessages.add(newMessage);
        }
        if (addMessage) {
            synchronized (userMessageList) {
                UserMessage um = new UserMessage(user.getUserId(), newMessage.getMessageId());
                userMessageList.add(um);
            }

            synchronized (clientList) {
                for (RMIChatClientInterface client : clientList) {
                    if (newMessage.isInForum()) {
                        client.newMessageSent(forumMessages);
                    }
                }
            }
        }
        return addMessage;
    }

    /**
     * This method gets all the users that are registered with the application,
     * it takes no parameters but returns an ArrayList of User objects,
     * containing all users details, as long as the userList is not empty
     * otherwise it returns a null value, it uses the synchronized method to
     * ensure that the data that is being read is the correct data
     * @return returns an ArrayList of User objects
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    @Override
    public ArrayList<User> getAllUsers() throws RemoteException {
        synchronized (userList) {
            if (userList != null) {
                return userList;
            }
        }
        return null;
    }

    /**
     * This method is used to register a user to the system and add them to the
     * current list of users, it takes in a User object which contains the users
     * email, password and username and tries to use these details to try and
     * register the user, if successful the method will return true otherwise it
     * will return false and the user will not be registered, it uses the
     * sychronized method to ensure that the list of users we are working with
     * contains the most recent data by holding locks on the data to avoid
     * 'dirty data '
     *
     * @param newUser is the user object containing the users details
     * @return returns a true or false value depending if the user was
     * registered successfully or not
     * @throws RemoteException must be thrown in order to access this method
     * remotely
     */
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

    /**
     * This method is used to log a user into the system, it takes in a User
     * object that contains the users details, it uses these details to try to
     * log the user in, if successful it will change the users loggedIn
     * attribute to be true to state that the user is currently logged in, it
     * then adds the user to the loggedOn user list and notifies everyone that
     * the user has logged into the chatroom, The method returns true if the
     * user was logged in successfully or returns false otherwise.
     *
     * @param user is the User object that contains the users information such
     * as email, password and username
     * @return returns a true or false value depending on if the user was logged
     * in or not
     * @throws RemoteException must be thrown to allow this method to be called
     * remotely i.e. from a non-local virtual device
     */
    @Override
    public boolean login(User user) throws RemoteException {
        //if the user is not null and is contained within the list of users
        if (user != null) {
            //user (u) = to the user object retrieved with the login method from the database
            u = uDAO.login(user.getUserName(), user.getPassword());
            //lock the list of users 
            synchronized (userList) {
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).equals(u)) {
                        userList.get(i).setLoggedIn(true);
                        u.setLoggedIn(true);
                    }
                }
            }
            synchronized (loggedOnUsers) {
                loggedOnUsers.add(u);
            }
            synchronized (clientList) {
                if (clientList.size() > 0) {
                    for (RMIChatClientInterface client : clientList) {
                        client.newLoginNotification(user.getUserName());
                        client.newUserLoggedIn(userList);
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * This method is used to log a user out of the system, it takes in a single
     * parameter, a User object which contains the information about the user
     * that wishes to log out, it sets the users loggedIn attribute as false
     * stating that the user is no longer logged into the system and also
     * removes the user from the list of users currently logged in, if all this
     * succeeds the method returns a value of true otherwise it returns a value
     * of false, if it succeeds it also notifies all users that the user has
     * logged out of the system
     *
     * @param user is a User object containing information on the user that
     * wishes to log out
     * @return returns a true or false value depending if the logout was
     * successful or not
     * @throws RemoteException must be thrown in order to access the method
     * remotely
     */
    @Override
    public boolean logoff(User user) throws RemoteException {

        //If the user passed is not null and the user is in the list of users
        if (user != null && userList.contains(user)) {

            synchronized (userList) {
                //Set the user as not logged in
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).equals(user)) {
                        userList.get(i).setLoggedIn(false);
                    }
                }
                uDAO.logout(user);
            }

            synchronized (loggedOnUsers) {
                //Remove the user from the list of users
                loggedOnUsers.remove(user);
            }

            //Notify everyone that the user has logged ut
            synchronized (clientList) {
                for (RMIChatClientInterface client : clientList) {
                    client.newLogoffNotification(user.getUserName());
                    client.newUserLoggedIn(userList);
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * This method is used to register the user for callback services so that
     * the client can receive notifications and use other methods that the
     * callback service offers, it takes in an RMIChatClientInterdace object as
     * a parameter that is created for the current client and adds them to the
     * list of clients, if this succeeds the method returns a value of true
     * otherwise it returns a value of false
     *
     * @param client is an RMIChatClientInterface that is passed so the client
     * can registered for callback services
     * @return returns a true or false value depending on if the user was
     * registered successfully or not
     * @throws RemoteException must be thrown so the method can be accessed
     * remotely
     */
    @Override
    public boolean registerForCallback(RMIChatClientInterface client) throws RemoteException {
        synchronized (clientList) {
            //If the client is not empty and the client list does not already contain the client
            if (client != null && !clientList.contains(client)) {
                //Add the client to the client list
                clientList.add(client);
                //If all succeeds return true
                return true;
            }
            //Any issues return false
            return false;
        }
    }

    /**
     * This method is used to unregister a client from the callback services
     * such as notifications and other methods, it takes in an
     * RMIChatClientInterdace object as a parameter that is created for the
     * current client and checks if the client is present in the list of clients
     * and that the clients information is not null, if this succeeds the method
     * returns a value of true otherwise it returns a value of false
     *
     * @param client is an RMIChatClientInterface that is passed so the client
     * can be unregistered for callback services
     * @return returns a value of true or false depending on if the client was
     * unregistered successfully or not
     * @throws RemoteException must be thrown so that the method can be
     * accessed remotely.
     */
    @Override
    public boolean unregisterForCallback(RMIChatClientInterface client) throws RemoteException {
        synchronized (clientList) {
            //If the client is not empty and the list of clients contains the client passed
            if (client != null && clientList.contains(client)) {
                //Remove the client from the list
                clientList.remove(client);
                //If all succeeds return true
                return true;
            }
            //If anything fails return false
            return false;
        }
    }

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
    @Override
    public ArrayList<Message> getAllMessages() throws RemoteException {
        synchronized (messageList) {
            //If the message list is not empty
            if (messageList != null && messageList.size() > 0) {
                //Return the message list
                return messageList;
            }
        }
        //If the list is empty return the empty list
        return new ArrayList();
    }

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
    @Override
    public ArrayList<Message> getAllForumMessages() throws RemoteException {
        //If the list is not empty
        if (messageList != null && messageList.size() > 0) {
            synchronized (forumMessages) {
                forumSenderList = new ArrayList();
                //Cycle through the list
                for (Message m : forumMessages) {
                    User user = umDAO.findUserByMessage(m.getMessageId());
                    forumSenderList.add(user.getUserName());
                }
                //Return the list
                return forumMessages;
            }
        }
        //If the list was empty return a new empty list
        return new ArrayList();
    }

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
    @Override
    public ArrayList<Message> getAllPrivateMessages(String username1, String username2) throws RemoteException {
        //Create an ArrayList to hold all of the messages the user has ever sent
        ArrayList<Message> sentMessages = mDAO.getMessagesByUsername(username1);
        //Create an Arraylist to hold all of the private messages
        ArrayList<Message> privateMessages = new ArrayList<Message>();

        //Make sure we are reading current data
        synchronized (sentMessages) {
            //If the message list is not empty
            if (sentMessages != null && sentMessages.size() > 0) {

                //Cycle through the list
                for (Message m : sentMessages) {
                    //If the other user was the intended receiver and the message was sent privately
                    if (m.getReceiver().equals(username2) && m.isInForum() == false) {
                        //add the message to the list of private messages
                        privateMessages.add(m);
                    }
                }
            }
        }

        //Recreate the list to hold all of the messages that the second user sent
        sentMessages = mDAO.getMessagesByUsername(username2);

        //Make sure we are reading current data
        synchronized (sentMessages) {
            //If the message list is not empty
            if (sentMessages != null && sentMessages.size() > 0) {
                //Cycle through the list
                for (Message m : sentMessages) {
                    //If the intended recipient was the other user and the message was sent privately 
                    if (m.getReceiver().equals(username1) && m.isInForum() == false) {
                        //Add the message to the list of private messages
                        privateMessages.add(m);
                    }
                }
            }
        }

        //If the list of private messages is not empty
        if (privateMessages.size() > 0) {
            //return the list of private messages
            return privateMessages;
        } else {
            //Otherwise return a new empty list
            return new ArrayList();
        }
    }

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
     */
    public boolean sendPrivateMessage(int userId, Message message) throws RemoteException {
        boolean sent = false;

        //If the message is not empty and the users id is valid
        if (message != null || userId > 0) {
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timeSent = new java.sql.Timestamp(calendar.getTimeInMillis());
            message.setTimeSent(timeSent);
            //Use the MessageDAO to send the private message
            sent = mDAO.sendPrivateMessage(userId, message);

            synchronized (clientList) {
                for (RMIChatClientInterface client : clientList) {
                    client.repopulateUnreadMessages();
                    client.repopulatePrivateMessages();
                }
            }

        }
        //Return true or false depending on if the message was successfully sent
        return sent;
    }

    /**
     * This method is used to get all details of a user by only specifying the
     * users username to do so, It takes in a single String parameter which is
     * the users username and uses this information to retrieve a User object
     * which is then stored locally before being returned.
     *
     * @param username is the username of the user we wish to get details for
     * @return returns a User object
     * @throws RemoteException must be thrown so that this method can be
     * accessed remotely
     */
    @Override
    public User getUser(String username) throws RemoteException {
        //Create a new User object instance
        User u = null;

        //If the username taken in is not empty
        if (username != null) {
            //fill the User object with the users information that we are trying to find
            u = uDAO.getUserByUsername(username);
        }
        //Returb the User object
        return u;
    }

    /**
     * This method gets all the messages that where sent to the user while they
     * where offline, it takes in an int representing the users id that we wish
     * to get unread messages for, the results are then returned if any
     *
     * @param userId is the id of the user we wish to get unread messages for
     * @return returns an arrayList of message objects
     */
    public ArrayList<Message> getUnreadMessages(int userId) throws RemoteException {
        return mDAO.getUnreadMessages(userId);
    }

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
    public ArrayList<User> getAllMessageSenders(ArrayList<Message> messages) throws RemoteException {
        ArrayList<User> senders = new ArrayList<User>();

        if (messages != null) {
            for (int i = 0; i < messages.size(); i++) {
                senders.add(umDAO.findUserByMessage(messages.get(i).getMessageId()));
            }
        }
        return senders;
    }

//    public void setPrivateMessageAsRead(Message message)
//    {
//        if(message != null)
//        {
//            mDAO.
//        }
//    }
    /**
     * Reruns the current forumSenderList. This is an arraylist of strings. This
     * method is used to supply a list of usernames to the order of
     * forumMessages so that they can accurately be displayed beside the
     * username of the user that submitted the message
     *
     * @return Return an ArrayList of Strings thats stores the names of users as
     * they submit messages to the forum chat
     * @throws RemoteException must be thrown in order to access this method
     * remotely.
     */
    @Override
    public ArrayList<String> getAllForumSenderNames() throws RemoteException {
        return forumSenderList;
    }

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
    @Override
    public boolean setMessagesAsRead(ArrayList<Message> messages, String username) throws RemoteException {
        boolean marked = false;
        if (messages != null) {
            marked = mDAO.setMessagesAsRead(messages, username);
        }
        return marked;
    }

}
