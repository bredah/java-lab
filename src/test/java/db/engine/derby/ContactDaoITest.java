package db.engine.derby;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import db.dao.ContactDAO;
import db.dao.ContactDAOImp;
import db.entity.Contact;

public class ContactDaoITest {

	private Connection connection;
	private Contact contact;
	private ContactDAO dao;

	@Before
	public void setUpClass() throws Exception {
		connection = new ConnectionFactory().getConnection();
		dao = new ContactDAOImp(connection);
	}

//	@After
//	public static void tearDownClass() throws SQLException {	
//		connection.close();
//	}

	/**
	 * Create and Delete a table
	 * 
	 * @throws SQLException
	 */
	@Test
	public void tableCreateAndDrop() throws SQLException {
		// Logger.getGlobal().setLevel(Level.INFO);
		// Execute query
		Statement stmt = connection.createStatement();
		dao.createTable();
		stmt.executeUpdate("DROP TABLE  IF NOT EXISTS addressbook");
	}

	/**
	 * Create a new register
	 * 
	 * @throws SQLException
	 *             Throw when occurs a error from DAO
	 */
	public void insertValue() throws SQLException {
		contact = new Contact();
		contact.setName("XPTO");
		contact.setEmail("xpto@mail.com");
		contact.setCellphone("+5511912341234");

		assertTrue(dao.create(contact));
	}

	// private static void executeDbUnitPrepare() throws Exception {
	// final IDatabaseConnection conn = getConnection();
	// final IDataSet data = getDataSet();
	// try {
	// DatabaseOperation.CLEAN_INSERT.execute(conn, data);
	// } finally {
	// conn.close();
	// }
	// }
	//
	// private static IDataSet getDataSet() throws IOException, DataSetException {
	// return new FlatXmlDataSetBuilder().build(new
	// FileInputStream(Helper.getTestResource() + "addressbook.xml"));
	// }
	//
	// private static IDatabaseConnection getConnection()
	// throws ClassNotFoundException, DatabaseUnitException, SQLException {
	// return new DatabaseConnection(connection);
	// }
}
