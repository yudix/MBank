package db.managers;

import helpers.MBankException;

import java.util.List;

import beans.Deposit;

public interface DepositManager {
	Deposit addDeposit(Deposit deposit) 
			throws MBankException;

	void removeDeposit(long depositID) 
			throws MBankException;

	Deposit viewDeposit(long deposit_id)
			throws MBankException;
	
	Deposit viewDeposit(long clientID, long deposit_id)
			throws MBankException;

	List<Deposit> viewCilentDeposits(long clientID) 
			throws MBankException;

	List<Deposit> viewAllDeposits() 
			throws MBankException;

}
