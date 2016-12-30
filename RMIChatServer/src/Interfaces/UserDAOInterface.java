package Interfaces;

import business.User;
import java.util.ArrayList;

/**
 * This class is used to define what methods must be implemented into the UserDAO class,
 * it defines each method and its parameter and return types.
 * @author Megatronus
 */
public interface UserDAOInterface {
    
    /**
     * This method is used to get a users details by using the user id passed to the method and accessing the database
     * to get the details of the user corresponding the id passed, once the method gets a result it packs these results into
     * a User object which is then returned.
     * 
     * @param userId is the id of the user we wish to get details for
     * @return returns a User object with the users details
     */
    public User getUserById(int userId);
    
    /**
     * This method is used to log a user into the system using their username and password to do so,
     * it takes in the username and password and searches the database for a corresponding user, if 
     * it gets a result it packs this result into a User object and returns it.
     * @param userName
     * @param password
     * @return 
     */
    public User login (String userName, String password);
    
    /**
     * This method is used to log the user out of the the system, it takes no parameters and returns nothing
     */
    public void logout();
    
    /**
     * This method is used to register a new user into the system, it takes in a single parameter, a User object which contains
     * the users email, username and password, the method uses these attributes to check the database to see if the username is 
     * already taken and if not it registers the user and the method returns true otherwise it does not register the user and returns 
     * false.
     * @param newUser a User object that contains the users username, password and email.
     * @return returns a true or false value depending if the user was registered successfully or not
     */
    public boolean register(User newUser);
    
    /**
     * This method accesses the database the retrieves all the users that are currently registered with the application,
     * it takes no parameters but accesses the User table and extracts all the users details and stores the results into
     * User objects which are stored in an ArrayList and returned
     * @return returns an ArrayList of User objects
     */
    public ArrayList<User> getAllUsers();
    
    /**
     * This method is used to get a user from the database,
     * it takes a string as a parameter, a username and gets the corresponding users details and stores the results into
     * a User object which is then returned
     * @param username is the username of the user we wish to get details for 
     * @return returns a user object containing the details of the user we wish to find
     */
    public User getUserByUsername(String username);

}
