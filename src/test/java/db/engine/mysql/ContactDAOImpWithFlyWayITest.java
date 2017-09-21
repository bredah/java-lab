package db.engine.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import db.DbRestore;
import db.dao.ContactDAOImp;
import db.engine.DbProperties;
import db.engine.Engine;
import db.entity.Contact;

public class ContactDAOImpWithFlyWayITest
{
	@BeforeClass
	public static void setpUpClass() throws IOException {
		// Restore database
		DbProperties.setEngine(Engine.MYSQL);
		new DbRestore().restore(DbProperties.getEngine());
		// Set database engine
		DbProperties.setEngine(Engine.MYSQL);
	}
	
	@Test
	public void create() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(6, "XPTO", "xpto@email.com", "+5511999994444");
		boolean resultCreate = new ContactDAOImp(new ConnectionFactory().getConnection()).create(contact);
		Assert.assertTrue(String.format("Not possible to add a new contact on database: %s", contact.toString()),
				resultCreate);
	}
	
	@Test(expected = SQLException.class)
	public void createException() throws SQLException, ClassNotFoundException, IOException {
		// Generate connection with error
		Connection connection = new ConnectionFactory().getConnection();
		connection.close();
		// Generate exception
		new ContactDAOImp(connection).create(new Contact(6, "XPTO", "xpto@email.com", "+5511999994444"));
	}
	
	@Test
	public void read() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(1, "user_01", "user_01@email.com", "+551190001101");
		Contact resultRead = new ContactDAOImp(new ConnectionFactory().getConnection()).read(contact.getCellphone());
		Assert.assertNotNull(resultRead);
		Assert.assertEquals(contact, resultRead);
	}
	
	@Test
	public void update() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(2, "user", "", "+5511");
		boolean resultUpdate = new ContactDAOImp(new ConnectionFactory().getConnection()).update(contact);
		Assert.assertTrue(String.format("Not possible update the contact on database: %s", contact.toString()),
				resultUpdate);
	}
	
	@Test
	public void updateAndRead() throws SQLException, ClassNotFoundException, IOException {
		Contact contactOld = new Contact(2, "user_04", "user_04@email.com", "+551190001102");
		Contact contactNew = new Contact(2, "user", "", "+551102");
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
	public void delete() throws SQLException, ClassNotFoundException, IOException {
		Contact contact = new Contact(3, "user_03", "user_03@email.com", "+551190001103");
		boolean result = new ContactDAOImp(new ConnectionFactory().getConnection()).delete(contact);
		Assert.assertTrue(String.format("Not possible to delete the contact: %s", contact.toString()), result);
	}
}
