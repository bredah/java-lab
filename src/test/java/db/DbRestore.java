package db;

import java.io.IOException;

import org.flywaydb.core.Flyway;

import db.engine.DbProperties;
import db.engine.DbProperty;
import db.engine.Engine;

/**
 * Restore a database using the FlyWay library
 * 
 * @author henbreda
 * @version 1.00
 * @since 2017-09-21
 */
public class DbRestore
{
	/**
	 * Execute DUMP file and migrate/restore database
	 * 
	 * <pre>
	 * {@code
	 * // Example:
	 * new DbRestore().restore();
	 * }
	 * </pre>
	 * 
	 * @param engine
	 *            TODO
	 * 
	 * @throws IOException
	 *             Using this exception when not found the properties file
	 */
	public void restore(Engine engine) throws IOException {
		// Set engine
		if (engine == null) { throw new RuntimeException("Need to setup database engine"); }
		DbProperties.setEngine(engine);
		// Run FlyWay
		restore();
	}
	
	/**
	 * Execute DUMP file and migrate/restore database
	 * 
	 * <pre>
	 * {@code
	 * // Example:
	 * new DbRestore().restore();
	 * }
	 * </pre>
	 * 
	 * @throws IOException
	 *             Using this exception when not found the properties file
	 */
	public void restore() throws IOException {
		// Set engine
		if (DbProperties.getEngine() == null) { throw new RuntimeException("Need to setup database engine"); }
		// Run FlyWay
		Flyway flyway = new Flyway();
		flyway.setDataSource(
				// Connection String
				DbProperties.getPropery(DbProperty.CONNECTION_STRING),
				// Access: User
				DbProperties.getPropery(DbProperty.USER),
				// Access: Password
				DbProperties.getPropery(DbProperty.PASSWORD));
		flyway.setLocations("classpath:db/migration");
		flyway.clean();
		// Migrate metadata to database
		flyway.migrate();
	}
}
