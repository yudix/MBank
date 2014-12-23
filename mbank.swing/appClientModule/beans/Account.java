package beans;

import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1428395474767324506L;
	private long accountID;
	private long clientID;
	private double balance;
	private double creditLimit;
	private String comment;

	public Account() {
		super();
	}

	public Account(long clientID, double balance, double creditLimit,
			String comment) {
		super();
		this.clientID = clientID;
		this.balance = balance;
		this.creditLimit = creditLimit;
		this.comment = comment;
	}

	public Account(long accountID, long clientID, double balance,
			double creditLimit, String comment) {
		super();
		this.accountID = accountID;
		this.clientID = clientID;
		this.balance = balance;
		this.creditLimit = creditLimit;
		this.comment = comment;
	}

	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}

	public long getClientID() {
		return clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", clientID=" + clientID
				+ ", balance=" + balance + ", creditLimit=" + creditLimit
				+ ", comment=" + comment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountID ^ (accountID >>> 32));
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
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		return true;
	}

}
