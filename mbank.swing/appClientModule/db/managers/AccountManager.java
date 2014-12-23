package db.managers;

import helpers.MBankException;

import java.util.List;

import beans.Account;

public interface AccountManager {
	
	Account addAccount(Account account) 
			throws MBankException;

	Account viewAccountDetails(long clientID) 
			throws MBankException;

	void updateAccountDetails(Account account) 
			throws MBankException;

	void removeAccount(long clientID) 
			throws MBankException;

	List<Account> viewAllAccounts() 
			throws MBankException;
}
