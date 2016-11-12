/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import javax.sql.DataSource;

/**
 *
 * @author Lucas
 */
public class DaoManager {
    private DataSource dataSource;
    
    public DaoManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public SeasonDao getSeasonDao(){
        return new SeasonDao(this.dataSource);
    }
    
    public TagDao getTagDao(){
        return new TagDao(this.dataSource);
    }
    
    public CharacterDao getCharacterDao(){
        return new CharacterDao(this.dataSource);
    }
    
    public UserDao getUserDao(){
        return new UserDao(this.dataSource);
    }
    
    public QuoteDao getQuoteDao(){
        return new QuoteDao(this.dataSource);
    }
    
    public EpisodeDao getEpisodeDao(){
        return new EpisodeDao(this.dataSource);
    }
    
    public RoleDao getRoleDao(){
        return new RoleDao(this.dataSource);
    }
}
