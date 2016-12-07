/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import static fr.athome.southparkfmc.dataaccess.SeasonDaoTest.getDataSource;
import fr.athome.southparkfmc.model.Apparition;
import fr.athome.southparkfmc.model.CharacterEntity;
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
public class CharacterDaoTest {
    private static DataSource myDataSource;
    private static Connection myConnection;
    private static CharacterDao dao;
    
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
        dao = manager.getCharacterDao();
    }
    
    @After
    public void tearDown() throws SQLException {
        myConnection.close();
    }

    /**
     * Test of find method, of class CharacterDao.
     */
    @Test
    public void testFind() throws Exception {
        CharacterEntity result = dao.find(1);
        assertTrue(result.getCharacterName().equals("Randy"));
    }

    /**
     * Test of findAll method, of class CharacterDao.
     */
    @Test
    public void testFindAll() throws Exception {
        List<CharacterEntity> result = dao.findAll();
        assertTrue(result.size() == 2);
        
    }

    /**
     * Test of findApparitions method, of class CharacterDao.
     */
    @Test
    public void testFindApparitions() throws Exception {
        int characterId = 1;
        List<Apparition> result = dao.findApparitions(characterId);
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getEpisodeId() == 1);
    }

    /**
     * Test of create method, of class CharacterDao.
     */
    @Test
    public void testCreate() throws SQLException {
        String characterName = "Chef";
        dao.create(characterName);
        assertEquals(dao.findAll().size(), 3);
    }

    /**
     * Test of update method, of class CharacterDao.
     */
    @Test
    public void testUpdate() throws SQLException {
        int characterId = 1;
        String characterName = "Randy Marsh";
        String background = "Le papa de stan";
        dao.update(characterId, characterName, background);
        CharacterEntity result = dao.find(1);
        assertEquals(characterName, result.getCharacterName());
        assertEquals(background, result.getBackground());
    }

    /**
     * Test of delete method, of class CharacterDao.
     */
    @Test
    public void testDelete() throws SQLException {
        int characterId = 1;
        dao.delete(characterId);
        assertEquals(1, dao.findAll().size());
    }
    
    @Test
    public void testFindByName() throws SQLException{
        String name = "Ran";
        List<CharacterEntity> res = dao.findByCharacterName(name);
        assertEquals(1,res.size());
        assertTrue(res.get(0).getCharacterName().equals("Randy"));
    }
    
}
