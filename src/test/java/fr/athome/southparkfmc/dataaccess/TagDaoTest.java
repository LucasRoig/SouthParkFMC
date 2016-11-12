/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import static fr.athome.southparkfmc.dataaccess.SeasonDaoTest.getDataSource;
import fr.athome.southparkfmc.model.ActiveTag;
import fr.athome.southparkfmc.model.Tag;
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
public class TagDaoTest {
    private static DataSource myDataSource;
    private static Connection myConnection;
    private static TagDao dao;    
    
    
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
        dao = manager.getTagDao();
    }
    
    @After
    public void tearDown() throws SQLException {
        myConnection.close();
    }

    /**
     * Test of find method, of class TagDao.
     */
    @Test
    public void testFind() throws Exception {
        int tagId = 1;
        Tag result = dao.find(tagId);
        assertEquals("Panda", result.getTagName());
    }

    /**
     * Test of findAll method, of class TagDao.
     */
    @Test
    public void testFindAll() throws Exception {
        List<Tag> result = dao.findAll();
        assertEquals(2, result.size());
    }

    /**
     * Test of findUses method, of class TagDao.
     */
    @Test
    public void testFindUses() throws Exception {
        int tagId = 1;
        List<ActiveTag> result = dao.findUses(tagId);
        assertEquals(2, result.size());
    }

    /**
     * Test of create method, of class TagDao.
     */
    @Test
    public void testCreate() throws SQLException {
        String tagName = "cornichon";
        dao.create(tagName);
        List result = dao.findAll();
        assertEquals(3, result.size());
    }

    /**
     * Test of update method, of class TagDao.
     */
    @Test
    public void testUpdate() throws SQLException {
        int tagId = 1;
        String tagName = "Pandas";
        dao.update(tagId, tagName);
        assertEquals(tagName, dao.find(1).getTagName());
    }

    /**
     * Test of delete method, of class TagDao.
     */
    @Test
    public void testDelete() throws SQLException {
        int tagId = 1;
        dao.delete(tagId);
        List result = dao.findAll();
        assertEquals(1, result.size());
    }
    
}
