/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import static fr.athome.southparkfmc.dataaccess.SeasonDaoTest.getDataSource;
import fr.athome.southparkfmc.model.Quote;
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
public class QuoteDaoTest {
    private static DataSource myDataSource;
    private static Connection myConnection;
    private static QuoteDao dao; 
    
    @Before
    public void setUp() throws SQLException, IOException, SqlToolError {
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
        dao = manager.getQuoteDao();
    }
    @After
    public void tearDown() throws SQLException {
        myConnection.close();
    }

    /**
     * Test of find method, of class QuoteDao.
     */
    @Test
    public void testFind() throws Exception {
        int quoteId = 0;
        Quote result = dao.find(quoteId);
        assertEquals("Et je rentre à ma maison", result.getQuoteText());
        assertEquals(2, result.getCharacterId());
        assertEquals("Cartman", result.getCharacterName());
        assertEquals(1, result.getEpisodeId());
        assertEquals("episode1", result.getEpisodeName());
        assertEquals("le grand classique", result.getQuoteNote());
    }

    /**
     * Test of findAll method, of class QuoteDao.
     */
    @Test
    public void testFindAll() throws Exception {    
        List<Quote> result = dao.findAll();
        assertEquals(2, result.size());
    }

    /**
     * Test of createWithCharacter method, of class QuoteDao.
     */
    @Test
    public void testCreate() throws SQLException {
        int episodeId = 2;
        int characterId = 1;
        String quoteText = "bli blu";
        String quoteNote = "";
        dao.createWithCharacter(episodeId, characterId, quoteText, quoteNote);
        assertEquals(3, dao.findAll().size());
    }

    /**
     * Test of update method, of class QuoteDao.
     */
    @Test
    public void testUpdate() throws SQLException {
        int quoteId = 0;
        int episodeId = 1;
        int characterId = 1;
        String quoteText = "reblyu";
        String quoteNote = "";
        dao.update(quoteId, episodeId, characterId, quoteText, quoteNote);
        Quote actual = dao.find(quoteId);
        assertEquals(episodeId, actual.getEpisodeId());
        assertEquals(characterId, actual.getCharacterId());
        assertEquals(quoteText, actual.getQuoteText());
    }

    /**
     * Test of delete method, of class QuoteDao.
     */
    @Test
    public void testDelete() throws SQLException {
        int quoteId = 0;
        dao.delete(quoteId);
        assertEquals(1, dao.findAll().size());
    }
    
}
