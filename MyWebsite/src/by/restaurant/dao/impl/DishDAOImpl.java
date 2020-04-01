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
import by.restaurant.bean.util.Category;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.DishDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class DishDAOImpl implements DishDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	private Connection connection;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
    
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
    private static final String INSERT_INGREDIENT = 
    		"insert into ingredient(name) values(?)";
    private static final String INSERT_DISH_CONTENTS = 
    		"insert into dish_contents(id_dish, id_ingredient) "
    		+ "values(?, (select id_ingredient from ingredient where name=?))";
    private static final String SELECT_ID_DISH_BY_NAME = 
    		"select id_dish from dish where name=?";
    
	@Override
	public void addDish(Dish dish) throws DAOException{
		
		//int status = 0;
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(INSERT_DISH);
			ps.setString(1, dish.getName());
			ps.setDouble(2, dish.getPrice());
			ps.setString(3, dish.getPicture());
			ps.setString(4, dish.getCategory().name());
			ps.setString(5, dish.getAmount());
			ps.executeUpdate();
			
			insertIngredients(dish.getIngredients());

			dish.setId(selectIdDishByName(dish.getName()));

			insertDishContents(dish.getIngredients(), dish.getId());
			
			}catch(SQLException e) {
				throw new DAOException("Error during additing dish in database!", e);
			}catch(ConnectionPoolException e) {
				throw new DAOException("Error during getting connection from connection pool!", e);
			} finally {
				try {
					ps.close();
					connection.close();
	            } catch (SQLException ex) {
	              //log
	            }
			}
		//return status;
	}


	@Override
	public List<Dish> findSnacks() throws DAOException {

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


	@Override
	public List<Dish> findHotDishes() throws DAOException {

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


	@Override
	public List<Dish> findPizza() throws DAOException {

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


	@Override
	public List<Dish> findDesserts() throws DAOException {

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


	@Override
	public List<Dish> findDrinks() throws DAOException {

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

	
	@Override 
	public List<Dish> findSalads() throws DAOException{

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
	
	private void insertIngredients(Set<String> ingredients) throws SQLException {
		
		ps = connection.prepareStatement(INSERT_INGREDIENT);
		for(String ingredient: ingredients) {
			ps.setString(1, ingredient);
			ps.executeUpdate();
		}
		ps.close();
	}
	
	private void insertDishContents(Set<String> ingredients, int idDish) throws SQLException {
		
		ps = connection.prepareStatement(INSERT_DISH_CONTENTS);
		for(String ingredient: ingredients) {
			ps.setInt(1, idDish);
			ps.setString(2, ingredient);
		}
		ps.close();
	}
	
	private int selectIdDishByName(String name) throws SQLException {
		
		ps = connection.prepareStatement(SELECT_ID_DISH_BY_NAME);
		ps.setString(1, name);
		rs = ps.executeQuery();
		int idDish = rs.getInt(1);
		
		return idDish;		
	}
	
	private Dish createDishFromResultSet(ResultSet rs) throws SQLException {
		
		Dish dish = new Dish();

		int id_dish = rs.getInt(1);
			
		dish.setId(id_dish);
		dish.setName(rs.getString(2));
		dish.setPrice(rs.getDouble(3));
		dish.setPicture(rs.getString(4));//????
		dish.setCategory(Category.valueOf(rs.getString(5)));
		dish.setAmount(rs.getString(6));
		
		dish.setIngredients(createIngredientsFromResultSet(id_dish));

		return dish;
	}

	private Set<String> createIngredientsFromResultSet(int id_dish) throws SQLException {

		Set<String> ingredients = new HashSet<>();

		ps = connection.prepareStatement(SELECT_INGREDIENTS_OF_DISH);
		ps.setInt(1, rs.getInt(1));
		
		rs = ps.executeQuery();
	
		while(rs.next()) {
			ingredients.add(rs.getString(1));
		}
		rs.close();
		ps.close();
			
		return ingredients;
}
}
