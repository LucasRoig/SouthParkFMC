/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.Episode;
import fr.athome.southparkfmc.model.EpisodeBuilder;
import fr.athome.southparkfmc.model.Role;
import fr.athome.southparkfmc.model.RoleBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
public class RoleDao {
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(QuoteDao.class);
    DataSource dataSource;

    public RoleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Retourne le role
     * @param roleId
     * @return Le role ayant pour id roleId s'il existe
     * @throws SQLException 
     */
    public Role find(int roleId) throws SQLException{
        Role result = null;
        
        String sql = "SELECT * FROM role WHERE roleid=?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, roleId);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = RoleBuilder.buildFromRS(rs);
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Retourne tous les roles existants
     * @return La liste des roles presents dans la base
     * @throws SQLException 
     */
    public List<Role> findAll() throws SQLException{
        List<Role> result = new ArrayList<>();
        
        String sql = "SELECT * FROM role";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(RoleBuilder.buildFromRS(rs));
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
}
