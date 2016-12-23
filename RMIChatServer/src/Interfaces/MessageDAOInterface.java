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
 *
 * @author Megatronus
 */
public interface MessageDAOInterface {
    public ArrayList<Message> getAllMessages();
    public ArrayList<Message> getUserMessages(int userId);
    public Message getMessageById(int messageId);
    public ArrayList<Message> getMessageByDate(Date date);
    public ArrayList<Message> getUnreadMessages(int userId);
    public boolean sendMessage (Message message);
    public void recieveMessage();
    public ArrayList<Message> getMessagesByUsername(String username);
    public boolean sendPrivateMessage (int userId, Message message);
}