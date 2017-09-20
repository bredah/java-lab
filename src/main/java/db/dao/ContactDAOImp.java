package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.entity.Contact;

public class ContactDAOImp implements ContactDAO
{
	
	private Connection connection;
	private PreparedStatement stmt;
	
	public ContactDAOImp(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public boolean create(Contact contact) throws SQLException {
		String query = "INSERT INTO adressbook (name, email, cellphone) VALUES (?, ?, ?);";
		try {
			stmt = this.connection.prepareStatement(query);
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getEmail());
			stmt.setString(3, contact.getCellphone());
			if (stmt.executeUpdate() < 1) { return false; }
		} catch (SQLException e) {
			throw new SQLException("Could not insert a new  contact: " + contact.toString(), e);
		} finally {
			closeConnection();
		}
		return true;
	}
	
	@Override
	public Contact read(String cellphone) throws SQLException {
		Contact contact = null;
		String query = "SELECT id, name, email, cellphone FROM adressbook WHERE cellphone = ?;";
		try {
			stmt = this.connection.prepareStatement(query);
			stmt.setString(1, cellphone);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setCellphone(rs.getString("cellphone"));
			}
		} catch (SQLException e) {
			throw new SQLException("Could not retrive a contact with cellphone: " + cellphone, e);
		} finally {
			closeConnection();
		}
		return contact;
	}
	
	@Override
	public boolean update(Contact contact) throws SQLException {
		String query = "UPDATE adressbook SET name = ?, email = ?, cellphone = ? WHERE id = ?;";
		try {
			stmt = this.connection.prepareStatement(query);
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getEmail());
			stmt.setString(3, contact.getCellphone());
			stmt.setInt(4, contact.getId());
			if (stmt.executeUpdate() < 1) { return false; }
		} catch (SQLException e) {
			throw new SQLException("Could not modify contact record: " + contact.toString(), e);
		} finally {
			closeConnection();
		}
		return true;
	}
	
	@Override
	public boolean delete(Contact contact) throws SQLException {
		String query = "DELETE FROM adressbook WHERE id = ?;";
		try {
			stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, contact.getId());
			if (stmt.executeUpdate() < 1) { return false; }
		} catch (SQLException e) {
			throw new SQLException("Could not remove contact record: " + contact.toString(), e);
		} finally {
			closeConnection();
		}
		return true;
	}
	
	/**
	 * Create default table
	 * 
	 * @throws SQLException
	 */
	public void createTable() throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("CREATE TABLE addressbook (");
		query.append(" id int(11) NOT NULL AUTO_INCREMENT,");
		query.append(" name varchar(50) NOT NULL,");
		query.append(" email varchar(50),");
		query.append(" cellphone varchar(15) NOT NULL,");
		query.append(" PRIMARY KEY (id))");
		try {
			// Execute query
			stmt = this.connection.prepareStatement(query.toString());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(String.format("QUERY: %s", query.toString()), e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void closeConnection() throws SQLException {
		if(stmt != null) {
			stmt.close();
		}
		if(connection != null) {
			connection.close();
		}		
	}
	
}
