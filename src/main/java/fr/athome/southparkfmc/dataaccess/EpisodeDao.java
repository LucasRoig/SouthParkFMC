/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.ActiveTag;
import fr.athome.southparkfmc.model.ActiveTagBuilder;
import fr.athome.southparkfmc.model.Apparition;
import fr.athome.southparkfmc.model.ApparitionBuilder;
import fr.athome.southparkfmc.model.Episode;
import fr.athome.southparkfmc.model.EpisodeBuilder;
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
public class EpisodeDao {
    DataSource dataSource;

    public EpisodeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retourne l'episode recherche
     * @param episodeId : id de l'episode recherche
     * @return L'episode ayant pour id episodeId s'il existe
     * @throws SQLException
     */
    public Episode find(int episodeId) throws SQLException{
        Episode result = null;

        String sql = "SELECT * FROM episode WHERE episodeid=?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, episodeId);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = EpisodeBuilder.buildFromRS(rs);
        }
        rs.close();
        stmt.close();
        connection.close();

        return result;
    }

    /**
     * Retourne tous les episodes existants
     * @return La liste des episodes presents dans la base
     * @throws SQLException
     */
    public List<Episode> findAll() throws SQLException{
        List<Episode> result = new ArrayList<>();

        String sql = "SELECT * FROM episode";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
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
     * Retourne tous les tags actifs sur l'episode
     * @return La liste de tous les tags actifs sur l'episode
     * @param episodeId
     * @throws SQLException
     */
    public List<ActiveTag> findActiveTag(int episodeId) throws SQLException{
        List<ActiveTag> result = new ArrayList<>();

        String sql = "SELECT * FROM ("
                + "(SELECT * FROM tagged WHERE episodeid=?)AS tagged "
                + "JOIN tag ON(tagged.tagid=tag.tagid))AS tag "
                + "JOIN "
                + "(SELECT episodeid,nameVF,nameVO FROM episode WHERE episodeId = ?)AS episode "
                + "ON episode.episodeId=tag.episodeid";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, episodeId);
        stmt.setInt(2, episodeId);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(ActiveTagBuilder.buildFromRS(rs));
        }
        rs.close();
        stmt.close();
        connection.close();

        return result;
    }

    /**
     * Retourne toutes les apparitions d'un episode
     * @return La liste de toutes les apparitions d'un episode
     * @param episodeId
     * @throws SQLException
     */
    public List<Apparition> findApparition(int episodeId) throws SQLException{
        List<Apparition> result = new ArrayList<>();

        String sql = "SELECT * FROM " +
                    "(((SELECT * FROM apparition WHERE episodeId = ?)AS apparition " +
                    "JOIN " +
                    "(SELECT characterId,characterName FROM characters)AS characters " +
                    "ON apparition.characterid = characters.characterid)AS apparition " +
                    "JOIN " +
                    "(SELECT episodeid, namevf, namevo FROM episode WHERE episodeid = ?)AS episode " +
                    "ON apparition.episodeid = episode.episodeid) AS apparition " +
                    "JOIN " +
                    "(SELECT roleId,roleName FROM role)AS role " +
                    "ON role.roleId = apparition.roleid ";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, episodeId);
        stmt.setInt(2, episodeId);

        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(ApparitionBuilder.buildFromRS(rs));
        }
        rs.close();
        stmt.close();
        connection.close();

        return result;
    }

        /**
     * Retourne les quotes d'un episode
     * @param episodeId
     * @return La liste des quotes d'un episode
     * @throws SQLException
     */
    public List<Quote> findQuotes(int episodeId) throws SQLException{
        List<Quote> result = new ArrayList<>();

        String sql = "SELECT * FROM " +
                    "((SELECT * FROM quote WHERE episodeid=?)AS quote " +
                    "LEFT JOIN " +
                    "(SELECT characterid,charactername FROM characters)AS characters " +
                    "ON quote.characterid=characters.characterid)AS quote " +
                    "JOIN " +
                    "(SELECT episodeid,namevo,namevf FROM episode WHERE episodeid=?)AS episode " +
                    "ON quote.episodeid=episode.episodeid";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, episodeId);
        stmt.setInt(2, episodeId);

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
     * Ajoute un nouvel episode dans la base
     * @param productionCode
     * @param seasonId
     * @param nameVO
     * @param nameVF
     * @param plot
     * @param indexInSeason
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public boolean create(int productionCode, int seasonId, String nameVO, String nameVF, String plot, int indexInSeason){
        boolean result = false;

        String sql = "INSERT INTO episode (productioncode,seasonid,namevo,namevf,plot,indexinseason) VALUES(?,?,?,?,?,?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,productionCode);
        stmt.setInt(2,seasonId);
        stmt.setString(3,nameVO);
        stmt.setString(4,nameVF);
        stmt.setString(5,plot);
        stmt.setInt(6,indexInSeason);
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
     * Update un episode de la base
     * @param episodeId
     * @param productionCode
     * @param seasonId
     * @param nameVO
     * @param nameVF
     * @param plot
     * @param indexInSeason
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public boolean update(int episodeId, int productionCode, int SeasonId, String nameVO, String nameVF, String plot, int indexInSeason){
        boolean result = false;

        String sql = "UPDATE episode SET productioncode=?,seasonId=?,nameVO=?,nameVF=?,plot=?,indexInSeason=? WHERE episodeId=?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,productionCode);
        stmt.setInt(2,SeasonId);
        stmt.setString(3,nameVO);
        stmt.setString(4,nameVF);
        stmt.setString(5,plot);
        stmt.setInt(6,indexInSeason);
        stmt.setInt(7, episodeId);
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
     * Suppression d'un episode de la base
     * @param episodeId
     * @return true si la suppresion s'est terminee sans erreur, false sinon
     */
    public boolean delete(int episodeId){
        boolean result = false;

        String sql = "DELETE FROM episode WHERE episodeid = ?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, episodeId);
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
     * Ajout d'une relation entre un episode et un tag (ActiveTag)
     * @param episodeId
     * @param tagId
     * @param note
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public boolean addTag(int episodeId, int tagId, String note){
        boolean result = false;
        String sql = "INSERT INTO tagged VALUES(?,?,?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,tagId);
        stmt.setInt(2,episodeId);
        stmt.setString(3,note);
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

    public boolean updateActiveTag(int episodeId, int tagId, String note){
        boolean result = false;
        String sql = "UPDATE tagged SET taggedNote=? WHERE (episodeId = ? AND tagId = ?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,note);
        stmt.setInt(2,episodeId);
        stmt.setInt(3,tagId);
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
     * Ajout d'une relation entre un episode et un character (Apparition)
     * @param episodeId
     * @param characterId
     * @param roleId
     * @param note
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public boolean addApparition(int episodeId, int characterId,int roleId, String note){
        boolean result = false;

        String sql = "INSERT INTO apparition VALUES(?,?,?,?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,characterId);
        stmt.setInt(2,episodeId);
        stmt.setInt(3,roleId);
        stmt.setString(4,note);
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
     * Ajout d'une relation entre un episode et un character (Apparition)
     * @param episodeId
     * @param characterId
     * @param roleId
     * @param note
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public boolean updateApparition(int episodeId, int characterId,int roleId, String note){
        boolean result = false;

        String sql = "UPDATE apparition SET roleId = ?, apparitionNote = ? WHERE (episodeId = ? AND characterId = ?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,roleId);
        stmt.setInt(3,episodeId);
        stmt.setInt(4,characterId);
        stmt.setString(2,note);
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
     * Suppression d'une relation entre un episode et un tag
     * @param episodeId
     * @param tagId
     * @return true si la suppresion s'est terminee sans erreur, false sinon
     */
    public boolean removeTag(int episodeId,int tagId){
        boolean result = false;

        String sql = "DELETE FROM tagged WHERE (episodeid = ? AND tagid=?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, episodeId);
        stmt.setInt(2, tagId);
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
     * Suppression d'une relation entre un episode et un character
     * @param episodeId
     * @param characterId
     * @return true si la suppresion s'est terminee sans erreur, false sinon
     */
    public boolean removeApparition(int episodeId,int characterId){
        boolean result = false;

        String sql = "DELETE FROM apparition WHERE (episodeid = ? AND characterid=?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, episodeId);
        stmt.setInt(2, characterId);
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
