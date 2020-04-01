package by.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.restaurant.bean.Order;
import by.restaurant.bean.User;
import by.restaurant.bean.util.DeliveryType;
import by.restaurant.bean.util.OrderState;
import by.restaurant.bean.util.PaymentType;
import by.restaurant.bean.util.Role;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.OrderDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class OrderDAOImpl implements OrderDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	private Connection connection;
	private Statement statement;
	private PreparedStatement ps;
	private ResultSet rs;
	
    private static final String SELECT_DISHES_OF_ORDER = 
    		"select * from dish as d "
    	    + "join order_dish od on od.id_dish=d.id_dish "
    	    + "where id_order=?";
    		
    		
    
	private static final String SELECT_ORDERS_IN_PROCESS = 
			"select * from orders where state=IN_PROCESS";
	
	@Override
	public void addOrder(Order order, int idUser) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getOrdersInProcess() throws DAOException {

		List<Order> orderList = new ArrayList<>();
        try {
			connection = pool.takeConnection();
        	statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ORDERS_IN_PROCESS);
            while (rs.next()) {
            	orderList.add(createOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new DAOException("Error during getting all orders in process from database", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
            } catch (SQLException ex) {
              //log
            }
		}
        return orderList;
	}

	private Order createOrderFromResultSet(ResultSet rs) throws SQLException  {

		Order order = new Order();

		int id_order = rs.getInt(1);

		order.setId(id_order);
		//order.setOrderTime(rs.getDate(2));//???приведение типов
		//order.setDeliveryTime(rs.getDate(3));//???приведение типов
		order.setPrice(rs.getDouble(4));
		order.setPaymentType(PaymentType.valueOf(rs.getString(5)));
		order.setDeliveryType(DeliveryType.valueOf(rs.getString(6)));
		order.setState(OrderState.valueOf(rs.getString(7)));

		order.setDishes(createDishesFromResultSet(id_order));


		return order;
	}
	
	private Set<String> createDishesFromResultSet(int id_order) throws SQLException {
		
		Set<String> dishes = new HashSet<>();

		ps = connection.prepareStatement(SELECT_DISHES_OF_ORDER);
		ps.setInt(1, rs.getInt(1));
		
		ResultSet rsDishes = ps.executeQuery();
	
		while(rsDishes.next()) {
			dishes.add(rsDishes.getString(1));
		}

		ps.close();

		return dishes;
	}

	
}
