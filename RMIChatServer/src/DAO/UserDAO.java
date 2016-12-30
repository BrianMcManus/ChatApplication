package DAO;

import business.User;
import Interfaces.UserDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to control the data coming from and being posted to
 * the User table in the database, it extends the DAO class to be able to access
 * the connection methods within the DAO class, it implements the UserDAOInterface in
 * order to ensure all methods are implemented and signatures match that which is defined
 * in the interface.
 * @author Megatronus
 */
public class UserDAO extends DAO implements UserDAOInterface {

    private User user;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * This method is used to get a users details by accessing the User table and 
     * extracting the information correlating with the user id that is taken in as a parameter, it
     * then stores this information into a user object and returns it.
     * @param userId is the id of the user we wish to get details for
     * @return returns a User object filled with the users information
     */
    @Override
    public User getUserById(int userId) {
        
        //Creates a new user object
        user = new User();
        
        try {
            //Get connection to the database
            con = getConnection();
            
            //Create query 
            String query = "SELECT * FROM users WHERE userId = ?";
            //Create prepared statement
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            //Execute the query
            rs = ps.executeQuery();
            //While we get results back, pack results into a User object
            while (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(rs.getBoolean("loggedIn"));
            }
            //Catch any possible exceptions
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        //Return the user object
        return user;
    }
    
    /**
     * This method is used to get a users information by querying the database for the user information
     * correlating with the username that is provided as a parameter to this method, It gains a connection to the database
     * and queries the database for the users information, the resulting information is then packed into a User object and returned.
     * @param username is the username of the user that we wish to get the information for
     * @return returns a User object that contains the users information
     */
    @Override
    public User getUserByUsername(String username) {
        
        //Create a new User object
        user = new User();
        
        try {
            //Get a connection to the database
            con = getConnection();
            //Create a query
            String query = "SELECT * FROM users WHERE username = ?";
            //Create a prepared statement
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            //Execute the query
            rs = ps.executeQuery();
            //While we get results pack the into the User object
            while (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(rs.getBoolean("loggedIn"));
            }
            //Catch any possible exceptions
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        //Return the user object containing the users information
        return user;
    }

    /**
     * This method is used to log a user into the system, it takes in two strings, a username and 
     * a password which are then used to query the user table in the database,
     * if it get results these results are packed into a User object 'loggedIn' attribute set to true
     * so the user is considered to be logged in, it then returns the User object filled with the users details.
     * @param userName is the username of the user trying to log into the system
     * @param password is the password for the user trying to log into the system
     * @return returns a User object with the users information contained within it
     */
    @Override
    public User login(String userName, String password) {
        //Create a new User
        user = new User();
        
        try {
            //Get a connection to the database
            con = getConnection();
            //Create a query
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            //Create prepared statement
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            //Execute the query
            rs = ps.executeQuery();
            //While we get results pack them into a User object
            while (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(true);
            }
            
            //Mark the user as logged in
            
            //Create a query
            query = "UPDATE Users SET loggedIn = true WHERE username = ?";
            
            //Create prepared statement
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            
             //Execute the update
            ps.executeUpdate();

            //Catch any possible exceptions
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        //Return the User object
        return user;
    }

    /**
     * This method is used to log a user out of the system, it takes no parameters
     * and  returns nothing, it sets the users
     * User object to be null so the users details are removed from the system
     */
    @Override
    public void logout() {
        
        try {
            //Get a connection to the database
            con = getConnection();
            //Create a query
            String query = "UPDATE USER SET loggedIn = true WHERE username = ?";
            //Create prepared statement
            ps = con.prepareStatement(query);
            ps.setString(1, user.getUserName());
            //Execute the update
            ps.executeUpdate();
            
            //Catch any possible exceptions
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                e.printStackTrace();
            }
        }
 
        //If the users not already logged out
        if (user != null) {
            //log user out
            user = null;
        }
    }

    /**
     * This method is used to register a new user to the application, it takes in a User object containing the
     * new users information, it then tries to add this user to the database, if the username is already taken or the
     * email is not valid the method will return a value of false stating that the registration process has failed, 
     * otherwise it will add the new user to the system
     * @param newUser is the details of the user trying to register
     * @return returns a true or false value stating if the registration was successful or not
     */
    @Override
    public boolean register(User newUser) {
        
        //Set the user's values equal to that of the user taken in as a parameter
        user = newUser;
        
        try {
            //Get a connection to the database
            con = getConnection();
            //Create a query
            String query = "INSERT INTO users(username, email, password, loggedIn) VALUES(?,?,?,false)";
            //Create a prepared statement
            ps = con.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            //Execute the query
            ps.executeUpdate();
            //Return true if the user was added successfully
            return true;
            
            //Catch any possible exceptions
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        //Return false if anything goes wrong
        return false;
    }

    /**
     * This method is used to get all users currently registered to the application, it takes no parameters 
     * but returns an ArrayList of User objects
     * The method queries the database for all users and upon getting results it packs the results into User
     * objects and places these objects into an ArrayList
     * @return returns an ArrayList of User objects
     */
    @Override
    public ArrayList<User> getAllUsers() {     
        
        //Create a new ArrayList to store users
        ArrayList<User> userList = new ArrayList();
        
        try {
            //Get a connection to the database
            con = getConnection();
            //Create a query
            String query = "SELECT * FROM users";
            //Create a prepareed statement
            ps = con.prepareStatement(query);
            //Execute the query
            rs = ps.executeQuery();
            //While we get results pack them into User objects and add them to the list
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(rs.getBoolean("loggedIn"));
                userList.add(user);
            }
            //Catch any possible exceptions
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                System.out.println("SQL exception");
                return null;
            }
        }
        //Return the ArrayList of users
        return userList;
    }

}
