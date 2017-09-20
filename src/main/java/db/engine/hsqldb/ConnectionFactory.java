package db.engine.hsqldb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.engine.DbProperty;

/**
 * Connection Factory from HSQLDB (HyperSQL Database)
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
		Class.forName(HSQLDBProperties.getPropery(DbProperty.CLASS));
		return DriverManager.getConnection(
				HSQLDBProperties.getPropery(DbProperty.CONNECTION_STRING), 
				HSQLDBProperties.getPropery(DbProperty.USER), 
				HSQLDBProperties.getPropery(DbProperty.PASSWORD));
	}
}
