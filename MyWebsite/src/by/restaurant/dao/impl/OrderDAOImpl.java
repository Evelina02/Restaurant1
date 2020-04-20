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

import by.restaurant.bean.Dish;
import by.restaurant.bean.Order;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.DeliveryType;
import by.restaurant.bean.constant.OrderState;
import by.restaurant.bean.constant.PaymentType;
import by.restaurant.bean.constant.Role;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.OrderDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class OrderDAOImpl implements OrderDAO {

    //private static Logger logger = LogManager.getLogger();
	private ConnectionPool pool = ConnectionPool.getInstance();

    private static final String SELECT_DISHES_OF_ORDER = 
    		"select * from dish as d "
    	    + "join order_dish od on od.id_dish=d.id_dish "
    	    + "where id_order=?";
    		
    		
    
	private static final String SELECT_ORDERS_IN_PROCESS = 
			"select * from orders where state=IN_PROCESS";
	
	//проверить в mysql
    private static final String INSERT_ORDER = 
    		"insert into orders(id_client, order_time, delivery_time, price, payment_type, "
    		+ "delivery_type, state) values(?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_ORDER_DISH = 
    		"insert into order_dish(id_order, id_dish) "
    		+ "values(?, (select id_dish from dish where name=?))";
    
    private static final String SELECT_ID_ORDER_BY_ORDER_TIME = 
    		"select id_order from orders where order_time=?";
    
	@Override
	public void addOrder(Order order, int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = pool.takeConnection();
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(INSERT_ORDER);
			ps.setInt(1,idUser);
			ps.setString(2, order.getOrderTime());
			ps.setString(3, order.getDeliveryTime());
			ps.setDouble(4, order.getPrice());
			ps.setString(5, order.getPaymentType().name());
			ps.setString(6, order.getDeliveryType().name());
			ps.setString(7, order.getState().name());

			ps.executeUpdate();

			order.setId(selectIdOrderByOrderTime(connection, order.getOrderTime()));

			insertOrderDish(connection, order.getDishes(), order.getId());
			
			connection.commit();
			}catch(SQLException e) {
				if(connection != null) {
					try {
						connection.rollback();
					}catch(SQLException ex) {
						//log
					}
				}
				throw new DAOException("Error during additing dish in database!", e);
				
			}catch(ConnectionPoolException e) {
				throw new DAOException("Error during getting connection from connection pool!", e);
			} finally {
				try {
					connection.setAutoCommit(true);
				} catch (SQLException e) {
					// log
				}
				pool.closeConnection(connection, ps);
			}
	}

	private void insertOrderDish(Connection connection, Set<Dish> dishes, int idOrder) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_DISH);
		for(Dish dish: dishes) {
			ps.setInt(1, idOrder);
			ps.setString(2, dish.getName());
		}
		ps.close();
	}

	private int selectIdOrderByOrderTime(Connection connection, String orderTime) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(SELECT_ID_ORDER_BY_ORDER_TIME);
		ps.setString(1, orderTime);
		ResultSet rs = ps.executeQuery();
		int idDish = rs.getInt(1);
		
		rs.close();
		ps.close();
		
		return idDish;
	}

	@Override
	public List<Order> findOrdersInProcess() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Order> orderList = new ArrayList<>();
        try {
			connection = pool.takeConnection();
        	st = connection.createStatement();
            rs = st.executeQuery(SELECT_ORDERS_IN_PROCESS);
            while (rs.next()) {
            	//исправить
            	orderList.add(createOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new DAOException("Error during getting all orders in process from database", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
        return orderList;
	}

	private Order createOrderFromResultSet(ResultSet rs) throws SQLException, ConnectionPoolException  {

		Order order = new Order();

		int id_order = rs.getInt(1);

		order.setId(id_order);
		order.setOrderTime(rs.getString(2));
		order.setDeliveryTime(rs.getString(3));
		order.setPrice(rs.getDouble(4));
		order.setPaymentType(PaymentType.valueOf(rs.getString(5)));
		order.setDeliveryType(DeliveryType.valueOf(rs.getString(6)));
		order.setState(OrderState.valueOf(rs.getString(7)));
		//исправить
		order.setDishes(createDishesFromResultSet(id_order));


		return order;
	}
	
	private Set<Dish> createDishesFromResultSet(int id_order) throws SQLException, ConnectionPoolException {

		Set<Dish> dishes = new HashSet<>();

		Connection connection = pool.takeConnection();;

		PreparedStatement ps = connection.prepareStatement(SELECT_DISHES_OF_ORDER);
		ps.setInt(1, id_order);
		
		ResultSet rs = ps.executeQuery();
		//исправить
		while(rs.next()) {
		//	dishes.add(rs.getString(1));
		}
		pool.closeConnection(connection, ps, rs);
		
		return dishes;
	}

	
}
