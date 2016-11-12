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
public class RoleBuilder {
    /**
     * Construit un Role a partir d'un ResultSet
     * @param rs : ResultSet
     * @return La saison construite a partir de rs
     * @throws SQLException 
     */
    static public Role buildFromRS (ResultSet rs) throws SQLException{
        int roleId = rs.getInt("roleid");       
        String roleName = rs.getString("rolename"); 
        
        return new Role(roleId, roleName);
    }
}
