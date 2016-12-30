/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to get and release the connection to the database
 * @author Megatronus
 */
public class DAO {
    
    /**
     * This method is used to gain a connection with the database, it takes no parameters but returns
     * a connection object for connecting to the database.
     * @return returns the connection object representing the connection to the database
     */
    public Connection getConnection(){
        //Driver for connection
        String driver = "com.mysql.jdbc.Driver";
        //Location of database
        String url = "jdbc:mysql://localhost:3306/chatapp";
        //Username of owner
        String username = "root";
        //Password to database
        String password = "";
        try
        {
            // Load the database driver
            Class.forName( driver ) ;

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password) ;

            //Return the connection
            return conn;
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * This method is used to release a connection to the database, it takes in a connection object 
     * representing the connection to the database that is to be closed and returns nothing.
     * @param con is the connection that is to be closed
     */
    public void freeConnection(Connection con)
    {
        try
        {
            if (con != null)
            {// If there is a connection to close, close it.
                con.close();
                con = null;
            }
        }
        catch (SQLException e) {// If something went wrong when talking to the db, print an error and shut down.
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
