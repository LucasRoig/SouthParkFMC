/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import fr.athome.southparkfmc.model.Season;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Before;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class SeasonDaoTest {
    private static DataSource myDataSource;
    private static Connection myConnection;
    private static SeasonDao dao;
    
    @Before
    public void setUp() throws IOException, SQLException, SqlToolError {
        // On crée la connection vers la base de test "in memory"
	myDataSource = getDataSource();
	myConnection = myDataSource.getConnection();
	// On initialise la base avec le contenu d'un fichier de test
	String sqlFilePath = "src/main/resources/testdata.sql";
	SqlFile sqlFile = new SqlFile(new File(sqlFilePath));
	sqlFile.setConnection(myConnection);
	sqlFile.execute();
	sqlFile.closeReader();
        DaoManager manager = new DaoManager(getDataSource());
        dao = manager.getSeasonDao();
    }
    
    @After
    public void tearDown() throws SQLException{
        myConnection.close();
    }

    /**
     * Test of find method, of class SeasonDao.
     */
    @org.junit.Test
    public void testFind() throws Exception {
        Season actual = dao.find(1);
        int expectedId = 1;
        int expectedYear = 1997;
        assertTrue(actual.getSeasonId() == expectedId && actual.getDiffusionYear() == expectedYear);
    }

    /**
     * Test of findAll method, of class SeasonDao.
     */
    @org.junit.Test
    public void testFindAll() throws Exception {
        assertTrue(dao.findAll().size() == 3);
    }

    /**
     * Test of findEpisodeInSeason method, of class SeasonDao.
     */
    @org.junit.Test
    public void testFindEpisodeInSeason() throws Exception {
        assertTrue(dao.findEpisodeInSeason(1).size() == 2);
    }

    /**
     * Test of create method, of class SeasonDao.
     */
    @org.junit.Test
    public void testCreate() {
        try {
            dao.create(4, 2000);
            assertTrue(dao.findAll().size() == 4);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Test of update method, of class SeasonDao.
     */
    @org.junit.Test
    public void testUpdate() {
        try {
            dao.update(1, 2016);
            Season s = dao.find(1);
            assertTrue(s.getDiffusionYear() == 2016);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Test of delete method, of class SeasonDao.
     */
    @org.junit.Test
    public void testDelete() {
        try{
            dao.delete(1);
            assertTrue(dao.findAll().size() == 2);
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }
    
    @org.junit.Test
    public void testCount() throws SQLException {
        assertEquals(3, dao.countSeason());
    }
    
    public static DataSource getDataSource(){
        org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
	ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
	ds.setUser("sa");
	ds.setPassword("sa");
	return ds;
    }
}
