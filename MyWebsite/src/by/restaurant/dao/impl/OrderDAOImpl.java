package by.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.bean.Order;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.Category;
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

	private static final String SELECT_ALL_ORDERS= 
			"select * from orders o join users u on o.id_client=u.id_user order by id_order desc";

    private static final String INSERT_ORDER = 
    		"insert into orders(id_client, order_time, delivery_time, price, payment_type, "
    		+ "delivery_type, state) values(?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_ORDER_DISH = 
    		"insert into order_dish(id_order, id_dish, amount) "
    		+ "values(?, (select id_dish from dish where name=?), ?)";
    
    private static final String SELECT_ID_ORDER_BY_ORDER_TIME = 
    		"select id_order from orders where order_time=?";
    
	private static final String SELECT_ORDERS_OF_USER = 
			"select * from orders o join users u on u.id_user=o.id_client "
			+ "where id_client=? order by id_order desc";

	private static final String CANCEL_ORDER = 
			"update orders set state='CANCELED' where id_order=?";

	private static final String DO_ORDER = 
			"update orders set state='DONE' where id_order=?";

	private static final String CLOSE_ORDER = 
			"update orders set state='CLOSED' where id_order=?";
	
	@Override
	public boolean addOrder(Order order, int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = pool.takeConnection();
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(INSERT_ORDER);
			ps.setInt(1,idUser);
			ps.setString(2, order.getOrderTime());
			ps.setString(3, order.getDeliveryTime());
			ps.setDouble(4, order.getBasket().getTotalPrice());
			ps.setString(5, order.getPaymentType().name());
			ps.setString(6, order.getDeliveryType().name());
			ps.setString(7, order.getState().name());

			ps.executeUpdate();

			order.setId(selectIdOrderByOrderTime(connection, order.getOrderTime()));

			insertOrderDish(connection, order.getBasket(), order.getId());
			
			connection.commit();
			
			return true;

			}catch(SQLException e) {
				if(connection != null) {
					try {
						connection.rollback();
						return false;
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

	@Override
	public List<Order> selectAllOrdersOfUser(int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Order> orderList = new ArrayList<>();
        try {
			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_ORDERS_OF_USER);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
            while (rs.next()) {
            	orderList.add(createOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new DAOException("Error during getting all orders in process from database", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
        return orderList;
		
	}

	private int insertOrderDish(Connection connection, Basket basket, int idOrder) throws SQLException {

		int status = 0;
		PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_DISH);
		for(Dish dish: basket.getDishes()) {
			ps.setInt(1, idOrder);
			ps.setString(2, dish.getName());
			ps.setInt(3, basket.getCountDishById().get(dish.getId()));
			status += ps.executeUpdate();
		}
		ps.close();
		
		if(status == basket.getDishes().size()) {
			return status;
		}
		return 0;
	}

	private int selectIdOrderByOrderTime(Connection connection, String orderTime) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(SELECT_ID_ORDER_BY_ORDER_TIME);
		ps.setString(1, orderTime);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int idOrder = rs.getInt(1);
		
		rs.close();
		ps.close();
		
		return idOrder;
	}

	private Order createOrderFromResultSet(ResultSet rs) throws SQLException, ConnectionPoolException  {

		Order order = new Order();

		int id_order = rs.getInt(1);

		order.setId(id_order);
		order.setOrderTime(rs.getString(3));
		order.setDeliveryTime(rs.getString(4));
		order.setBasket((createDishesFromResultSet(id_order)));
		order.getBasket().setTotalPrice((rs.getDouble(5)));
		order.setPaymentType(PaymentType.valueOf(rs.getString(6)));
		order.setDeliveryType(DeliveryType.valueOf(rs.getString(7)));
		order.setState(OrderState.valueOf(rs.getString(8)));
		order.setUserLogin(rs.getString("login"));
		return order;
	}
	
	private Basket createDishesFromResultSet(int id_order) throws SQLException, ConnectionPoolException {

		Basket basket = new Basket();
		Set<Dish> dishes = new HashSet<>();
		Map <Integer, Integer> countDishById = new HashMap<>();

		Connection connection = pool.takeConnection();;

		PreparedStatement ps = connection.prepareStatement(SELECT_DISHES_OF_ORDER);
		ps.setInt(1, id_order);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dishes.add(createDishFromResultSet(rs));
			countDishById.put(rs.getInt("id_dish"), rs.getInt(10));
		}
		
		basket.setCountDishById(countDishById);
		basket.setDishes(dishes);
		pool.closeConnection(connection, ps, rs);

		return basket;
	}

	private Dish createDishFromResultSet(ResultSet rs) throws SQLException, ConnectionPoolException {
		
		Dish dish = new Dish();

		dish.setId(rs.getInt("id_dish"));
		dish.setName(rs.getString("name"));
		dish.setPrice(rs.getDouble("price"));
		dish.setPicture(rs.getString("picture"));
		dish.setCategory(Category.valueOf(rs.getString("category")));
		dish.setAmount(rs.getString(6));//amount
		
		return dish;
	}

	@Override
	public int cancelOrder(int orderId) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int status = 0;
		
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(CANCEL_ORDER);
			
			ps.setInt(1,orderId);
			
			status = ps.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Error during additing user in database!", e);
			}catch(ConnectionPoolException e) {
				throw new DAOException("Error during getting connection from connection pool!", e);
			} finally {
				pool.closeConnection(connection, ps, rs);
			}
		
		return status;
	}

	@Override
	public List<Order> selectAllOrders() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Order> orderList = new ArrayList<>();
        try {
			connection = pool.takeConnection();
        	st = connection.createStatement();
            rs = st.executeQuery(SELECT_ALL_ORDERS);
            while (rs.next()) {
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

	@Override
	public int doOrder(int orderId) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int status = 0;
		
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(DO_ORDER);
			
			ps.setInt(1,orderId);
			
			status = ps.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Error during doing order in database!", e);
			}catch(ConnectionPoolException e) {
				throw new DAOException("Error during getting connection from connection pool!", e);
			} finally {
				pool.closeConnection(connection, ps, rs);
			}
		
		return status;
	}

	@Override
	public int closeOrder(int orderId) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int status = 0;
		
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(CLOSE_ORDER);
			
			ps.setInt(1,orderId);
			
			status = ps.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Error during closing order in database!", e);
			}catch(ConnectionPoolException e) {
				throw new DAOException("Error during getting connection from connection pool!", e);
			} finally {
				pool.closeConnection(connection, ps, rs);
			}
		
		return status;
	}

	@Override
	public int getIdOrderByOrderTime(String orderTime) throws DAOException {

		Connection connection = null;
		Statement st = null;//closeConnection
		int idOrder = 0;

		try {

			connection = pool.takeConnection();
			idOrder = selectIdOrderByOrderTime(connection, orderTime);

		} catch (SQLException e) {
			throw new DAOException("Error during getting email of user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st);
		}

		return idOrder;
	}

	
}
