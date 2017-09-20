package db.entity;

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
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", cellphone=" + cellphone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (cellphone == null) {
			if (other.cellphone != null)
				return false;
		} else if (!cellphone.equals(other.cellphone))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
