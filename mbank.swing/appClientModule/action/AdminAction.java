package action;

import helpers.ClientType;
import helpers.DepositType;
import helpers.MBankException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Account;
import beans.Activity;
import beans.Client;
import beans.Deposit;
import beans.Property;

public class AdminAction extends Action implements AdminActionInterface {

	private Connection connection;

	public AdminAction(Connection connection) {
		super(connection);
		this.setConnection(connection);
	}

	

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Client addNewClient
		(String clientName, String password,
			String address, String phone, String email, String comment,
				double initialDeposit) throws MBankException {
		Client client = new Client();
		// setting details
		client.setClientName(clientName);
		client.setPassword(password);
		client.setAddress(address);
		client.setPhone(phone);
		client.setEmail(email);
		client.setComments(comment);
		
		// setting the Type according to initial deposit
		try {
			// transaction started
			getConnection().setAutoCommit(false);
			// Client setting Type
			ClientType type = initialDepositChecker(client, initialDeposit);
			client.setType(type);
			// inserting to database and getting clientID
			client = clientManager.addClient(client);

			createNewAccount(client, initialDeposit);

			getConnection().commit();
			return client;

		} catch (MBankException e) {
			try {
				getConnection().rollback();
				throw e;
			} catch (SQLException e1) {
				throw new MBankException(e1.getMessage());
			}
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}

	}
	
