package db.dao;

import java.sql.SQLException;

import db.entity.Contact;

public interface ContactDAO {

	public boolean create(Contact contact) throws SQLException;

	public Contact read(String cellphone) throws SQLException;

	public boolean update(Contact contact) throws SQLException;

	public boolean delete(Contact contact) throws SQLException;
}
