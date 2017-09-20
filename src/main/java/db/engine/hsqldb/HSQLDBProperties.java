package db.engine.hsqldb;

import java.io.IOException;

import db.engine.DbProperties;
import db.engine.DbProperty;

/**
 * Retrieving properties from HSQLDB DB
 * 
 * @author henbreda
 * @since 2017-09-18
 * @version 1.00
 */
public class HSQLDBProperties
{
	private static final String DB_NAME = "HSQLDB";
	
	/**
	 * Retrieving properties from H2 DB
	 */
	private HSQLDBProperties()
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
