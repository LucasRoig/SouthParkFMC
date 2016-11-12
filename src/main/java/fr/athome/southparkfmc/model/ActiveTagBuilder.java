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
public class ActiveTagBuilder {
    
    /**
     * Construit un ActiveTag a partir d'un resultSet
     * @param rs : ResultSet
     * @return episode construit a partir de rs
     * @throws SQLException 
     */
    static public ActiveTag buildFromRS (ResultSet rs) throws SQLException{
        int tagId = rs.getInt("tagid");
        int episodeId = rs.getInt("episodeid");
        String note = rs.getString("taggednote");
        String tagName = rs.getString("tagname");
        String name = rs.getString("namevf");
        if(name.isEmpty()){
            name = rs.getString("namevo");
        }
        
        return new ActiveTag(tagId, episodeId, note, tagName, name);
    }
    
}
