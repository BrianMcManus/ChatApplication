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
 *
 * @author Megatronus
 */
public class DAO {
    public Connection getConnection(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/chatapp";
        String username = "root";
        String password = "";
        try
        {
            // Load the database driver
            Class.forName( driver ) ;

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password) ;

            return conn;
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }
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
