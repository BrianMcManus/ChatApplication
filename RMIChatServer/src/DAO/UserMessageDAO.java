package DAO;

import business.Message;
import business.User;
import Interfaces.UserMessageInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used to control the data coming from and being posted to the UserMessage table within the 
 * database, it extends the DAO class to be able to access connection methods for the database which are 
 * within the DAO class, it implements the UserMessageInterface in order to ensure all methods are implemented
 * and signatures are correct.
 * @author Brian
 */
public class UserMessageDAO extends DAO implements UserMessageInterface{
    
    // objects for establishing the connection
        private Connection con = null;
        private PreparedStatement ps = null;
        private ResultSet rs = null;
        

    /**
     * This method is used to find the user that sent a particular message, it takes in a message id in the form of an int,
     * it uses this id to identify the user by searching the UserMessage table in the database and finding the corresponding user
     * to the message id, once a result is found it packs the users information into a User object and returns this object.
     * @param messageId is the id of the message that we wish to know who sent.
     * @return returns a User object with the senders information.
     */
    @Override
    public User findUserByMessage(int messageId) {
        //Create a new User object
         User user = new User();
         
        try {
            // requesting a connection
            con = getConnection();

            // creating the query
            String query = "SELECT * FROM Users, UserMessage WHERE Users.UserId = UserMessage.UserId and UserMessage.MessageId = (?)";
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
            // Catching any possible exceptions
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
        // returning the User oobject
        return user;
    }

    /**
     * This method is used to get all the messages that a specific user has sent, it takes in a single parameter,
     * a user id in the form of an int to represent the user we wish to find messages for,
     * The method uses this id to query the database and get all the users mail, when it gets results it packs 
     * them into message objects and adds them to an ArrayList and returns this ArrayList
     * @param userId is the id of the user we wish to find messages for
     * @return returns an ArrayList of Message objects 
     */
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
                m.setReceiver(rs.getString("receiver"));
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
