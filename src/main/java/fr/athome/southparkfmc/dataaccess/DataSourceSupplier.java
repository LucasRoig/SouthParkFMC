/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    
/**
 *
 * @author Lucas
 */
public class DataSourceSupplier {
    static private Logger LOGGER = LoggerFactory.getLogger(DataSourceSupplier.class);
    
    static private PGSimpleDataSource dataSource;
    
    static private void initDataSource(){
        InputStream input = DataSourceSupplier.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        try {
            prop.load(input);       
        } catch (IOException ex) {
            LOGGER.error("Unable to find config.properties");
        }
        String server = prop.getProperty("server");
        String database = prop.getProperty("database");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        int port = Integer.parseInt(prop.getProperty("port"));
        dataSource = new PGSimpleDataSource();
        dataSource.setServerName(server);
        dataSource.setDatabaseName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setPortNumber(port);
    }
    
    static public DataSource getDataSource() {
        if(dataSource == null){
            initDataSource();
        }
        return dataSource;
    }     
    
}
