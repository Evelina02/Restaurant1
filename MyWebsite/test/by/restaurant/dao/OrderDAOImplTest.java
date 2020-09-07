package by.restaurant.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import by.restaurant.bean.Order;
import by.restaurant.bean.constant.DeliveryType;
import by.restaurant.bean.constant.OrderState;
import by.restaurant.bean.constant.PaymentType;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class OrderDAOImplTest {


    private static ConnectionPool pool = ConnectionPool.getInstance();
       
    private DAOFactory daoFactory = DAOFactory.getInstance();
	private OrderDAO orderDAO = daoFactory.getOrderDAO();
	
	
	@BeforeClass
	public static void createPool() throws ConnectionPoolException, SQLException, IOException {

		pool.initPoolData();
	}

	@AfterClass
	public static void destroyPool() throws ConnectionPoolException, SQLException, IOException {

		pool.dispose();
	}



	@Test
	public void testCancelOrder() throws DAOException {

		int id = 85;
		int status = orderDAO.cancelOrder(id);
		
		Assert.assertEquals(1, status);	
	}

	@Test
	public void testDoOrder() throws DAOException {

		int id = 85;
		int status = orderDAO.doOrder(id);
		
		Assert.assertEquals(1, status);	
	}

	@Test
	public void testCloseOrder() throws DAOException {

		int id = 85;
		int status = orderDAO.closeOrder(id);
		
		Assert.assertEquals(1, status);	
	}

	@Test
	public void testGetIdOrderByOrderTime() throws DAOException {

		int id = 84;
		String orderTime = "2020-07-07 16:40";
		Assert.assertEquals(id, orderDAO.getIdOrderByOrderTime(orderTime));

	}

//	@Test
//	public void testAddOrder() {
//
//		String orderTime = "2020-07-25 17:00";
//		String delveryTime = "2020-07-27 20:00";
//		PaymentType paymentType = PaymentType.CREDIT_CARD;
//		DeliveryType deliveryType = DeliveryType.BY_COURIER;
//		OrderState state = OrderState.IN_PROCESS;
//		
//		Set<Dish> dishes = new HashSet<Dish>();
//		
//		Dish dish = new Dish("ќвощи-гриль", 4.90, "https://cdn.carte.by/assets/2018/02/06/ovoschi-gril---jpg_550x342:whitepadding15_6d506_convert.jpg", 
//				Category.SNACKS, "130 гр", ingredients);
//		
//		dishes.add();
//		
//		
//		Order order = new Order()
//		
//		int status = orderDAO.addOrder(order, idUser);
//		Assert.assertEquals(1, status);
//	}
}
