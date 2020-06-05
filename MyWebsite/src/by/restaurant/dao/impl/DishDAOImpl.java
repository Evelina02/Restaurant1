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
import by.restaurant.bean.User;
import by.restaurant.bean.constant.Category;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.DishDAO;

import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class DishDAOImpl implements DishDAO {

    //private static Logger logger = LogManager.getLogger();
	private ConnectionPool pool = ConnectionPool.getInstance();
	
    
    private static final String SELECT_SALADS = 
    		"select * from dish where category='SALADS' and is_deleted=0";
    private static final String SELECT_HOT_DISHES = 
    		"select * from dish where category='HOT_DISHES' and is_deleted=0";
    private static final String SELECT_SNACKS = 
    		"select * from dish where category='SNACKS' and is_deleted=0";
    private static final String SELECT_PIZZA = 
    		"select * from dish where category='PIZZA' and is_deleted=0";
    private static final String SELECT_DESSERTS = 
    		"select * from dish where category='DESSERTS' and is_deleted=0";
    private static final String SELECT_DRINKS = 
    		"select * from dish where category='DRINKS' and is_deleted=0";
    
    private static final String SELECT_INGREDIENTS_OF_DISH = 
    		"select i.name from ingredient as i "
    		+ "join dish_contents dc on dc.id_ingredient=i.id_ingredient "
    		+ "where id_dish=?";

    private static final String INSERT_DISH = 
    		"insert into dish(name, price, picture, category, amount)"
    		+ " values(?, ?, ?, ?, ?)";
    private static final String SELECT_INGREDIENT_BY_NAME = 
    		"select id_ingredient from ingredient where name=?";//////
    
    private static final String INSERT_INGREDIENT = 
    		"insert into ingredient(name) values(?)";
    private static final String INSERT_DISH_CONTENTS = 
    		"insert into dish_contents(id_dish, id_ingredient) "
    		+ "values(?, (select id_ingredient from ingredient where name=?))";
    private static final String SELECT_ID_DISH_BY_NAME = 
    		"select id_dish from dish where name=?";


	private static final String SELECT_ALL_DISHES = 
			"select * from dish where is_deleted=0";

	private static final String DELETE_DISH = 
			"update dish set is_deleted=1 where id_dish=?";


	private static final String UPDATE_DISH = 
			"update dish set picture=?, price=?, amount=? where id_dish=?";
		
	private static final String SEARCH_DISH_BY_PART_OF_NAME =
			"select * from dish where match(name) against (?) and is_deleted=0";
    
	@Override 
	public int addDish(Dish dish) throws DAOException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		int status = 0;

		try {
			connection = pool.takeConnection();
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(INSERT_DISH);
			ps.setString(1, dish.getName());
			ps.setDouble(2, dish.getPrice());
			ps.setString(3, dish.getPicture());
			ps.setString(4, dish.getCategory().name());
			ps.setString(5, dish.getAmount());
			ps.executeUpdate();
			
			dish.setId(selectIdDishByName(connection, dish.getName()));

			if(dish.getIngredients() != null) {
				insertIngredients(connection, dish.getIngredients());
				insertDishContents(connection, dish.getIngredients(), dish.getId());
			}

			connection.commit();
			status = 1;
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
		return status;
	}

	
	@Override
	public List<Dish> searchDishByPartOfName(String partOfName) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			ps = connection.prepareStatement(SEARCH_DISH_BY_PART_OF_NAME);
			ps.setString(1, partOfName);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				dishes.add(createDishFromResultSet(rs));
			}
		}catch(SQLException e) {
			throw new DAOException("Error during finding snacks in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
		
		return dishes;		
	}

	@Override
	public List<Dish> findSnacks() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
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
			pool.closeConnection(connection, st, rs);
		}
		
		return dishes;		
	}


	@Override
	public List<Dish> findHotDishes() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_HOT_DISHES);
			while (rs.next()) {
				dishes.add(createDishFromResultSet(rs));
			}
		}catch(SQLException e) {
			throw new DAOException("Error during finding hot dishes in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
		return dishes;		
	}


	@Override
	public List<Dish> findPizza() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_PIZZA);
			while (rs.next()) {
				dishes.add(createDishFromResultSet(rs));
			}
		}catch(SQLException e) {
			throw new DAOException("Error during finding pizza in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
		return dishes;		
	}


	@Override
	public List<Dish> findDesserts() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_DESSERTS);
			while (rs.next()) {
				dishes.add(createDishFromResultSet(rs));
			}
		}catch(SQLException e) {
			throw new DAOException("Error during finding desserts in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
		return dishes;		
	}


	@Override
	public List<Dish> findDrinks() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_DRINKS);
			while (rs.next()) {
				dishes.add(createDishFromResultSet(rs));
			}
		}catch(SQLException e) {
			throw new DAOException("Error during finding drinks in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
		return dishes;	
	}

	
	@Override 
	public List<Dish> findSalads() throws DAOException{

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_SALADS);
			while (rs.next()) {
				dishes.add(createDishFromResultSet(rs));
			}
			
		}catch(SQLException e) {
			throw new DAOException("Error during finding salads in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}

		return dishes;
		}
	
	private void insertIngredients(Connection connection, Set<String> ingredients) throws SQLException {
		
		for(String ingredient: ingredients) {
			PreparedStatement ps = connection.prepareStatement(SELECT_INGREDIENT_BY_NAME);
			ps.setString(1, ingredient);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				PreparedStatement ps1 = connection.prepareStatement(INSERT_INGREDIENT);

				ps1.setString(1, ingredient);
				ps1.executeUpdate();
				
				ps1.close();
			}
			ps.close();
		}
	}
	
	private void insertDishContents(Connection connection, Set<String> ingredients, int idDish) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement(INSERT_DISH_CONTENTS);
		for(String ingredient: ingredients) {
			ps.setInt(1, idDish);
			ps.setString(2, ingredient);
			
			ps.executeUpdate();
		}
		ps.close();
	}
	
	private int selectIdDishByName(Connection connection, String name) throws SQLException {
		
		int idDish;
		PreparedStatement ps = connection.prepareStatement(SELECT_ID_DISH_BY_NAME);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		rs.next();
		idDish = rs.getInt(1);
		
		rs.close();
		ps.close();
		
		return idDish;		
	}
	
	private Dish createDishFromResultSet(ResultSet rs) throws SQLException, ConnectionPoolException {
		
		Dish dish = new Dish();

		int id_dish = rs.getInt(1);
			
		dish.setId(id_dish);
		dish.setName(rs.getString(2));
		dish.setPrice(rs.getDouble(3));
		dish.setPicture(rs.getString(4));
		dish.setCategory(Category.valueOf(rs.getString(5)));
		dish.setAmount(rs.getString(6));
		
		dish.setIngredients(createIngredientsFromResultSet(id_dish));

		return dish;
	}

	private Set<String> createIngredientsFromResultSet(int id_dish) throws SQLException, ConnectionPoolException {

		Set<String> ingredients = new HashSet<>();

		Connection connection = pool.takeConnection();;

		PreparedStatement ps = connection.prepareStatement(SELECT_INGREDIENTS_OF_DISH);
		ps.setInt(1, id_dish);
		
		ResultSet rs = ps.executeQuery();
	
		while(rs.next()) {
			ingredients.add(rs.getString(1));
		}
		pool.closeConnection(connection, ps, rs);

			
		return ingredients;
}


	@Override
	public List<Dish> getAllDishes() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		List<Dish> dishes = new ArrayList<Dish>();

		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_ALL_DISHES);
			while (rs.next()) {
				dishes.add(createDishFromResultSet(rs));
			}
		}catch(SQLException e) {
			throw new DAOException("Error during finding snacks in database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
		
		return dishes;		
	}


	@Override
	public int deleteDish(int dishId) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int status = 0;
		
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(DELETE_DISH);
			
			ps.setInt(1,dishId);
			
			status = ps.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Error during deleting dish!", e);
			}catch(ConnectionPoolException e) {
				throw new DAOException("Error during getting connection from connection pool!", e);
			} finally {
				pool.closeConnection(connection, ps, rs);
			}
		
		return status;
	
	}


	@Override
	public int updateDish(int idDish, String picture, Double price, String amount) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(UPDATE_DISH);

			ps.setString(1, picture);
			ps.setDouble(2, price);
			ps.setString(3, amount);
			ps.setInt(4, idDish);

			status = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error during additing user in database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return status;
	}
}
