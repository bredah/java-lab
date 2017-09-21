package db.engine.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import core.Helper;
import db.dao.ContactDAOImp;
import db.engine.DbProperties;
import db.engine.DbProperty;
import db.engine.Engine;
import db.entity.Contact;

public class DbUnitITest {

	private static Connection connection;
	private static Savepoint savepoint;
	private static IDatabaseConnection databaseConnection;

	@BeforeClass
	public static void setUpClass() throws ClassNotFoundException, IOException, SQLException, DatabaseUnitException {
		DbProperties.setEngine(Engine.MYSQL);
		// Load driver class
		Class.forName(DbProperties.getPropery(DbProperty.CLASS));
		connection = new ConnectionFactory().getConnection();
		connection.setAutoCommit(false);
		// new ContactDAOImp(connection).createTable();
		savepoint = connection.setSavepoint();
		databaseConnection = new DatabaseConnection(connection);
		databaseConnection.getConnection().setAutoCommit(true);
		// Set DataType and MetaData
		DatabaseConfig config = databaseConnection.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
		// Read the XML file and load all data into database
		IDataSet dataSet = new FlatXmlDataSetBuilder()
				.build(new FileInputStream(Helper.getTestResources() + "addressbook.xml"));
		DatabaseOperation.INSERT.execute(databaseConnection, dataSet);
	}

	@AfterClass
	public static void tearDownClass() throws SQLException, ClassNotFoundException, IOException {
		connection.rollback(savepoint);
		// Close all connection from database
		databaseConnection.close();		
		connection.close();
	}

	@Test
	public void search() throws ClassNotFoundException, SQLException, IOException {
		Contact contact = new Contact(1, "XPTO", "xpto@mail.com", "+5511912340001");
		Contact result = new ContactDAOImp(new ConnectionFactory().getConnection()).read(contact.getCellphone());
		Assert.assertEquals(contact, result);
	}
}
