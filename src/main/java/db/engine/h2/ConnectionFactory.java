package db.engine.h2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.engine.DbProperties;
import db.engine.DbProperty;
import db.engine.Engine;

/**
 * Connection Factory from H2
 * 
 * @author henbreda
 * @version 1.00
 * @since 2017-09-13
 */
public class ConnectionFactory {

	/**
	 * Generation connection from DB
	 * 
	 * @return DB Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		DbProperties.setEngine(Engine.DERBY);
		Class.forName(DbProperties.getPropery(DbProperty.CLASS));
		return DriverManager.getConnection(
				DbProperties.getPropery(DbProperty.CONNECTION_STRING), 
				DbProperties.getPropery(DbProperty.USER), 
				DbProperties.getPropery(DbProperty.PASSWORD));
	}
}