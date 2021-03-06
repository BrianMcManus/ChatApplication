/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Message;
import Interfaces.MessageDAOInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Megatronus
 */
public class MessageDAO extends DAO implements MessageDAOInterface{
        // objects for establishing the connection
        private Connection con = null;
        private PreparedStatement ps = null;
        private ResultSet rs = null;
        Message m;
        
    @Override
    public ArrayList<Message> getUserMessages(int userId) {
        
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE Message.messageId = userMessage.MessageId and userMessage.UserId = (?)";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setInt(1, userId);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                m = new Message();

                
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
        // returning the addresses ArrayList
        return messages;
    }

    @Override
    public ArrayList<Message> getForumMessages() {
        
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE inForum = true";

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                m = new Message();

                
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
        // returning the addresses ArrayList
        return messages;
    }

    @Override
    public Message getMessageById(int messageId) {

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE Message.messageId = (?)";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setInt(1, messageId);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                m = new Message();

                
                m.setMessageId(rs.getInt("messageId"));
                m.setMessageContent(rs.getString("message"));
                m.setSender(rs.getString("sender"));
                m.setRead(rs.getBoolean("messageRead"));
                m.setInForum(rs.getBoolean("inForum"));
                

                // Store the current message object (now filled with information) in the arraylist
                
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
        // returning the addresses ArrayList
        return m;
    }

    @Override
    public ArrayList<Message> getMessageByDate(Date date) {
        
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE timeSent = (?)";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setDate(1, date);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                m = new Message();

                
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
        // returning the addresses ArrayList
        return messages;
    }

    @Override
    public ArrayList<Message> getUnreadMessages(int userId) {
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE userId = (?) AND messageRead = false";
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
        // returning the addresses ArrayList
        return messages;
    }

    @Override
    public boolean sendMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void recieveMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
