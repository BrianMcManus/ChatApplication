/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DTO.Message;
import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public interface UserMessageInterface {
    public User findUserByMessage(int messageId);
    public ArrayList<Message> getAllMessagesByUser(int userId);
}
