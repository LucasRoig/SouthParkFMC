/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import static fr.athome.southparkfmc.dataaccess.SeasonDaoTest.getDataSource;
import fr.athome.southparkfmc.model.ActiveTag;
import fr.athome.southparkfmc.model.Apparition;
import fr.athome.southparkfmc.model.Episode;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class EpisodeDaoTest {
    private static DataSource myDataSource;
    private static Connection myConnection;
    private static EpisodeDao dao;
    
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
        dao = manager.getEpisodeDao();
    }
    
    @After
    public void tearDown() throws SQLException {
        myConnection.close();
    }

    /**
     * Test of find method, of class EpisodeDao.
     */
    @Test
    public void testFind() throws Exception {
        Episode actual = dao.find(1);
        assertTrue(actual.getProductionCode() == 101 && actual.getSeasonId() == 1);
        assertTrue(actual.getNameVF().equals("episode1") && actual.getPlot().equals("plot"));
    }

    /**
     * Test of findAll method, of class EpisodeDao.
     */
    @Test
    public void testFindAll() throws Exception {
        assertTrue(dao.findAll().size() == 2);
    }

    /**
     * Test of create method, of class EpisodeDao.
     */
    @Test
    public void testCreate() throws SQLException {
        int productionCode = 103;
        int SeasonId = 1;
        String nameVO = "episode3";
        String nameVF = "";
        String plot = "";
        int indexInSeason = 3;
        dao.create(productionCode, SeasonId, nameVO, nameVF, plot, indexInSeason);
        assertTrue(dao.findAll().size() == 3);
    }

    /**
     * Test of create method, of class EpisodeDao.
     */
    @Test
    public void testUpdate() throws SQLException {
        int episodeId = 1;
        int productionCode = 101;
        int SeasonId = 1;
        String nameVO = "Cartman gets an anal probe";
        String nameVF = "Cartman a une sonde anale";
        String plot = "";
        int indexInSeason = 1;
        dao.update(episodeId, productionCode, SeasonId, nameVO, nameVF, plot, indexInSeason);
        Episode actual = dao.find(1);
        assertTrue(actual.getNameVF().equals(nameVF) && actual.getNameVO().equals(nameVO));
    }

    /**
     * Test of delete method, of class EpisodeDao.
     */
    @Test
    public void testDelete() throws SQLException {
        int episodeId = 1;
        dao.delete(episodeId);
        assertTrue(dao.findAll().size() == 1);
    }
    
    @Test
    public void testFindTag() throws SQLException{
        List<ActiveTag> actual = dao.findActiveTag(1);
        assertTrue(actual.size() == 1);
    }
    
    @Test
    public void testFindApparition() throws SQLException{
        List<Apparition> actual = dao.findApparition(1);
        assertTrue(actual.size() == 1);
    }

    /**
     * Test of addTag method, of class EpisodeDao.
     */
    @Test
    public void testAddTag() throws SQLException {
        int episodeId = 1;
        int tagId = 2;
        String note = "";
        dao.addTag(episodeId, tagId, note);
        assertEquals(dao.findActiveTag(episodeId).size(), 2);
    }

    /**
     * Test of addApparition method, of class EpisodeDao.
     */
    @Test
    public void testAddApparition() throws SQLException {
        int episodeId = 2;
        int characterId = 2;
        int roleId = 2;
        String note = "Une petite apparition";
        dao.addApparition(episodeId, characterId, roleId, note);
        assertEquals(dao.findApparition(episodeId).size(), 1);        
    }

    /**
     * Test of removeTag method, of class EpisodeDao.
     */
    @Test
    public void testRemoveTag() throws SQLException {
        int episodeId = 1;
        int tagId = 1;
        dao.removeTag(episodeId, tagId);
        assertEquals(dao.findActiveTag(episodeId).size(), 0);
    }

    /**
     * Test of removeApparition method, of class EpisodeDao.
     */
    @Test
    public void testRemoveApparition() throws SQLException {
        int episodeId = 1;
        int characterId = 1;
        dao.removeApparition(episodeId, characterId);
        assertEquals(dao.findApparition(episodeId).size(), 0);
    }
    
    @Test
    public void testFindQuote()throws SQLException{
        assertEquals(2,dao.findQuotes(1).size());
    }
    
}
