/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.ActiveTag;
import fr.athome.southparkfmc.model.ActiveTagBuilder;
import fr.athome.southparkfmc.model.Tag;
import fr.athome.southparkfmc.model.TagBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lucas
 */
public class TagDao {
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TagDao.class);
    private DataSource dataSource;

    public TagDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retourne un tag recherche
     * @param tagId : id du tag recherche
     * @return Le tag ayant pour id tagId s'il existe
     * @throws SQLException
     */
    public Tag find(int tagId) throws SQLException{
        Tag result = null;

        String sql = "SELECT * FROM tag WHERE tagid=?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, tagId);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = TagBuilder.buildFromRS(rs);
        }
        rs.close();
        stmt.close();
        connection.close();

        return result;
    }

    /**
     * Retourne tous les tags existants
     * @return La liste des tags presents dans la base
     * @throws SQLException
     */
    public List<Tag> findAll() throws SQLException{
        List<Tag> result = new ArrayList<>();

        String sql = "SELECT * FROM tag";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(TagBuilder.buildFromRS(rs));
        }
        rs.close();
        stmt.close();
        connection.close();

        return result;
    }

    /**
     * Retourne une liste d'activeTag representants les utilisations d'un tag
     * @param tagId
     * @return liste d'activeTag
     */
    public List<ActiveTag> findUses(int tagId) throws SQLException{
         List<ActiveTag> result = new ArrayList<>();

        String sql = "SELECT * FROM ("
                + "(SELECT * FROM tagged WHERE tagid=?)AS tagged "
                + "JOIN tag ON(tagged.tagid=tag.tagid))AS tag "
                + "JOIN "
                + "(SELECT episodeid,nameVF,nameVO FROM episode)AS episode "
                + "ON episode.episodeId=tag.episodeid";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, tagId);
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
     * Ajoute un nouveau tag dans la base
     * @param tagName
     * @return l'id du tag créé, -1 si erreur
     */
    public int create(String tagName){
        int result = -1;

        String sql = "INSERT INTO tag (tagname) VALUES(?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, tagName);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        result = rs.getInt("tagId");
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            LOGGER.error("SQL Error with parameter tagName = " + tagName, e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection", e);
            }
        }
        return result;
    }

    /**
     * Update d'un tag de la base
     * @param tagId
     * @param tagName
     * @return true si l'update s'est termine sans erreur, false sinon
     */
    public boolean update(int tagId, String tagName){
        boolean result = false;

        String sql = "UPDATE tag SET tagname = ? WHERE tagid = ?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, tagName);
        stmt.setInt(2, tagId);
        stmt.executeUpdate();
        result = true;
        stmt.close();
        connection.close();
        } catch (SQLException e) {
           LOGGER.error("SQL Error with parameter tagName = " + tagName + " tagId = " + tagId, e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection", e);
            }
        }
        return result;
    }

     /**
     * Suppression d'un tag de la base
     * @param tagId
     * @return true si la suppresion s'est terminee sans erreur, false sinon
     */
    public boolean delete(int tagId){
        boolean result = false;

        String sql = "DELETE FROM tag WHERE tagid = ?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, tagId);
        stmt.executeUpdate();
        result = true;
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            LOGGER.error("SQL Error with parameter tagId = " + tagId, e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection", e);
            }
        }
        return result;
    }
    
    public List<Tag> findByTagName(String name){
        List<Tag> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tag WHERE tagname LIKE ?";
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%"+name+"%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result.add(TagBuilder.buildFromRS(rs));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            LOGGER.error("SQL Error with parameter name = " + name, ex);
        }
        return result;
    }
}
