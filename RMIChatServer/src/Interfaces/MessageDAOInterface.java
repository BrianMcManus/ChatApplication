/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import business.Message;
import java.sql.Date;
import java.util.ArrayList;

/**
 * This class is used to define what methods must be implemented into the MessageDAO class,
 * it states what must be taken in as parameters and what type must be returned
 * @author Megatronus
 */
public interface MessageDAOInterface {
    
    /**
     * This method gets all messages that have ever been sent through the application, 
     * adds them to a list and returns them.
     * @return returns an ArrayList of Message objects
     */
    public ArrayList<Message> getAllMessages();
    
    /**
     * This method gets all the messages that where sent by a specific user, adds them to 
     * an ArrayList and returns this list.
     * @param userId is the id of the user we wish to find messages for
     * @return returns an ArrayList of Message objects
     */
    public ArrayList<Message> getUserMessages(int userId);
    
    /**
     * This method get a single message from the message table, it uses the message id to identify
     * the message and retrieves it from the database and stores it into a Message object and returns
     * this object.
     * @param messageId is the id of the message we wish to find
     * @return returns a Message object containing the information for the message we wished to find
     */
    public Message getMessageById(int messageId);
    
    /**
     * This method gets all the messages that were posted to the forum on a particular date, adds them to a list and 
     * returns this list, it takes a single parameter, a Date object which is used to query the database to find any messages sent
     * on that date, if there are results they are packed into Message objects which are then stored into an ArrayList and returned
     * @param date is a Date object containing the date which we wish to find the messages in
     * @return returns an ArrayList of Message objects
     */
    public ArrayList<Message> getMessageByDate(Date date);
    
    /**
     * This method is used to get all unread messages for a specific user, it takes in a user id and uses this id to find all the messages
     * corresponding to that user once the message has not already been read, it then returns the results which are packed into Message objects and
     * added to an ArrayList which is then returned.
     * @param userId is the id of the user that we wish to find unread messages for.
     * @return returns an ArrayList of Message objects
     */
    public ArrayList<Message> getUnreadMessages(int userId);
    
    /**
     * This method is used to post a message to the forum, it takes in a Message object which contains the information needed to post the message,
     * if the message is posted successfully it returns a value of true otherwise it returns false.
     * @param message is the message object containing the message information
     * @return returns true or false depending on if the message was sent successfully or not.
     */
    public boolean sendMessage (Message message);
    
    /**
     * This method is used to receive messages that are posted to the forum, it has no parameters and no return values
     */
    public void recieveMessage();
    
    /**
     * This method is used to get all messages that a particular user has sent, this user is identified by the username that is taken in as a parameter,
     * if this user has messages then they are packed into message objects and these objects are added to an ArrayList and this ArrayList is then returned.
     * @param username is the username of the user we wish to find messages for.
     * @return 
     */
    public ArrayList<Message> getMessagesByUsername(String username);
    
    /**
     * This method is used to send a private message from one user to another, it takes 2 parameters, a user id in the form of an int which 
     * identifies the user trying to send the message, and a message object which contains all the information needed to send the message including the intended recipient,
     * if the message was sent successfully it will return a value true otherwise it returns a value of false stating that the message was not sent successfully.
     * @param userId is the id of the user sending the message
     * @param message is the message object that contains all the information needed to send the message
     * @return returns a true or false value depending if the message was sent successfully or not
     */
    public boolean sendPrivateMessage (int userId, Message message);
}