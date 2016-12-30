package DAO;

import business.Message;
import Interfaces.MessageDAOInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used to manage information coming from and being posted to the message table
 * in the database, it implements the MesssageDAOInterface to ensure all methods are being implemented
 * and are correctly implemented and extends the DAO class to be able to access connection methods from the 
 * DAO class.
 * @author Megatronus
 */
public class MessageDAO extends DAO implements MessageDAOInterface{
        // objects for establishing the connection
        private Connection con = null;
        private PreparedStatement ps = null;
        private ResultSet rs = null;
        //New message object
        private Message m;
        
    /**
     * This method is used to get an ArrayList of message objects that represent all the messages sent by a particular user,
     * the method takes in a single parameter, a int representing the id of the user, using this id we query the database for all messages
     * that this user has sent, it then stores the returned values into message objects and add them to an ArrayList, it then returns the ArrayList
     * @param userId is the id of the user that we want the messages for
     * @return returns an ArrayList of Message objects
     */    
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
                // Make a message object for the current message
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
        // returning the messages ArrayList
        return messages;
    }
    
    /**
     * This method gets all the messages sent by a user, it uses the username that is taken in as a parameter
     * to find the messages associated with that user, it then adds these messages to an ArrayList and returns this list.
     * @param username is the username of the user we want to get messages for
     * @return returns an ArrayList of Message objects.
     */
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
                // Make a message object for the current message
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
        // returning the messages ArrayList
        return messages;
    }

    /**
     * This method is used to get all messages that have been sent to date through the application, it gets all
     * entries in the message table and stores them in Message objects which are then stored in an ArrayList and then returned
     * @return returns an ArrayList of Message objects.
     */
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
                // Make a message object for the current message
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
        // returning the messages ArrayList
        return messages;
    }

    /**
     * This method is used to find a single message in the database, it takes in a single parameter, a user id in
     * the form of an int which is used to find the corresponding message in the database, once found it stores the 
     * result in a message object and returns this object.
     * @param messageId is the id of the message we wish to find
     * @return returns a Message object containing information corresponding with the message id. 
     */
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
                // Make a message object for the current message
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
        // returning the mesages ArrayList
        return m;
    }

    /**
     * This method is used to get all messages that where sent on a particular date, it takes in a Date variable
     * which holds the date information to be used in the database query, once queried the results are stored in Message
     * objects which are then Stored in an ArrayList and returned.
     * @param date is the date in which we wish to find messages for
     * @return returns an ArrayList of Message objects
     */
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
                // Make a message object for the current message
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
        // returning the messages ArrayList
        return messages;
    }

    /**
     * This method is used to get all the messages that have not been read by a particular user, an id of a user
     * is passed as a parameter in the form of an int and is used to query the database to see if the user in question has any 
     * unread mail in their messages, if they do the results are stored in Message objects which are then stored in an ArrayList and
     * returned.
     * @param userId is the id of the user we wish to find unread messages for.
     * @return returns an ArrayList of unread messages.
     */
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
                // Make a message object for the current message
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
        // returning the messages ArrayList
        return messages;
    }

    /**
     * This method is used to send a message to the forum, it takes in a Message object as a parameter
     * @param message is the message to be posted to the forum
     * @return returns true or false value stating if the message was successfully sent or not
     */
    @Override
    public boolean sendMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is used to receive any messages that are posted to the forum, it takes no parameters and returns nothing
     */
    @Override
    public void recieveMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is used to send a private message to another user, it takes two parameters a user id in the form of an int which represents
     * the user that wishes to send the message, and a Message object that contains all the information needed to sent the message, it uses this
     * information to insert a message into the database then it must update the UserMessage table to ensure integrity of data but firstly needs
     * the id assigned to the message just written to the database, once gotten it creates a new entry in the UserMessage table to show that the user linked 
     * with the user id is the one who sent this message, it then returns a value of true if all queries executed successfully otherwise it returns false
     * @param userId is the id of the user that wishes to send the message
     * @param message is the message object to be sent
     * @return returns a true or false value depending if the message was sent successfully
     */
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
                // Make a message object for the current message
                

                
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
