package action;

import helpers.MBankException;

import java.util.List;

import beans.Account;
import beans.Activity;
import beans.Client;
import beans.Deposit;
import beans.Property;

public interface ClientActionInterface {

	public List<Activity> viewClientActivities()
			throws MBankException;

	public String viewSystemProperty(String prop_key)
			throws MBankException;

	public List<Deposit> viewClientDeposits()
			throws MBankException;

	public Account viewAccountDetails()
			throws MBankException;

	public Client viewClientdetails()
			throws MBankException;

	public Deposit preOpenDeposit(long depositId )
			throws MBankException;

	public Deposit createNewDeposit(double initialDeposit,
			 int monthOfDeposit, int yearsOfDeposit) throws MBankException;

	public Account depositToAccount( double depositAmount)
			throws MBankException;

	public Account withdrawFromAccount( double withdraw)
			throws MBankException;

	public Client updateClientDetails(String address,
			String email, String phone) throws MBankException;

	List<Property> viewSystemProperties() throws MBankException;
	

}
