/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class UserBuilder {
    /**
     * Construit un User a partir d'un resultSet
     * @param rs : ResultSet
     * @return User construit a partir de rs
     * @throws SQLException 
     */
    static public User buildFromRS (ResultSet rs) throws SQLException{
        String userLogin = rs.getString("userlogin");
        String userPassword = rs.getString("userpassword");
        String privilege = rs.getString("privilege");
        
        return new User(userLogin, userPassword, privilege);
    }
}
