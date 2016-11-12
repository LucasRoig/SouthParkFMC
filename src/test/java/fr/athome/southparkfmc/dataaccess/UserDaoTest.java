/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.dataaccess;

import static fr.athome.southparkfmc.dataaccess.SeasonDaoTest.getDataSource;
import fr.athome.southparkfmc.model.User;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
public class UserDaoTest {
    private static DataSource myDataSource;
    private static Connection myConnection;
    private static UserDao dao;
    
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
        dao = manager.getUserDao();
    }
    
    @After
    public void tearDown() throws SQLException {
        myConnection.close();
    }
    
    /**
     * Test of find method, of class UserDao.
     */
    @Test
    public void testFind() throws Exception {
        String userLogin = "user";
        User result = dao.find(userLogin);
        assertEquals("pass", result.getUserPassword());
        assertEquals("user", result.getPrivilege());
    }
    
}
