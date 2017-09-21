package db.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String cellphone;

	public Contact() {
	}

	public Contact(int id, String name, String email, String cellphone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cellphone = cellphone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("ID", this.id)
				.append("Name", this.name)
				.append("E-mail", this.email)
				.append("Cellphone", this.cellphone)
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.id)
				.append(this.name)
				.append(this.email)
				.append(this.cellphone)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Contact == false) { return false; }
		if (obj == this) { return true; }
		final Contact other = (Contact) obj;
		return new EqualsBuilder()
				.append(this.id, other.id)
				.append(this.name, other.name)
				.append(this.email, other.email)
				.append(this.cellphone, other.cellphone)
				.isEquals();
	}
}
