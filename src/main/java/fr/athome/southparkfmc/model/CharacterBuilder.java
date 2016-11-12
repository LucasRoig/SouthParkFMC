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
public class CharacterBuilder {
    /**
     * Construit un Episode a partir d'un resultSet
     * @param rs : ResultSet
     * @return episode construit a partir de rs
     * @throws SQLException 
     */
    static public CharacterEntity buildFromRS (ResultSet rs) throws SQLException{
        int characterId = rs.getInt("characterid");
        String characterName = rs.getString("charactername");
        String background = rs.getString("background");
        
        return new CharacterEntity(characterId, characterName, background);
    }
}
