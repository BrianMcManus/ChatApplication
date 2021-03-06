/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import Interfaces.UserDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Megatronus
 */
public class UserDAO extends DAO implements UserDAOInterface {

    private User user;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public User getUserById(int userId) {
        user = new User();
        try {
            con = getConnection();
            String query = "SELECT * FROM user WHERE userId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(rs.getBoolean("loggedIn"));
            }
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
        return user;
    }

    @Override
    public User login(String userName, String password) {
        user = new User();
        try {
            con = getConnection();
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(true);
            }
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
        return user;
    }

    @Override
    public void logout() {
        if (user != null) {
            user = null;
        }
    }

    @Override
    public boolean register(User newUser) {
        user = newUser;
        try {
            con = getConnection();
            String query = "INSERT INTO user(username, email, password) VALUES(?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            rs = ps.executeQuery();
            return true;
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
        return false;
    }

    @Override
    public boolean loggedIn(int userId) {
        user = new User();
        try {
            con = getConnection();
            String query = "SELECT * FROM user WHERE userId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoggedIn(rs.getBoolean("loggedIn"));
            }
            if(user.isLoggedIn()==true){
                return true;
            } else{
                return false;
            }
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
        return false;
    }

}
