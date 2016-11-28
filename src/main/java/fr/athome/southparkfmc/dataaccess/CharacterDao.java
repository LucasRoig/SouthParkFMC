/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.Apparition;
import fr.athome.southparkfmc.model.ApparitionBuilder;
import fr.athome.southparkfmc.model.CharacterBuilder;
import fr.athome.southparkfmc.model.CharacterEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Lucas
 */
public class CharacterDao {
    
    private DataSource dataSource;

    public CharacterDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Retourne un character recherche
     * @param characterId : id du character recherche
     * @return Le character ayant pour id characterId s'il existe
     * @throws SQLException 
     */
    public CharacterEntity find(int characterId) throws SQLException{
        CharacterEntity result = null;
        
        String sql = "SELECT * FROM characters WHERE characterid=?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, characterId);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            result = CharacterBuilder.buildFromRS(rs);
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Retourne tous les character existants
     * @return La liste des character presents dans la base
     * @throws SQLException 
     */
    public List<CharacterEntity> findAll() throws SQLException{
        List<CharacterEntity> result = new ArrayList<>();
        
        String sql = "SELECT * FROM characters";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            result.add(CharacterBuilder.buildFromRS(rs));
        }      
        rs.close();
        stmt.close();
        connection.close();
        
        return result;
    }
    /**
     * Retourne une liste d'apparitions representants les apparitions d'un
     * personnage dans un episode.
     * @param characterId
     * @return liste d'apparition
     */
    public List<Apparition> findApparitions(int characterId) throws SQLException{
         List<Apparition> result = new ArrayList<>();
        
        String sql = "SELECT * FROM " +
                    "(((SELECT * FROM apparition WHERE characterid = ?)AS apparition " +
                    "JOIN " +
                    "(SELECT characterId,characterName FROM characters WHERE characterId=?)AS characters " +
                    "ON apparition.characterid = characters.characterid)AS apparition " +
                    "JOIN " +
                    "(SELECT episodeid, namevf, namevo FROM episode)AS episode " +
                    "ON apparition.episodeid = episode.episodeid) AS apparition " +
                    "JOIN " +
                    "(SELECT roleId,roleName FROM role)AS role " +
                    "ON role.roleId = apparition.roleid ";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, characterId);
        stmt.setInt(2,characterId);
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
     * Ajoute un nouveau character dans la base
     * @param characterName
     * @return true si l'ajout s'est termine sans erreur, false sinon
     */
    public int create(String characterName){
        int result = -1;
        
        String sql = "INSERT INTO characters (charactername) VALUES(?)";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, characterName);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        result = rs.getInt("characterId");
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
     * Update d'un character de la base
     * @param characterId
     * @param characterName
     * @param background
     * @return true si l'update s'est termine sans erreur, false sinon
     */
    public boolean update(int characterId, String characterName, String background){
        boolean result = false;
        
        String sql = "UPDATE characters SET charactername=?, background=? WHERE characterid=?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, characterName);
        stmt.setString(2, background);
        stmt.setInt(3,characterId);
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
     * Suppression d'un character de la base
     * @param characterId
     * @return true si la suppresion s'est terminee sans erreur, false sinon
     */
    public boolean delete(int characterId){
        boolean result = false;
        
        String sql = "DELETE FROM characters WHERE characterid = ?";
        Connection connection= null;
        try {
        connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, characterId);
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
