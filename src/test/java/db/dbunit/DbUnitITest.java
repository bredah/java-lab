package db.dbunit;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import core.Helper;
import db.dao.ContactDAOImp;
import db.engine.DbProperties;
import db.engine.DbProperty;
import db.engine.derby.ConnectionFactory;
import db.entity.Contact;

public class DbUnitITest
{
	private static Connection jdbcConnection;
	private static ContactDAOImp dao;

	@BeforeClass
	public static void setUpClass() throws ClassNotFoundException, IOException, SQLException, DatabaseUnitException {
		// Load driver class
		Class.forName(DbProperties.getPropery(DbProperty.CLASS));
		// Get the connection from factory
		jdbcConnection = new ConnectionFactory().getConnection();
		// FIRST: Create table
		dao = new ContactDAOImp(jdbcConnection);
		// Create a connection for DbUnit
		IDatabaseConnection databaseConnection = new DatabaseConnection(jdbcConnection);
		// Read the XML file and load all data into database
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(Helper.getTestResources() + "addressbook.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
		// Close all connection from database
		databaseConnection.close();
		jdbcConnection.close();
	}
	
	@Test
	public void search() throws ClassNotFoundException, SQLException, IOException {
		Contact contact = new Contact(1, "XPTO", "xpto.mail.com", "+5511912341234");
		dao = new ContactDAOImp(jdbcConnection);
		Contact result = dao.read(contact.getCellphone());
		Assert.assertEquals(contact, result);
	}
}
