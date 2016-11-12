/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.Tag;
import fr.athome.southparkfmc.model.TagBuilder;
import fr.athome.southparkfmc.model.User;
import fr.athome.southparkfmc.model.UserBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Lucas
 */
public class UserDao {
    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Retourne un user recherche
     * @param userLogin : login de l'user recherche
     * @return L'user ayant pour login userLogin s'il existe
     * @throws SQLException 
     */
    public User find(String userLogin) throws SQLException{
        User result = null;
        
        String sql = "SELECT * FROM users WHERE userLogin=?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, userLogin);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = UserBuilder.buildFromRS(rs);
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
}
