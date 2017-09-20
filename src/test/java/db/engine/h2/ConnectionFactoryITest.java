package db.engine.h2;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.engine.h2.ConnectionFactory;

public class ConnectionFactoryITest {

	private Connection connection;

	@Before
	public void setUp() throws SQLException, ClassNotFoundException, IOException {
		connection = new ConnectionFactory().getConnection();
	}

	@After
	public void tearDown() throws SQLException {
		connection.close();
	}

	/**
	 * Check if it's possible to create a connection
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @see db.engine.derby.ConnectionFactory
	 */
	@Test
	public void testConnection() throws ClassNotFoundException, SQLException, IOException {
		connection = new ConnectionFactory().getConnection();
		assertTrue(connection.isValid(1));
	}

}