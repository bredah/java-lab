package db.engine.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.Helper;
import db.dao.ContactDAOImp;
import db.entity.Contact;

public class ContactDAOImpWithDbUnitITest extends DatabaseTestCase
{
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(new ConnectionFactory().getConnection());
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(Helper.getTestResources() + "addressbook.xml"));
		// return new StreamingDataSet(new FlatXmlProducer(new
		// InputSource(Helper.getTestResources() + "addressbook.xml")));
	}
	
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}
	
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
	
	@Override
	protected void setUpDatabaseConfig(DatabaseConfig config) {
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
	}
	
	@Test
	public void testCreate() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(10, "user_10", "user_10@email.com", "+551190001110");
		boolean resultCreate = new ContactDAOImp(new ConnectionFactory().getConnection()).create(contact);
		Assert.assertTrue(String.format("Not possible to add a new contact on database: %s", contact.toString()),
				resultCreate);
	}
	
	@Test(expected = SQLException.class)
	public void testCreateException() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(10, "user_10", "user_10@email.com", "+551190001110");
		// Generate connection with error
		Connection connection = new ConnectionFactory().getConnection();
		connection.close();
		// Validate exception
		try {
			new ContactDAOImp(connection).create(contact);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(String.format("Could not insert a new  contact: %s", contact.toString()),
					e.getMessage());
		}
	}
	
	@Test
	public void testRead() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(11, "user_11", "user_11@email.com", "+551190001111");
		Contact resultRead = new ContactDAOImp(new ConnectionFactory().getConnection()).read(contact.getCellphone());
		Assert.assertNotNull(resultRead);
		Assert.assertEquals(contact, resultRead);
	}
	
	@Test(expected = SQLException.class)
	public void testReadException() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(11, "user_11", "user_11@email.com", "+551190001111");
		// Generate connection with error
		Connection connection = new ConnectionFactory().getConnection();
		connection.close();
		// Validate exception
		try {
			new ContactDAOImp(connection).read(contact.getCellphone());
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(String.format("Could not retrive a contact with cellphone: %s", contact.getCellphone()),
					e.getMessage());
		}
	}
	
	@Test
	public void testUpdate() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(12, "user_12", "", "+551112");
		boolean resultUpdate = new ContactDAOImp(new ConnectionFactory().getConnection()).update(contact);
		Assert.assertTrue(String.format("Not possible update the contact on database: %s", contact.toString()),
				resultUpdate);
	}
	
	@Test(expected = SQLException.class)
	public void testUpdateException() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(12, "user_12", "", "+551112");
		// Generate connection with error
		Connection connection = new ConnectionFactory().getConnection();
		connection.close();
		// Validate exception
		try {
			new ContactDAOImp(connection).update(contact);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(String.format("Could not modify contact record: %s", contact.toString()),
					e.getMessage());
		}
	}
	
	@Test
	public void testUpdateAndRead() throws SQLException, ClassNotFoundException, IOException {
		Contact contactOld = new Contact(13, "user_13", "user_13@email.com", "+551190001113");
		Contact contactNew = new Contact(13, "13", "user@email.com", "+551191113");
		// Validate: Update
		boolean resultUpdate = new ContactDAOImp(new ConnectionFactory().getConnection()).update(contactNew);
		Assert.assertTrue(String.format("Not possible update the contact on database: %s", contactNew.toString()),
				resultUpdate);
		// Validate: Contact data on database
		Contact resultRead = new ContactDAOImp(new ConnectionFactory().getConnection()).read(contactNew.getCellphone());
		Assert.assertNotNull(resultRead);
		Assert.assertNotEquals(contactOld, resultRead);
		Assert.assertEquals(contactNew, resultRead);
	}
	
	@Test
	public void testDelete() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(14, "user_14", "user_14@email.com", "+551190001114");
		boolean result = new ContactDAOImp(new ConnectionFactory().getConnection()).delete(contact);
		Assert.assertTrue(String.format("Not possible to delete the contact: %s", contact.toString()), result);
	}
	
	@Test(expected = SQLException.class)
	public void testDeleteException() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(14, "user_14", "user_14@email.com", "+551190001114");
		// Generate connection with error
		Connection connection = new ConnectionFactory().getConnection();
		connection.close();
		// Validate exception
		try {
			new ContactDAOImp(connection).delete(contact);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(String.format("Could not remove contact record: %s", contact.toString()),
					e.getMessage());
		}
	}
}
