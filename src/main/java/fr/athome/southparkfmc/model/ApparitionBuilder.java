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
public class ApparitionBuilder {
    /**
     * Construit une Apparition a partir d'un resultSet
     * @param rs : ResultSet
     * @return episode construit a partir de rs
     * @throws SQLException
     */
    static public Apparition buildFromRS (ResultSet rs) throws SQLException{
        int roleId = rs.getInt("roleid");
        int characterId = rs.getInt("characterid");
        int episodeId = rs.getInt("episodeid");
        String note = rs.getString("apparitionNote");
        String characterName = rs.getString("characterName");
        String episodeName = rs.getString("nameVF");
        if(episodeName.isEmpty()){
            episodeName = rs.getString("namevo");
        }
        String role = rs.getString("roleName");
      
        return new Apparition(characterId, episodeId, role, note, characterName, episodeName,roleId);
    }
}
