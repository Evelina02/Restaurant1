package by.restaurant.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.Role;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;


public class UserDAOImplTest {

    private static ConnectionPool pool = ConnectionPool.getInstance();
       
    private DAOFactory daoFactory = DAOFactory.getInstance();
	private UserDAO userDAO = daoFactory.getUserDAO();
	

//тесты на отриц и исключенни
	
    @BeforeClass
    public static void createPool() throws ConnectionPoolException, SQLException, IOException {
        
    	 pool.initPoolData();
    }

    @AfterClass
    public static void destroyPool() throws ConnectionPoolException, SQLException, IOException {
        
    	 pool.dispose();
    }

//	@Test
//	public void testAddUser() throws DAOException, ConnectionPoolException {
//		
//		String login = "julya_testUser";
//		String password = "julya";
//		String email = "julya_testUser@mail.ru";
//		String address = "Минск";
//
//		User user = new User(login, BCrypt.hashpw(password, BCrypt.gensalt()), Role.CLIENT, email, address);
//		int status = userDAO.addUser(user);
//		Assert.assertEquals(1, status);
//	}


//	@Test
//	public void testGetUserById() throws DAOException {
//
//		int id = 70;
//		
//		String login = "julya_testUser";
//		String password = "julya";
//		String email = "julya_testUser@mail.ru";
//		String address = "Минск";
//		User user = new User(id, login, BCrypt.hashpw(password, BCrypt.gensalt()), Role.CLIENT, email, address);
//
//		Assert.assertEquals(user, userDAO.getUserById(id));
//	}

	@Test
	public void testIsExist() throws DAOException {

		String login = "Vanya_testUser";
		Assert.assertTrue(userDAO.isExist(login));	
	}
	
	@Test
	public void testIsExistNegative() throws DAOException {

		String login = "Vanya_test";
		Assert.assertFalse(userDAO.isExist(login));	
	}
//
//	@Test
//	public void testChangePassword() throws DAOException {
//		
//		int id = 84;
//		String password = "anya";
//		String newPassword = "anya1";
//		
//		userDAO.changePassword(id, password, newPassword);
//		
//		Assert.assertEquals(1, userDAO.changePassword(id, password, newPassword));
//	}

	@Test
	public void testBanUser() throws DAOException {

		int id = 70;	
		Assert.assertEquals(1, userDAO.banUser(id));		
	}

	@Test
	public void testUnbanUser() throws DAOException {

		int id = 70;	
		Assert.assertEquals(1, userDAO.unbanUser(id));
	}
	
//не
//	@Test
//	public void testIsBanned() throws DAOException {
//
//		String login = "Vanya_testUser";
//		Assert.assertFalse(userDAO.isBanned(login));
//		
//	}



	@Test
	public void testGetEmailByLogin() throws DAOException {

		String login = "Vanya_testUser";
		String expectedEmail = "Vanya_testUser@mail.ru";
		
		Assert.assertEquals(expectedEmail, userDAO.getEmailByLogin(login));
	}

	@Test
	public void testGetEmailById() throws DAOException {
		
		int id = 70;
		String expectedEmail = "Vanya_testUser@mail.ru";
		
		Assert.assertEquals(expectedEmail, userDAO.getEmailById(id));

	}

	@Test
	public void testGetIdByLogin() throws DAOException {
		
		String login = "Vanya_testUser";
		int expectedId = 70;
		
		Assert.assertEquals(expectedId, userDAO.getIdByLogin((login)));
	}



}
