/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import business.Message;
import Interfaces.MessageDAOInterface;
import business.User;
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
            String query = "SELECT * FROM Message, userMessage WHERE Message.messageId = userMessage.MessageId and userMessage.UserId = (?)";
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
                m.setReceiver(rs.getString("reciever"));
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
    public ArrayList<Message> getMessagesByUsername(String username) {
        
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();
            
            // creating the query
            String query = "select message.messageId, message.message, message.reciever, message.messageRead, message.inForum, "
                    + "message.timeSent from message, usermessage, users where message.messageId = usermessage.messageId and usermessage.userId = users.userId and users.username = (?) and message.inForum = false;";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setString(1, username);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                m = new Message();

                
                m.setMessageId(rs.getInt("messageId"));
                m.setMessageContent(rs.getString("message"));
                m.setReceiver(rs.getString("reciever"));
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
    public ArrayList<Message> getAllMessages() {
        
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message";
            ps = con.prepareStatement(query);
            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                m = new Message();

                
                m.setMessageId(rs.getInt("messageId"));
                m.setMessageContent(rs.getString("message"));
                m.setReceiver(rs.getString("reciever"));
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
                m.setReceiver(rs.getString("reciever"));
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
                m.setReceiver(rs.getString("reciever"));
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
                m.setReceiver(rs.getString("reciever"));
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

    @Override
    public boolean sendPrivateMessage(int userId, Message message) {
        // Objects for stablishing the connection
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Checking that the message object received is not null
        if (message == null) {
            return false;
        }

        try {
            // Getting the connection
            con = getConnection();

            // Preparing the query
            String query = "INSERT INTO MESSAGE (message, reciever, messageRead, timeSent, inForum) VALUES (?,?,?,?,?);";

            // Preparing the statement for the insertion
            ps = con.prepareStatement(query);
            
            
            ps.setString(1, message.getMessageContent());
            ps.setString(2, message.getReceiver());
            ps.setBoolean(3, message.isRead());
            ps.setDate(4, message.getTimeSent());
            ps.setBoolean(5, message.isInForum());
            
             // Executing the query and storing the response
            // if i>0 the information was inserted
            // if i==0 no information inserted into the database
            int i = ps.executeUpdate();
            
            
            query = "SELECT * FROM Message WHERE message.message = ? and message.reciever = ? and message.timeSent = ?";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setString(1, message.getMessageContent());
            ps.setString(2, message.getReceiver());
            ps.setDate(3, message.getTimeSent());

            // executing the query
            rs = ps.executeQuery();

            Message mess = new Message();
            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current customer
                

                
                mess.setMessageId(rs.getInt("messageId"));
                mess.setMessageContent(rs.getString("message"));
                mess.setReceiver(rs.getString("reciever"));
                mess.setTimeSent(rs.getDate("timeSent"));
                mess.setRead(rs.getBoolean("messageRead"));
                mess.setInForum(rs.getBoolean("inForum"));
               
            }
            

           
            
            query = "INSERT INTO USERMESSAGE (userId, messageId) VALUES (?, ?);";

            // Preparing the statement for the insertion
            ps = con.prepareStatement(query);
            
            ps.setInt(1, userId);
            ps.setInt(2, mess.getMessageId());

            // Executing the query and storing the response
            // if i>0 the information was inserted
            // if i==0 no information inserted into the database
            int j = ps.executeUpdate();

            // Returning true if the response is > than 0
           if(i>0 && j>0)
           {
                return true;
           }
           else
           {
               return false;
           }
            

            // catching any possible exception
        } catch (SQLException e) {
            return false;
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
                return false;
            }
        }

    }
    
    
}
