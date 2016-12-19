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
public class UserDAO implements UserDAOInterface{
    private User user;

    @Override
    public User getUserById(int userId) {Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        user = new User();

        try {
            con = getConnection();
            String query = "Select * from classes where classId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                cClass.setName(rs.getString("className"));
                cClass.setDesc(rs.getString("classDesc"));
                cClass.setRole(rs.getString("classRole"));
                cClass.setAlignment(rs.getString("classAlignment"));
                cClass.setHitDie(rs.getString("classHitDie"));
                cClass.setSkills(rs.getString("classSkills"));
                cClass.setFeatures(rs.getString("classFeatures"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacterClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public User login(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean register(User newUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loggedIn(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
