/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Message;
import DTO.User;
import Interfaces.UserMessageInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class UserMessageDAO extends DAO implements UserMessageInterface{
    
    // objects for establishing the connection
        private Connection con = null;
        private PreparedStatement ps = null;
        private ResultSet rs = null;
        

    @Override
    public User findUserByMessage(int messageId) {
         User user = new User();
         
        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM User WHERE User.UserId = UserMessage.UserId and UserMessage.MessageId = (?)";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setInt(1, messageId);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
               
                
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(rs.getBoolean("loggedIn"));
                

                
            }
            // Catching any possible exception
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                return null;
            }
        }
        // returning message
        return user;
    }

    @Override
    public ArrayList<Message> getAllMessagesByUser(int userId) {
        
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM UserMessage WHERE UserId = (?)";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setInt(1, userId);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                Message m = new Message();

                
                m.setMessageId(rs.getInt("messageId"));
                m.setMessageContent(rs.getString("message"));
                m.setSender(rs.getString("sender"));
                m.setRead(rs.getBoolean("messageRead"));
                m.setInForum(rs.getBoolean("inForum"));
                

                // Store the current message object (now filled with information) in the arraylist
                messages.add(m);
            }
            // Catching any possible exception
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                return null;
            }
        }
        // returning the messages ArrayList
        return messages;
    }
    
}
