package by.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.restaurant.bean.User;
import by.restaurant.bean.constant.Role;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {

    //private static Logger logger = LogManager.getLogger();
	private ConnectionPool pool = ConnectionPool.getInstance();

	
	private static final String INSERT_USER = 
			"insert into users(login, password, role, email, address)"
			+ " values(?,?,?,?,?)";
	private static final String SELECT_USER_BY_LOGIN_PASSWORD = 
			"select * from users where login=? and password=?";
	private static final String SELECT_USER_BY_LOGIN = 
			"select * from users where login=?";
	private static final String SELECT_UNBANNED_USERS = 
			"select * from users where is_banned=0";

	@Override
	public int addUser(User user) throws DAOException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int status = 0;
		
		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(INSERT_USER);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole().name());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getAddress());
			
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
	public User getUser(String login, String password) throws DAOException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
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
			user = createUserFromResultSet(rs);
			
		}catch(SQLException e) {
			throw new DAOException("Error during getting user from database!", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
	
			
		return user;
	}
	
	public boolean isExist(String login) throws DAOException{

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
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
			pool.closeConnection(connection, ps, rs);
		}
		return false;
	}

	public ArrayList<User> findActiveUsers() throws DAOException {
        
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		ArrayList<User> userList = new ArrayList<>();
        try {
			connection = pool.takeConnection();
        	st = connection.createStatement();
            rs = st.executeQuery(SELECT_UNBANNED_USERS);
            while (rs.next()) {
                userList.add(createUserFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new DAOException("Error finding unbanned users in database", e);
		}catch(ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
        return userList;
    }

	private User createUserFromResultSet(ResultSet rs) throws SQLException {

		User user = new User();
		user.setId(rs.getInt(1));
		user.setLogin(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setRole(Role.valueOf(rs.getString(4)));
		user.setEmail(rs.getString(5));
		user.setAddress(rs.getString(6));
		user.setLoyaltyPoints(rs.getDouble(7));
		user.setBanned(rs.getBoolean(8));
		
		return user;
	}

}
