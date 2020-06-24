package by.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import by.restaurant.bean.Review;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.Role;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {

	// private static Logger logger = LogManager.getLogger();
	private ConnectionPool pool = ConnectionPool.getInstance();

	private static final String INSERT_USER = "insert into users(login, password, role, email, address)"
			+ " values(?,?,?,?,?)";
	
	private static final String SELECT_USER_BY_LOGIN = "select * from users where login=?";
	
	private static final String SELECT_ALL_USERS = "select * from users where role='CLIENT'";

	private static final String SELECT_USER_BY_ID = "select * from users where id_user=?";

	private static final String UPDATE_USER = "update users set login=?, email=?, address=? where id_user=?";

	private static final String SELECT_ALL_REVIEWS = 
			"select * from review r join users u on r.id_user=u.id_user where is_deleted=0 "
			+ "order by id_review desc";

	private static final String UPDATE_USER_PASSWORD = "update users set password=? where id_user=?";

	private static final String BAN_USER = "update users set is_banned=1 where id_user=?";

	private static final String UNBAN_USER = "update users set is_banned=0 where id_user=?";

	private static final String INSERT_REVIEW = 
			"insert into review(id_user, body, review_date) values(?, ?, ?)";

	private static final String DELETE_REVIEW = 
			"update review set is_deleted=1 where id_review=?";

	private static final String UPDATE_PASSWORD_BY_LOGIN = 
			"update users set password=? where login=?";

	private static final String SELECT_EMAIL_BY_LOGIN = 
			"select email from users where login=?";

	private static final String SELECT_EMAIL_BY_ID = 
			"select email from users where id_user=?";

	private static final String SELECT_ID_BY_LOGIN = 
			"select id_user from users where login=?";

	private static final String SELECT_LOYALTY_POINTS_BY_ID = 
			"select loyalty_points from users where id_user=?";

	private static final String UPDATE_LOYALTY_POINTS = 
			"update users set loyalty_points=? where id_user=?";

	@Override
	public int addUser(User user) throws DAOException {

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
		} catch (SQLException e) {
			throw new DAOException("Error during additing user in database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return status;
	}

	@Override
	public User getUser(String login, String password) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			ps.setString(1, login);
			rs = ps.executeQuery();
			rs.next();
			if (BCrypt.checkpw(password, rs.getString("password"))) {
				user = createUserFromResultSet(rs);
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException("Error during getting user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
		return user;
	}

	@Override
	public User getUserById(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			user = createUserFromResultSet(rs);

		} catch (SQLException e) {
			throw new DAOException("Error during getting user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return user;
	}

	public boolean isExist(String login) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			ps.setString(1, login);

			rs = ps.executeQuery();
			if (!rs.next()) {
				return false;
			}
			if (rs.getString("login").equals(login)) {
				return true;
			}

		} catch (SQLException e) {
			throw new DAOException("Error during getting user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
		return false;
	}

	public List<User> findAllUsers() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		ArrayList<User> userList = new ArrayList<>();
		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_ALL_USERS);
			while (rs.next()) {
				userList.add(createUserFromResultSet(rs));
			}

		} catch (SQLException e) {
			throw new DAOException("Error finding unbanned users in database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
		return userList;
	}

	@Override
	public int updateUser(int idUser, String login, String email, String address) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(UPDATE_USER);

			ps.setString(1, login);
			ps.setString(2, email);
			ps.setString(3, address);
			ps.setInt(4, idUser);

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

	@Override
	public List<Review> showAllReviews() throws DAOException {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		List<Review> reviews = new ArrayList<>();
		try {
			connection = pool.takeConnection();
			st = connection.createStatement();
			rs = st.executeQuery(SELECT_ALL_REVIEWS);
			while (rs.next()) {
				reviews.add(createReviewFromResultSet(rs));
			}

		} catch (SQLException e) {
			throw new DAOException("Error finding unbanned users in database", e);
		} catch (ConnectionPoolException e) {

			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, st, rs);
		}
		return reviews;
	}

	private Review createReviewFromResultSet(ResultSet rs) throws SQLException {

		Review review = new Review();
		review.setId(rs.getInt("id_review"));
		review.setBody(rs.getString("body"));
		review.setTime(rs.getString("review_date"));
		review.setUserLogin(rs.getString("login"));
		
		return review;
	}

	@Override
	public int changePassword(int idUser, String oldPassword, String newPassword) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(SELECT_USER_BY_ID);

			ps.setInt(1, idUser);
			rs = ps.executeQuery();
						
			rs.next();
			if (BCrypt.checkpw(oldPassword, rs.getString("password"))) {
				ps1 = connection.prepareStatement(UPDATE_USER_PASSWORD);

				ps1.setString(1, BCrypt.hashpw(newPassword, BCrypt.gensalt()));
				ps1.setInt(2, idUser);

				status = ps1.executeUpdate();
				ps1.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Error during additing user in database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return status;
	}

	@Override
	public int banUser(int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(BAN_USER);

			ps.setInt(1, idUser);

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

	@Override
	public int unbanUser(int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(UNBAN_USER);

			ps.setInt(1, idUser);

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

	@Override
	public boolean isBanned(String login) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			ps.setString(1, login);

			rs = ps.executeQuery();
			rs.next();
			
			if (rs.getBoolean("is_banned")) {
				return true;
			}

		} catch (SQLException e) {
			throw new DAOException("Error during getting user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public int addReview(Review review, int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(INSERT_REVIEW);
			ps.setInt(1, idUser);
			ps.setString(2, review.getBody());
			ps.setString(3, review.getTime());

			status = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error during additing review in database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return status;
	}

	@Override
	public int deleteReview(int reviewId) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(DELETE_REVIEW);

			ps.setInt(1, reviewId);

			status = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error during deleting review in database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
		return status;
	}

	@Override
	public int resetPassword(String login, String newPassword) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(UPDATE_PASSWORD_BY_LOGIN);

			ps.setString(1, newPassword);
			ps.setString(2, login);

			status = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error during reseting password in database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
		return status;
	}

	@Override
	public String getEmailByLogin(String login) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String email = null;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_EMAIL_BY_LOGIN);
			ps.setString(1, login);

			rs = ps.executeQuery();
			rs.next();
			email = rs.getString(1);

		} catch (SQLException e) {
			throw new DAOException("Error during getting email of user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return email;
	}

	@Override
	public String getEmailById(int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String email = null;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_EMAIL_BY_ID);
			ps.setInt(1, idUser);

			rs = ps.executeQuery();
			rs.next();
			email = rs.getString(1);

		} catch (SQLException e) {
			throw new DAOException("Error during getting email of user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return email;
	}

	@Override
	public int getIdByLogin(String login) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int idUser = 0;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_ID_BY_LOGIN);
			ps.setString(1, login);

			rs = ps.executeQuery();
			rs.next();
			idUser = rs.getInt(1);

		} catch (SQLException e) {
			throw new DAOException("Error during getting email of user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return idUser;
	}

	@Override
	public double getLoyaltyPointsById(int idUser) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double loyaltyPoints = 0;

		try {

			connection = pool.takeConnection();
			ps = connection.prepareStatement(SELECT_LOYALTY_POINTS_BY_ID);
			ps.setInt(1, idUser);

			rs = ps.executeQuery();
			rs.next();
			loyaltyPoints = rs.getDouble(1);

		} catch (SQLException e) {
			throw new DAOException("Error during getting loyaltyPoints of user from database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}

		return loyaltyPoints;
	}

	@Override
	public int updateUserLoyaltyPoints(int idUser, double loyaltyPoints) throws DAOException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int status = 0;

		try {
			connection = pool.takeConnection();

			ps = connection.prepareStatement(UPDATE_LOYALTY_POINTS);
			
			ps.setDouble(1, loyaltyPoints);
			ps.setInt(2, idUser);

			status = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error during updatint loyalty points of user in database!", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error during getting connection from connection pool!", e);
		} finally {
			pool.closeConnection(connection, ps, rs);
		}
		return status;
	}

}
