package beans;

import helpers.DepositType;

import java.io.Serializable;
import java.util.Date;

public class Deposit implements Serializable {

	private static final long serialVersionUID = 2390230872230469743L;
	
	private long depositID;
	private long clientID;
	private double balance;
	private DepositType depositType;
	private double estimatedBalance;
	private Date openingDate;
	private Date closingDate;

	public Deposit(long depositID, long clientID, double balance,
			DepositType depositType, double estimatedBalance, Date openingDate,
			Date closingDate) {
		super();
		this.depositID = depositID;
		this.clientID = clientID;
		this.balance = balance;
		this.depositType = depositType;
		this.estimatedBalance = estimatedBalance;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
	}

	public Deposit(long clientID, double balance, DepositType depositType,
			double estimatedBalance, Date openingDate, Date closingDate) {
		super();
		this.clientID = clientID;
		this.balance = balance;
		this.depositType = depositType;
		this.estimatedBalance = estimatedBalance;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
	}

	public Deposit() {
		super();
	}

	public long getDepositID() {
		return depositID;
	}

	public void setDepositID(long depositID) {
		this.depositID = depositID;
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

	public DepositType getDepositType() {
		return depositType;
	}

	public void setDepositType(DepositType depositType) {
		this.depositType = depositType;
	}

	public double getEstimatedBalance() {
		return estimatedBalance;
	}

	public void setEstimatedBalance(double estimatedBalance) {
		this.estimatedBalance = estimatedBalance;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	@Override
	public String toString() {
		return "Deposit [depositID=" + depositID + ", clientID=" + clientID
				+ ", balance=" + balance + ", depositType=" + depositType
				+ ", estimatedBalance=" + estimatedBalance + ", openingDate="
				+ openingDate + ", closingDate=" + closingDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (depositID ^ (depositID >>> 32));
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
		Deposit other = (Deposit) obj;
		if (depositID != other.depositID)
			return false;
		return true;
	}
}
