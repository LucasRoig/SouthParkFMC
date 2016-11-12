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
public class TagBuilder {
    /**
     * Construit un Tag a partir d'un resultSet
     * @param rs : ResultSet
     * @return episode construit a partir de rs
     * @throws SQLException 
     */
    static public Tag buildFromRS (ResultSet rs) throws SQLException{
        int tagId = rs.getInt("tagid");
        String tagName = rs.getString("tagname");
        
        return new Tag(tagId, tagName);
    }
}
