/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import business.Message;
import business.User;
import java.util.ArrayList;

/**
 * This class is used to define the methods that should be implemented into the UserMessage class and what
 * parameters they should take in and what type they should return.
 * 
 * @author Brian
 */
public interface UserMessageInterface {
    /**
     * This method is used to find the user that sent a particular message based on the messages id,
     * once it finds a result it packs these results into a User object and returns this object.
     * @param messageId is the id of the message which we wish to find the sender for.
     * @return returns a User object
     */
    public User findUserByMessage(int messageId);
    
    /**
     * This method is used to get all the messages sent by a particular user, it takes in the users id in the form of an int
     * and uses this information to find all the messages associated with that user, once it finds results it packs them into
     * message objects and these objects are stored into an ArrayList which is then returned.
     * @param userId is the id of the user that we wish to find messages for
     * @return returns an ArrayList of Message objects 
     */
    public ArrayList<Message> getAllMessagesByUser(int userId);
}
