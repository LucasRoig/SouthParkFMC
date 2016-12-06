/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.SearchCharacterResult;
import fr.athome.southparkfmc.model.SearchEpisodeResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Lucas
 */
public class SearchDao {
    private DataSource datasource;

    public SearchDao(DataSource datasource) {
        this.datasource = datasource;
    }
    
    public List<SearchEpisodeResult> searchEpisode(String text) throws SQLException{
        List<SearchEpisodeResult> result = new ArrayList<>();
        String sql = "SELECT episodeid , namevo,namevf, ts_rank(document, to_tsquery('french', ?))as score" +
                        " FROM episode_search_index" +
                        " WHERE document @@ to_tsquery('french',?)" +
                        " ORDER BY ts_rank(document, to_tsquery('french',?)) DESC;";
        Connection connection = this.datasource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, text);
        stmt.setString(2, text);
        stmt.setString(3, text);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            int episodeid = rs.getInt("episodeid");
            String nameVF = rs.getString("namevf");
            String nameVO = rs.getString("namevo");
            float score = rs.getFloat("score");
            result.add(new SearchEpisodeResult(episodeid, nameVF, nameVO, score));
        }
        rs.close();
        stmt.close();
        connection.close();

        return result;
    }
    
    public List<SearchCharacterResult> searchCharacter(String text) throws SQLException{
        List<SearchCharacterResult> result = new ArrayList<>();
        String sql = "SELECT characterid , charactername, ts_rank(document, to_tsquery('french', ?))as score" +
                        " FROM character_search_index" +
                        " WHERE document @@ to_tsquery('french',?)" +
                        " ORDER BY ts_rank(document, to_tsquery('french',?)) DESC;";
        Connection connection = this.datasource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, text);
        stmt.setString(2, text);
        stmt.setString(3, text);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            int characterId = rs.getInt("characterId");
            String characterName = rs.getString("characterName");
            float score = rs.getFloat("score");
            result.add(new SearchCharacterResult(characterId, characterName,score));
        }
        rs.close();
        stmt.close();
        connection.close();

        return result;
    }
    
}
