package action;

import helpers.ClientType;
import helpers.MBankException;

import java.util.List;

import beans.Account;
import beans.Activity;
import beans.Client;
import beans.Deposit;
import beans.Property;

public interface AdminActionInterface {

	public Client addNewClient(String clientName, String password,
			String address, String phone, String email, String comment,
			double initialDeposit) throws MBankException;

	public void updateClientDetails(long clientID, ClientType type,
			String comments) throws MBankException;

	public double removeClient(long clientID) throws MBankException;

	public void createNewAccount(Client client, double initialDeposit)
			throws MBankException;

	public double removeAccount(long clientID) throws MBankException;

	public void preOpenDeposit(long clientID, long depositID)
			throws MBankException;

	public void removeAllClientDeposits(long clientID) throws MBankException;

	public void updateSystemProperty(Property property) throws MBankException;

	public Client viewClientDetails(long clientID) throws MBankException;

	public List<Client> viewAllClients() throws MBankException;

	public Account viewAccount(long accountID) throws MBankException;

	public List<Account> viewAllAccounts() throws MBankException;

	public List<Deposit> viewClientDeposits(long clientID)
			throws MBankException;

	public List<Deposit> viewAllDeposits() throws MBankException;

	public List<Activity> viewClientActivities(long clientID)
			throws MBankException;

	public List<Activity> viewAllActivities() throws MBankException;

	public List<Property> viewSystemProperties() throws MBankException;

	public Property viewProperty(String propKey) throws MBankException;
}
