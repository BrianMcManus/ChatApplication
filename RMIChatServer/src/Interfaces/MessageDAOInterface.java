/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import business.Message;
import java.sql.Timestamp;
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
    public ArrayList<Message> getMessageByDate(Timestamp date);
    
    /**
     * This method is used to get all unread messages for a specific user, it takes in a user id and uses this id to find all the messages
     * corresponding to that user once the message has not already been read, it then returns the results which are packed into Message objects and
     * added to an ArrayList which is then returned.
     * @param userId is the id of the user that we wish to find unread messages for.
     * @return returns an ArrayList of Message objects
     */
    public ArrayList<Message> getUnreadMessages(int userId);
    
    /**
     * This method is used to get all messages that a particular user has sent, this user is identified by the username that is taken in as a parameter,
     * if this user has messages then they are packed into message objects and these objects are added to an ArrayList and this ArrayList is then returned.
     * @param username is the username of the user we wish to find messages for.
     * @return an arraylist of message objects
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
    
    /**
     * This method is used to record messages submitted to the forum by 
     * recording them to the database. The method itself accepts two parameters, 
     * these parameters being an int (userId) and a message object (newMessage).
     * 
     * @param userId This is the id of the user submitting the message to the 
     * forum. The id is required for entering in information to the user message
     * table
     * @param newMessage This the actual message being submitted to the forum 
     * and database
     * @return a boolean value of true/false or 0/1 to indicate whether the 
     * insertion was successful or not  
     */
    public boolean addForumMessage(int userId, Message newMessage);
    
    /**
     * This method is to allow us to get id associated with a message in the 
     * database. This is done submitting a message object. This allows us to 
     * maintain an arraylist on the clientside that directly reflects the 
     * message table in the database.
     * @param newMessage the message to be used in the query for finding the 
     * proper id of said message
     * @return returns an int with a value equal to the messageId stored in the 
     * database
     */
    public int getForumMessageId(Message newMessage);
    
    /**
     * This method returns an arraylist of message objects. With the requirement
     * that the inForum boolean value stored in each object is equal to true.
     * @return An arraylist of messages that are meant for the forum chat
     */
    public ArrayList<Message> getAllForumMessages();
    
    /**
     * This method is used to set all this users messages as read as they are viewing them, it
     * takes in an ArrayList of Message objects representing the messages sent privately between
     * two users and if the username is the same as the intended receiver then it marks them as 
     * read in the database.
     * @param messages an ArrayList of Message objects that represent the messages sent between two users privately
     * @param username is the username of the user looking at the messages
     * @return returns true or false depending if the method was successful or not#
     */
    public boolean setMessagesAsRead(ArrayList<Message> messages, String username);


}
