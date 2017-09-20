package db.engine.derby;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.engine.DbProperty;

/**
 * Connection Factory from Java DB (Derby)
 * 
 * @author henbreda
 * @version 1.00
 * @since 2017-09-13
 */
public class ConnectionFactory
{
	
	/**
	 * Generation connection from DB
	 * 
	 * @return DB Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Class.forName(DerbyProperties.getPropery(DbProperty.CLASS));
		return DriverManager.getConnection(DerbyProperties.getPropery(DbProperty.CONNECTION_STRING));
	}
}
