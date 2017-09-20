package db.engine.h2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.engine.DbProperty;

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
		Class.forName(H2Properties.getPropery(DbProperty.CLASS));
		return DriverManager.getConnection(
				H2Properties.getPropery(DbProperty.CONNECTION_STRING), 
				H2Properties.getPropery(DbProperty.USER), 
				H2Properties.getPropery(DbProperty.PASSWORD));
	}
}