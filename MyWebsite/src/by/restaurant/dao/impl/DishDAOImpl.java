package by.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.DishDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class DishDAOImpl implements DishDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	private Connection connection;
	private PreparedStatement ps;
	
    private static final String INSERT_DISH = 
    		"insert into dish(name, price, picture, category, amound, state)"
    		+ " values(?,?,?,?,?,?)";

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
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    //log
	                }
	            }
			}
		return status;
	}

//	
//	@Override
//    public List<Dish> findDeserts() throws DAOException {
//        
//		List<Dish> dishes = new ArrayList<Dish>();
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(SQL_SELECT_DESERTS);
//            while (resultSet.next()) {                dishList.add(createDishFromResultSet(resultSet)); }
//            return dishList;
//        } catch (SQLException e) {
//            throw new DaoException("Exception while finding deserts in db", e);
//        } }
//
//	
	

}