	private ClientType initialDepositChecker(Client client,
			double initialDeposit) throws MBankException {
		ClientType clientType;
		try {
			if (initialDeposit < propertiesUtil.getRegularDepositRate()) {
				throw new MBankException("initial deposit of :"
						+ initialDeposit + " is illegal!");
			} else if (initialDeposit < propertiesUtil.getGoldDepositRate()) {
				clientType = ClientType.REGULAR;
			} else if (initialDeposit < propertiesUtil.getPlatinumDepositRate()) {
				clientType = ClientType.GOLD;
			} else {
				clientType = ClientType.PLATINUM;
			}
			return clientType;
		} catch (MBankException e) {
			throw new MBankException(e.getMessage());
					//"Error, Sorry can not set the Client Type");
		}
	}
	@Override
	public void createNewAccount(Client client, double initialDeposit) throws MBankException {
		Account account = new Account();
		
		// 1 setting balance
		account.setBalance(initialDeposit);
		// 2 setting credit limit
		try {
				if (client.getType().equals(ClientType.REGULAR)) {
					account.setCreditLimit(propertiesUtil.getRegularCreditLimit());
				} else if (client.getType().equals(ClientType.GOLD)) {
					account.setCreditLimit(propertiesUtil.getGoldCreditLimit());
				} else if (client.getType().equals(ClientType.PLATINUM)) {
					account.setCreditLimit(-1);
				} 
			} catch (MBankException e) {
						throw new MBankException("Sorry can not set the Account credit limit");
			}
		// 3 setting accounts client id
		account.setClientID(client.getClientID());
		//4 setting comment
		account.setComment("no comments");
		try {
			accountManager.addAccount(account);
		} catch (MBankException e) {
			throw e;
		}		
	}
	
	
	///2
	@Override
	public void updateClientDetails(long clientID, ClientType type,
			String comments) throws MBankException {// parametrs for admin...
													// Client client) {
		Client client = new Client();
		try {
			getConnection().setAutoCommit(false);

			client = clientManager.viewClientDetails(clientID);

			client.setType(type);
			client.setComments(comments);

			clientManager.updateClientDetails(client);
			getConnection().commit();
		} catch (MBankException e) {
			try {
				getConnection().rollback();
			} catch (SQLException e1){}
			throw e;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}
	@Override
	public double removeClient(long clientID) throws MBankException {
		try {
			getConnection().setAutoCommit(false);
 			
			removeAllClientDeposits(clientID);
			double balance = removeAccount(clientID);
			
			clientManager.removeClient(clientID);
			System.out.println("Client "+clientID+" has removed");
			getConnection().commit();
			return balance;
		} catch (MBankException e) {
			try {
				getConnection().rollback();
			} catch (SQLException e1) {}
			throw e;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}
	@Override
	public void removeAllClientDeposits(long clientID)
			throws MBankException {
		List<Deposit> deposits;
		try{
			getConnection().setAutoCommit(false);
			
			Account account = accountManager.viewAccountDetails(clientID);
		
			double preOpenFee = propertiesUtil.getPreOpenFee();
			
			deposits = depositManager.viewCilentDeposits(clientID);
			double allDepositsBalance = 0;
				for (Deposit d : deposits) {
					allDepositsBalance += d.getBalance();
				}
			
			/**
			 * calculate all the deposits balance together and charging by pre opening fee
			 * because if its the openning time there is automatic opening with another fee
			 *if the balance is 1000
			 *and the percentage of the preOpenFee is 1%
			 *we have to decrease 1% of a 1000 from the balance 1000
			 *the math is (1000 - ( 1000* 0.01)
			 */
			double balanceAfterPreOpenFee = allDepositsBalance - ( allDepositsBalance * preOpenFee );
			
			// update the current balance of the account + 
			// the blance from the deposits after the pre open fee
			account.setBalance(account.getBalance() + balanceAfterPreOpenFee);
			
			//update account
			accountManager.updateAccountDetails(account);
			
			//removing all deposits
			for (Deposit d : deposits) {
				depositManager.removeDeposit(d.getDepositID());
				System.out.println("Deposit Number"+d.getDepositID()+" has removed");
			}
			getConnection().commit();
		} catch (MBankException e) {
			try {
				getConnection().rollback();
			} catch (SQLException e1) {
			}
			throw e;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}
	
	

	@Override
	public double removeAccount(long clientID) throws MBankException {

		try {
		Account account = accountManager.viewAccountDetails(clientID);
		
		Activity activity = new Activity();
			if (account.getBalance() < 0) {
				activity.setClientID(clientID);
				//(account.getClient_id());
				activity.setAmount(account.getBalance());
				activity.setActivityDate(new java.util.Date());
				activity.setCommission(propertiesUtil
						.getCommissionRate());
				activity.setDescription("Commission charged due to negative balance account on client removal");

				activityManager.addActivity(activity);
			}
	
		accountManager.removeAccount(clientID);
		System.out.println("Account of Client "+clientID+" has removed");
		return account.getBalance();
		} catch (MBankException e) {
			throw e;
		}
	}

	@Override
	public Client viewClientDetails(long clientID) throws MBankException  {
		Client client = new Client();
		try {
			client = clientManager.viewClientDetails(clientID);
			return client;
		} catch (MBankException e) {
			throw new MBankException("Can not view the client details");
		}
	}

	@Override
	public List<Client> viewAllClients() throws MBankException {
		return clientManager.viewAllClients();
	}

	@Override
	public Account viewAccount(long accountID) throws MBankException {
		return	accountManager.viewAccountDetails(accountID);
	}

	@Override
	public List<Account> viewAllAccounts() throws MBankException {
		return accountManager.viewAllAccounts();
	}

	@Override
	public List<Deposit> viewClientDeposits(long clientID) throws MBankException {
		return depositManager.viewCilentDeposits(clientID);
	}

	@Override
	public List<Deposit> viewAllDeposits() throws MBankException {
		return depositManager.viewAllDeposits();
	}

	@Override
	public List<Activity> viewClientActivities(long clientID) throws MBankException{
		return activityManager.viewClientActivities(clientID);
	}

	@Override
	public List<Activity> viewAllActivities() throws MBankException{
		return activityManager.viewAllActivities();
	}

	@Override
	public void updateSystemProperty(Property property)throws MBankException {
		propertiesManager.updateProperty(property);
	}

	@Override
	public List<Property> viewSystemProperties() throws MBankException{
		return propertiesManager.viewAllProperties();
	}
	
	@Override
	public Property viewProperty(String propKey) throws MBankException {
		return propertiesManager.viewProperty(propKey);
	}



	@Override
	public void preOpenDeposit(long depositID, long clientID)
			throws MBankException {
		try {
			getConnection().setAutoCommit(false);

			Deposit deposit = depositManager.viewDeposit(clientID, depositID);
			Account account = accountManager.viewAccountDetails(clientID);

			double balance = 0;
			double preOpenFee = 0;

			if (deposit.getDepositType().equals(DepositType.SHORT)) {
				throw new MBankException(
						"Your Deposit Type is SHORT\n you can not pre Open the Deposit");
			} else if (deposit.getDepositType().equals(DepositType.LONG)) {
				balance = deposit.getBalance();
				preOpenFee = propertiesUtil.getPreOpenFee();
			}
			
			/**
			 * calculate the balance after fee (if 1000 is the balance and its
			 * regularDepositCommission is 1.5%) 1000 - (1000*1.5%) = 850
			 */
			double balanceAfterPreOpenFee = balance - (balance * preOpenFee);

			account.setBalance(account.getBalance() + balanceAfterPreOpenFee);

			accountManager.updateAccountDetails(account);

			depositManager.removeDeposit(depositID);
			getConnection().commit();
		} catch (MBankException e) {
			try {
				getConnection().rollback();
			} catch (SQLException e1) {
				throw new MBankException(e.getMessage());
			}
			throw e;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
		
	}
	


}
