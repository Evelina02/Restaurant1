package by.restaurant.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.Category;
import by.restaurant.bean.constant.Role;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;


public class DishDAOImplTest {

    private static ConnectionPool pool = ConnectionPool.getInstance();
       
    private DAOFactory daoFactory = DAOFactory.getInstance();
	private DishDAO dishDAO = daoFactory.getDishDAO();
	

    @BeforeClass
    public static void createPool() throws ConnectionPoolException, SQLException, IOException {
        
    	 pool.initPoolData();
    }

    @AfterClass
    public static void destroyPool() throws ConnectionPoolException, SQLException, IOException {
        
    	 pool.dispose();
    }

//	@Test
//	public void testAddDish() throws DAOException {
//		
//		Set<String> ingredients = new HashSet<String>();
//		ingredients.add("Цукини");
//		ingredients.add("Баклажаны");
//		ingredients.add("Шампиньоны");
//		ingredients.add("Перец сладкий");
//		ingredients.add("Помидоры");
//
//		Dish dish = new Dish("Овощи-гриль", 4.90, "https://cdn.carte.by/assets/2018/02/06/ovoschi-gril---jpg_550x342:whitepadding15_6d506_convert.jpg", 
//				Category.SNACKS, "130 гр", ingredients);
//
//		int status = dishDAO.addDish(dish);
//		Assert.assertEquals(1, status);
//	}

//	@Test
//	public void testSearchDishByPartOfName() {
//		fail("Not yet implemented");
//	}
//

	@Test
	public void testDeleteDish() throws DAOException {

		int id = 74;
		int status = dishDAO.deleteDish(id);
		Assert.assertEquals(1, status);	
	}

	@Test
	public void testUpdateDish() throws DAOException {

		int id = 74;
		String picture = "https://cdn.carte.by/assets/2018/02/06/ovoschi-gril---jpg_550x342:whitepadding15_6d506_convert.jpg";
		double price = 5.0;
		String amount = "130 гр";
		int status = dishDAO.updateDish(id, picture, price, amount);
		
		Assert.assertEquals(1, status);
	}

}
