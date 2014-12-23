package beans;

import java.io.Serializable;

import helpers.ClientType;

public class Client implements Serializable {

	private static final long serialVersionUID = -5240413907181927005L;
	
	private long clientID;
	private String clientName;
	private String password;
	private ClientType clientType;
	private String address;
	private String email;
	private String phone;
	private String comment;

	public Client() {
		super();
	}

//	public Client(String clientName, String password, ClientType clientType,
//			String address, String email, String phone, String comment) {
//		super();
//		this.clientName = clientName;
//		this.password = password;
//		this.clientType = clientType;
//		this.address = address;
//		this.email = email;
//		this.phone = phone;
//		this.comment = comment;
//	}

	public Client(long clientID, String clientName, String password,
			ClientType clientType, String address, String email, String phone,
			String comment) {
		super();
		this.clientID = clientID;
		this.clientName = clientName;
		this.password = password;
		this.clientType = clientType;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public long getClientID() {
		return clientID;
	}

	public Client(String clientName, String password, String address, String email,
		String phone, String comment) {
	super();
	this.clientName = clientName;
	this.password = password;
	this.address = address;
	this.email = email;
	this.phone = phone;
	this.comment = comment;
}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ClientType getType() {
		return clientType;
	}

	public void setType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComments() {
		return comment;
	}

	public void setComments(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", clientName=" + clientName
				+ ", password=" + password + ", clientType=" + clientType + ", address="
				+ address + ", email=" + email + ", phone=" + phone
				+ ", comment=" + comment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (clientID ^ (clientID >>> 32));
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
		Client other = (Client) obj;
		if (clientID != other.clientID)
			return false;
		return true;
	}

}
