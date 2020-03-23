package by.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import by.restaurant.bean.User;
import by.restaurant.bean.util.Role;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static final String INSERT_USER = 
			"insert into users(login, password, role, email, address, "
			+ "loyalty_points, is_banned) values(?,?,?,?,?,?,?)";
	private static final String SELECT_USER_BY_LOGIN_PASSWORD = 
			"select * from users where login=? and password=?";
	private static final String SELECT_USER_BY_LOGIN = 
			"select * from users where login=?";

	@Override
	public int addUser(User user) throws DAOException{
		
		int status = 0;
		
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(INSERT_USER);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole().name());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getAddress());
			ps.setDouble(6, user.getLoyaltyPoints());
			ps.setBoolean(7, user.isBanned());
			
			status = ps.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Error during additing user in database!", e);
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

	@Override
	public User getUser(String login, String password) throws DAOException{
		
		User user = null;
		
		try {
			
			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_PASSWORD);
			ps.setString(1, login);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			if(!rs.next()) {
				return null;
			}
				//while(rs.next()) {
					user = new User();
					user.setLogin(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setRole(Role.valueOf(rs.getString(4)));
					user.setEmail(rs.getString(5));
					user.setAddress(rs.getString(6));
					user.setLoyaltyPoints(rs.getDouble(7));
					user.setBanned(rs.getBoolean(8));
				//}
			
		}catch(SQLException e) {
			throw new DAOException("Error during getting user from database!", e);
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
	
			
		return user;
	}
	
	public boolean isExist(String login) throws DAOException{

		try {
			
			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			ps.setString(1, login);
			
			rs = ps.executeQuery();
			if(!rs.next()) {
				return false;
			}
			if(rs.getString(2).equalsIgnoreCase(login)) {
					return true;
			}
			
		}catch(SQLException e) {
			throw new DAOException("Error during getting user from database!", e);
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
		return false;
	}

}
