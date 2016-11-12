/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
    
/**
 *
 * @author Lucas
 */
public class DataSourceSupplier {
    static private final String  SERVER = "82.245.68.210";
    static private final String USER = "southpark";
    static private final String DATABASE_NAME = "south-park";
    //TODO : fill password
    static private final String PASSWORD = "";
    static private final int PORT = 5432;
    
    static public DataSource getDataSource() {
        PGSimpleDataSource ds = new org.postgresql.ds.PGSimpleDataSource();
        ds.setServerName(SERVER);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
        ds.setPortNumber(PORT);
        return ds;
    }     
}
