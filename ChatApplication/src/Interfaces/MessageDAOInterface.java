/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DTO.Message;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Megatronus
 */
public interface MessageDAOInterface {
    public ArrayList<Message> getForumMessages();
    public ArrayList<Message> getUserMessages(int userId);
    public Message getMessageById(int userId);
    public ArrayList<Message> getMessageByDate(Date date);
    public ArrayList<Message> getUnreadMessages(int userId);
    public boolean sendMessage (Message message);
    public void recieveMessage();
}