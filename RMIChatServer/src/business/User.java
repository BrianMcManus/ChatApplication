/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Megatronus
 * 
 * This class is used to define what attributes a user object is comprised of,
 * it implements Serializable in order to allow the object to be passed over sockets
 */
public class User implements Serializable{
    //The id used to identify the user
    private int userId;
    //The username, password and email the user used upon registration
    private String userName, password, email;
    //States weather the user is currently logged in or not
    private boolean loggedIn;

    //Empty constructor
    public User() {
    }
    
    //Parameterized constructor using users username and password
    public User(String username, String nPassword)
    {
        userName = username;
        password = nPassword;
    }

    //Parameterized constructor using users username, password and email
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    //Setters and Getters
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    //Get hascode of the object
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.userId;
        hash = 97 * hash + Objects.hashCode(this.userName);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + (this.loggedIn ? 1 : 0);
        return hash;
    }

    //Check equivalency of 2 user objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    //Output the attributes of user object to string values
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email + ", loggedIn=" + loggedIn + '}';
    }

    
    
}
