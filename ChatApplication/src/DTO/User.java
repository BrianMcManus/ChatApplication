/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Megatronus
 */
public class User {
    private int userId;
    private String userName, password;
    private boolean loggedIn;

    public User() {
    }

    public User(int userId, String userName, String password, boolean loggedIn) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.loggedIn = loggedIn;
    }

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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.userId;
        hash = 73 * hash + Objects.hashCode(this.userName);
        hash = 73 * hash + Objects.hashCode(this.password);
        hash = 73 * hash + (this.loggedIn ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
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
        if (this.loggedIn != other.loggedIn) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + ", loggedIn=" + loggedIn + '}';
    }
    
}
