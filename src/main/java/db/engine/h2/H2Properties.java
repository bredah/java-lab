package db.engine.h2;

import java.io.IOException;

import db.engine.DbProperties;
import db.engine.DbProperty;

/**
 * Retrieving properties from H2 DB
 * 
 * @author henbreda
 * @since 2017-09-18
 * @version 1.00
 */
public class H2Properties
{
	private static final String DB_NAME = "H2";
	
	/**
	 * Retrieving properties from H2 DB
	 */
	private H2Properties()
	{
	}
	
	/**
	 * Retrieve a specific property from DB
	 * 
	 * @param property
	 *            DataBase property
	 * @return Property content value
	 * @throws IOException
	 */
	public static String getPropery(DbProperty property) throws IOException {
		return DbProperties.getPropery(DB_NAME, property);
	}
	
}
