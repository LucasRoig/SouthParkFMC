/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.Quote;
import fr.athome.southparkfmc.model.QuoteBuilder;
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
public class QuoteDao {
    DataSource dataSource;

    public QuoteDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
     /**
     * Retourne une quote recherchee
     * @param quoteId : id de la quote recherchee
     * @return La quote ayant pour id quoteId si elle existe
     * @throws SQLException 
     */
    public Quote find(int quoteId) throws SQLException{
        Quote result = null;
        
        String sql = "SELECT * FROM " +
                    "((SELECT * FROM quote WHERE quoteid=?)AS quote " +
                    "LEFT JOIN " +
                    "(SELECT characterid,charactername FROM characters)AS characters " +
                    "ON quote.characterid=characters.characterid)AS quote " +
                    "JOIN " +
                    "(SELECT episodeid,namevo,namevf FROM episode)AS episode " +
                    "ON quote.episodeid=episode.episodeid";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, quoteId);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = QuoteBuilder.buildFromRS(rs);
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Retourne toutes les quote existantes
     * @return La liste des quote presentes dans la base
     * @throws SQLException 
     */
    public List<Quote> findAll() throws SQLException{
        List<Quote> result = new ArrayList<>();
        
        String sql = "SELECT * FROM " +
                    "((SELECT * FROM quote)AS quote " +
                    "LEFT JOIN " +
                    "(SELECT characterid,charactername FROM characters)AS characters " +
                    "ON quote.characterid=characters.characterid)AS quote " +
                    "JOIN " +
                    "(SELECT episodeid,namevo,namevf FROM episode)AS episode " +
                    "ON quote.episodeid=episode.episodeid";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(QuoteBuilder.buildFromRS(rs));
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    
     /**
     * Ajoute une nouvelle quote dans la base
     * @param episodeId
     * @param characterId
     * @param quoteText
     * @param quoteNote
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public boolean create(int episodeId,int characterId,String quoteText, String quoteNote){
        boolean result = false;
        
        String sql = "INSERT INTO quote (episodeid,characterid,quotetext,quotenote) VALUES(?,?,?,?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,episodeId);
        stmt.setInt(2,characterId);
        stmt.setString(3, quoteText);
        stmt.setString(4, quoteNote);
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
     * Update une quote dans la base
     * @param quoteId
     * @param episodeId
     * @param characterId
     * @param quoteText
     * @param quoteNote
     * @return true si l'update s'est terminee sans erreur, false sinon
     */
    public boolean update(int quoteId,int episodeId,int characterId,String quoteText, String quoteNote){
        boolean result = false;
        
        String sql = "UPDATE quote SET episodeId=?,characterId=?,quotetext=?,quotenote=? WHERE quoteid=?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,episodeId);
        stmt.setInt(2,characterId);
        stmt.setString(3, quoteText);
        stmt.setString(4, quoteNote);
        stmt.setInt(5, quoteId);
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
     * Suppression d'une quote de la base
     * @param quoteId
     * @return true si la suppresion s'est terminee sans erreur, false sinon
     */
    public boolean delete(int quoteId){
        boolean result = false;
        
        String sql = "DELETE FROM quote WHERE quoteid = ?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, quoteId);
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
