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
public class EpisodeBuilder {
    
    /**
     * Construit un Episode a partir d'un resultSet
     * @param rs : ResultSet
     * @return episode construit a partir de rs
     * @throws SQLException 
     */
    static public Episode buildFromRS (ResultSet rs) throws SQLException{
        int episodeId = rs.getInt("episodeid");
        int productionCode = rs.getInt("productionCode");
        int seasonId = rs.getInt("seasonId");
        String nameVO = rs.getString("namevo");
        String nameVF = rs.getString("namevf");
        String plot = rs.getString("plot");
        int indexInSeason = rs.getInt("indexinseason");
        return new Episode(episodeId, productionCode, seasonId, nameVO, nameVF, plot, indexInSeason);
    }
}
