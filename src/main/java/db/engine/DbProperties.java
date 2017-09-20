package db.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Properties;

import core.Helper;
import db.engine.DbProperty;

/**
 * Retrieving properties from DB
 * 
 * @author henbreda
 * @since 2017-09-18
 * @version 1.00
 */
public class DbProperties
{
	private static Properties prop;
	private static Engine engine;
	
	/**
	 * Retrieving properties from a specific Database
	 */
	private DbProperties()
	{
	}
	
	/**
	 * Set Engine
	 * @param engine
	 */
	public static void setEngine(Engine engine) {
		DbProperties.engine = engine;
	}
	
	/**
	 * Retrieve a specific property from DB
	 * 
	 * @param database
	 *            Database name
	 * @param property
	 *            DataBase property
	 * @return Property content value
	 * @throws IOException
	 */
	public static String getPropery(DbProperty property) throws IOException {
		switch (property)
		{
			case CLASS:
			case CONNECTION_STRING:
			case PASSWORD:
			case USER:
				if (prop == null) {
					prop = new Properties();
					prop.load(new FileInputStream(Helper.getMainResources() + "database.properties"));
				}
				return prop.getProperty(engine.toString().toLowerCase() + "." + property.toString().toLowerCase());
			default:
				throw new InvalidParameterException(
						String.format("This property '%s' not existe", property.toString()));
		}
	}
	
}
