/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import business.User;
import java.util.ArrayList;

/**
 *
 * @author Megatronus
 */
public interface UserDAOInterface {
    public User getUserById(int userId);
    public User login (String userName, String password);
    public void logout();
    public boolean register(User newUser);
    public ArrayList<User> getAllUsers();
}
