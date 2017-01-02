package DAO;

import business.Message;
import Interfaces.MessageDAOInterface;
import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                m.setReceiver(rs.getString("receiver"));
                m.setTimeSent(rs.getTimestamp("timeSent"));
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
            String query = "select * from message, usermessage, users where message.messageId = usermessage.messageId and usermessage.userId = users.userId and users.username = (?) and message.inForum = false;";
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
                m.setReceiver(rs.getString("receiver"));
                m.setTimeSent(rs.getTimestamp("timeSent"));
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
                m.setReceiver(rs.getString("receiver"));
                m.setTimeSent(rs.getTimestamp("timeSent"));
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
                m.setReceiver(rs.getString("receiver"));
                m.setTimeSent(rs.getTimestamp("timeSent"));
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
    public ArrayList<Message> getMessageByDate(Timestamp date) {
        
        // ArrayList<Message> declaration for storing all the user's messages
        ArrayList<Message> messages = new ArrayList();

        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE timeSent = (?)";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setTimestamp(1, date);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current message
                m = new Message();

                
                m.setMessageId(rs.getInt("messageId"));
                m.setMessageContent(rs.getString("message"));
                m.setReceiver(rs.getString("receiver"));
                m.setTimeSent(rs.getTimestamp("timeSent"));
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

        UserDAO userDAO = new UserDAO();
        User u = userDAO.getUserById(userId);
        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE receiver = (?) and messageRead = false";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setString(1, u.getUserName());

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current message
                Message m = new Message();

                
                m.setMessageId(rs.getInt("messageId"));
                m.setMessageContent(rs.getString("message"));
                m.setReceiver(rs.getString("receiver"));
                m.setTimeSent(rs.getTimestamp("timeSent"));
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
            String query = "INSERT INTO MESSAGE (message, receiver, messageRead, timeSent, inForum) VALUES (?,?,?,?,?);";

            // Preparing the statement for the insertion
            ps = con.prepareStatement(query);
            
            
            ps.setString(1, message.getMessageContent());
            ps.setString(2, message.getReceiver());
            ps.setBoolean(3, message.isRead());
            ps.setTimestamp(4, message.getTimeSent());
            ps.setBoolean(5, message.isInForum());
            
             // Executing the query and storing the response
            // if i>0 the information was inserted
            // if i==0 no information inserted into the database
            int i = ps.executeUpdate();
            
            
            query = "SELECT * FROM Message WHERE message.message = ? and message.receiver = ? and message.timeSent = ?";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setString(1, message.getMessageContent());
            ps.setString(2, message.getReceiver());
            ps.setTimestamp(3, message.getTimeSent());

            // executing the query
            rs = ps.executeQuery();

            Message mess = new Message();
            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current message
                

                
                mess.setMessageId(rs.getInt("messageId"));
                mess.setMessageContent(rs.getString("message"));
                mess.setReceiver(rs.getString("receiver"));
                mess.setTimeSent(rs.getTimestamp("timeSent"));
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
    @Override
    public boolean addForumMessage(int userId, Message newMessage) {
        // Objects for stablishing the connection
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Checking that the message object received is not null
        if (newMessage == null) {
            return false;
        }

        try {
            // Getting the connection
            con = getConnection();

            // Preparing the query
            String query = "INSERT INTO MESSAGE (message, receiver, messageRead, timeSent, inForum) VALUES (?,?,?,?,?);";

            // Preparing the statement for the insertion
            ps = con.prepareStatement(query);
            
            
            ps.setString(1, newMessage.getMessageContent());
            ps.setString(2, newMessage.getReceiver());
            ps.setBoolean(3, newMessage.isRead());
            ps.setTimestamp(4, newMessage.getTimeSent());
            ps.setBoolean(5, newMessage.isInForum());
            
             // Executing the query and storing the response
            // if i>0 the information was inserted
            // if i==0 no information inserted into the database
            int i = ps.executeUpdate();
            
            
            query = "SELECT * FROM Message WHERE message.message = ? and message.receiver = ? and message.timeSent = ?";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setString(1, newMessage.getMessageContent());
            ps.setString(2, newMessage.getReceiver());
            ps.setTimestamp(3, newMessage.getTimeSent());

            // executing the query
            rs = ps.executeQuery();
            
            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current message
                newMessage.setMessageId(rs.getInt("messageId"));
               
            }
                        
            query = "INSERT INTO USERMESSAGE (userId, messageId) VALUES (?, ?);";

            // Preparing the statement for the insertion
            ps = con.prepareStatement(query);
            
            ps.setInt(1, userId);
            ps.setInt(2, newMessage.getMessageId());

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
    @Override
    public int getForumMessageId(Message newMessage) {
        // Objects for stablishing the connection
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // int to represent the id of the message.
        int id = -1;
        // Checking that the message object received is not null
        if (newMessage == null) {
            return id;
        }

        try {
            // Getting the connection
            con = getConnection();
            // Preparing the query
            String query = "SELECT * FROM Message WHERE message.message = ? and message.receiver = ? and message.timeSent = ?";
            ps = con.prepareStatement(query);

            // preparing the query
            ps.setString(1, newMessage.getMessageContent());
            ps.setString(2, newMessage.getReceiver());
            ps.setTimestamp(3, newMessage.getTimeSent());

            // executing the query
            rs = ps.executeQuery();
            
            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current message
                id = rs.getInt("messageId");
            }
            // catching any possible exception
        } catch (SQLException e) {
            return -1;
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
                return 0;
            }
        return id;    
        }
    }
    
    /**
     * This method returns an arraylist of message objects. With the requirement
     * that the inForum boolean value stored in each object is equal to true.
     * @return An arraylist of messages that are meant for the forum chat
     */
    @Override
    public ArrayList<Message> getAllForumMessages(){
        ArrayList<Message> messages = new ArrayList();
        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Message WHERE Message.inForum = true";
            ps = con.prepareStatement(query);

            // executing the query
            rs = ps.executeQuery();

            // This while loop stores all the messages retrieved from the database into the ArrayList
            while (rs.next()) {
                // Make a message object for the current message
                m = new Message();

                
                m.setMessageId(rs.getInt("messageId"));
                m.setMessageContent(rs.getString("message"));
                m.setReceiver(rs.getString("receiver"));
                m.setTimeSent(rs.getTimestamp("timeSent"));
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
        return messages;
    }
    
    /**
     * This method takes in an ArrayList of Message objects and set all those objects
     * isRead attribute as read in the database if the intended receiver was that particular user 
     * and returns a true or false value depending on if the all the messages where set as read or not
     * @param messages is an ArrayList of Message objects
     * @param username is the username of the person viewing at the messages
     * @return returns a true or false value depending if the objects isRead attribute were all changed successfully
     */
    @Override
    public boolean setMessagesAsRead(ArrayList<Message> messages, String username)
    {
        
        try {
            // requesting a connection
            con = getConnection();

            for(int i = 0; i<messages.size(); i++)
                {
                    if(username.equalsIgnoreCase(m.getReceiver()))
                    {
                        // creating the query
                        String query = "UPDATE MESSAGE SET messageRead = true WHERE MessageId = (?)";
                        ps = con.prepareStatement(query);

                        Message m = messages.get(i);

                        ps.setInt(1, m.getMessageId());
                        // executing the query
                        int j = ps.executeUpdate();

                        if(j<=0)
                        {
                            return false;
                        }
                    }
                }
            // Catching any possible exception
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
        return true;
    }

    
}
