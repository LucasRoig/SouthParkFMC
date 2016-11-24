/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.Episode;
import fr.athome.southparkfmc.model.EpisodeBuilder;
import fr.athome.southparkfmc.model.Season;
import fr.athome.southparkfmc.model.SeasonBuilder;
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
public class SeasonDao {
    private DataSource dataSource;
    
    public SeasonDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Retourne le nombre total de saisons.
     * @return le nombre total de saisons.
     */
    public int countSeason() throws SQLException{
        int result = 0;
        
        String sql = "select count(*) as nb from season";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = rs.getInt("nb");
        }
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    /**
     * Retourne une saison recherchee
     * @param seasonId : id de la saison recherchee
     * @return La saison ayant pour id seasonId si elle existe
     * @throws SQLException 
     */
    public Season find(int seasonId) throws SQLException{
        Season result = null;
        
        String sql = "SELECT * FROM season WHERE seasonid=?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, seasonId);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = SeasonBuilder.buildFromRS(rs);
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Retourne la liste de toutes les saisons
     * @return la liste de toutes les saisons
     * @throws SQLException 
     */
    public List<Season> findAll() throws SQLException{
        List<Season> result = new ArrayList<>();
        
        String sql = "SELECT * FROM season";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(SeasonBuilder.buildFromRS(rs));
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Retourne la liste de tous les episodes d'une saison
     * @param seasonid : int
     * @return la liste de tous les episodes d'une saison
     * @throws SQLException 
     */
    public List<Episode> findEpisodeInSeason(int seasonid) throws SQLException{
        List<Episode> result = new ArrayList<>();
        
        String sql = "SELECT * FROM episode WHERE seasonid = ?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, seasonid);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(EpisodeBuilder.buildFromRS(rs));
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Ajoute une nouvelle saison dans la base
     * @param seasonId
     * @param diffusionYear
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public boolean create(int seasonId, int diffusionYear){
        boolean result = false;
        
        String sql = "INSERT INTO season VALUES(?,?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, seasonId);
        stmt.setInt(2, diffusionYear);
        stmt.executeUpdate();
        result = true;
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * Update d'une saison de la base
     * @param seasonId
     * @param diffusionYear
     * @return true si l'update s'est termine sans erreur, false sinon
     */
    public boolean update(int seasonId, int diffusionYear){
        boolean result = false;
        
        String sql = "UPDATE season SET diffusionYear = ? WHERE seasonid = ?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(2, seasonId);
        stmt.setInt(1, diffusionYear);
        stmt.executeUpdate();
        result = true;
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  
        return result;
    }
    
    /**
     * Supprime une saison de la base
     * @param seasonId
     * @return true si la suppression s'est terminee sans erreur, false sinon.
     */
        public boolean delete(int seasonId){
        boolean result = false;
        
        String sql = "DELETE FROM season WHERE seasonid = ?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, seasonId);
        stmt.executeUpdate();
        result = true;
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  
        return result;
    }
}
