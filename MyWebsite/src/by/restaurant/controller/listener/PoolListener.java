package by.restaurant.controller.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import by.restaurant.dao.pool.ConnectionPool;
import by.restaurant.dao.pool.ConnectionPoolException;

@WebListener
public class PoolListener implements ServletRequestListener {

	public PoolListener() {}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		try {
			ConnectionPool.getInstance().initPoolData();
		} catch (ConnectionPoolException e) {
			//log
		}
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		ConnectionPool.getInstance().dispose();
	}
}
