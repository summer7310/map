package org.map.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;

public class ConnectionFactory {
	
	private static boolean hasRegistered = false;
	/**
	 * 从连接池中获得数据库连接.
	 * 使用proxool连接池，直接调用此方法即可获得数据库的连接
	 * @return  Connection
	 * @throws SQLException
	 */
	public static synchronized Connection getConnnection(String alias){
		Connection connection = null;
		if(hasRegistered == false){
			try {
				PropertyConfigurator.configure(new ConnectionFactory().getClass().getResource("/").getPath().replaceAll("%20", " ") + "org/map/database/config"); 
				hasRegistered = true;
				
				connection = DriverManager.getConnection(alias);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				connection = DriverManager.getConnection(alias);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

}
