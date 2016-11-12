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
public class QuoteBuilder {
    /**
     * Construit une quote a partir d'un ResultSet
     * @param rs : ResultSet
     * @return La saison construite a partir de rs
     * @throws SQLException 
     */
    static public Quote buildFromRS (ResultSet rs) throws SQLException{
        int quoteId = rs.getInt("quoteid");
        int episodeId = rs.getInt("episodeid");
        int characterId = rs.getInt("characterid");
        String quoteText = rs.getString("quotetext");        
        String quoteNote = rs.getString("quotenote"); 
        
        String characterName = rs.getString("charactername");
        String episodeName = rs.getString("namevf");
        if(episodeName.isEmpty()){
            episodeName = rs.getString("nomvo");
        }
        
        return new Quote(quoteId, episodeId, characterId, quoteText, quoteNote,episodeName,characterName);
    }
}
