package by.restaurant.controller.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.controller.command.impl.SignUp;
import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

@WebListener
public class PoolListener implements ServletRequestListener {

	private static final Logger logger = LogManager.getLogger(PoolListener.class);

	public PoolListener() {}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		try {
			ConnectionPool.getInstance().initPoolData();
			
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, "Error during initialization pool connection", e);
		}
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		ConnectionPool.getInstance().dispose();
	}
}
