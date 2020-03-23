package by.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.bean.util.Category;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.DishDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class DishDAOImpl implements DishDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	private Connection connection;
	private Statement st;
	PreparedStatement ps;
	private ResultSet rs;
	
	
    private static final String INSERT_DISH = 
    		"insert into dish(name, price, picture, category, amound, state)"
    		+ " values(?,?,?,?,?,?)";
    
    private static final String SELECT_SNACKS = 
    		""

	@Override
	public int addDish(Dish dish) throws DAOException{
		
		int status = 0;
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(INSERT_DISH);
			ps.setString(1, dish.getName());
			ps.setDouble(2, dish.getPrice());
			ps.setString(3, dish.getPicture());
			ps.setString(4, dish.getCategory().name());
			ps.setDouble(5, dish.getAmount());
			ps.setString(6, dish.getState().name());
			
			status = ps.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Error during additing dish in database!", e);
			}catch(ConnectionPoolException e) {
				throw new DAOException("Error during getting connection from connection pool!", e);
			} finally {
				try {
					rs.close();
					ps.close();
					connection.close();
	            } catch (SQLException ex) {
	              //log
	            }
			}
		return status;
	}

	@Override 
	public List<Dish> findSnacks() throws DAOException{

		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_SNACKS);
			while (rs.next()) {   
				dishes.add(createDishFromResultSet(rs)); 
			}
		}catch(SQLException e) {
			throw new DAOException("Error during finding snacks in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			try {
				rs.close();
				st.close();
				connection.close();
            } catch (SQLException ex) {
              //log
            }
		}

		return dishes;
		}
	
	
	//+5
	
	
	private Dish createDishFromResultSet(ResultSet rs) {
		
		Dish dish = new Dish();
		dish.setName(rs.getString(2));
		dish.setPrice(rs.getDouble(3));
		dish.setPicture(rs.getString(4));//????
		dish.setCategory(Category.valueOf(rs.getString(5)));
		dish.setAmount(rs.getDouble(6));
		dish.setName(rs.getString(2));
		//ingredients????
	}
}
