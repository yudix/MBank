package action;

import helpers.ClientType;
import helpers.DepositType;
import helpers.MBankException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import beans.Account;
import beans.Activity;
import beans.Client;
import beans.Deposit;
import beans.Property;

public class ClientAction extends Action implements ClientActionInterface {

	private Connection connection;

	private Client client;
	private long clientID;

	// CTOR
	public ClientAction (Connection connection,Client client) {
		super(connection);
		System.out.println("ClientAction.ClientAction()");
		this.setConnection(connection);
		this.client = client;
		this.clientID = client.getClientID();
		System.out.println(this);
	}
	
	/**
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * 
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Activity> viewClientActivities() throws MBankException {
		return activityManager.viewClientActivities(clientID);
	}
	@Override
	public List<Property> viewSystemProperties() throws MBankException{
		return propertiesManager.viewAllProperties();
	}
	@Override
	public String viewSystemProperty(String prop_key) throws MBankException {
		if (prop_key.equals("admin_password") || prop_key.equals("admin_username")) {
			throw new MBankException(
					"you are not allowed to wach it. that an admin property only");
		}
		try {
			Property property = propertiesManager.viewProperty(prop_key);
			return property.getPropValue();
		} catch (MBankException e) {
			throw e;
		}
	}

	@Override
	public List<Deposit> viewClientDeposits() throws MBankException {
		return depositManager.viewCilentDeposits(clientID);
	}

	@Override
	public Account viewAccountDetails() throws MBankException {
		return accountManager.viewAccountDetails(clientID);
	}

	@Override
	public Client viewClientdetails() throws MBankException {
		return clientManager.viewClientDetails(clientID);
	}

	@Override
	public Deposit preOpenDeposit(long depositId) throws MBankException {

		try {
			getConnection().setAutoCommit(false);
			Deposit deposit = depositManager.viewDeposit(depositId);

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

			depositManager.removeDeposit(depositId);
			getConnection().commit();
			return deposit;
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

	
	@Override
	public Deposit createNewDeposit(double initialDeposit, int monthOfDeposit,
			int yearsOfDeposit) throws MBankException {

		try {
			getConnection().setAutoCommit(false);
			Account account = accountManager.viewAccountDetails(clientID);
			// getting the initial deposit of deposit from the account
			account.setBalance(account.getBalance() - initialDeposit);
			// checking thats there is no exception from the credit limit
			if (!client.getType().equals(ClientType.PLATINUM)) {
				if (account.getBalance() < -account.getCreditLimit()) {
					throw new MBankException(
							"Your Credit limit not allowed you to create the Deposit");
				}
			}
			// setting the deposit-client_id
			Deposit deposit = new Deposit();
			deposit.setClientID(clientID);

			DateTime opening = new DateTime(new Date());
			DateTime closing = opening.plusMonths(monthOfDeposit).plusYears(
					yearsOfDeposit);

			int totalDaysOfDeposit = Days.daysBetween(opening, closing)
					.getDays();

			// check Deposit Time Legal
			checkDepositTimeLegal(deposit, totalDaysOfDeposit);

			// according the client type setting the Daily-interest-Rate
			double dailyInterestRatePercent = dailyInterestRateChecker(client);

			double estimatedBalance = estimatedBalance(initialDeposit,
					totalDaysOfDeposit, dailyInterestRatePercent);
			// set the estimate Balance
			deposit.setEstimatedBalance(estimatedBalance);
			// set the deposit balance
			deposit.setBalance(initialDeposit);
			// opening date
			deposit.setOpeningDate(opening.toDate());
			// closing date
			deposit.setClosingDate(closing.toDate());
			// creating the deposit

			accountManager.updateAccountDetails(account);
			depositManager.addDeposit(deposit);
			getConnection().commit();
			return deposit;
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
	
	private double estimatedBalance(double amount, int days, double dailyInterestRatePercent) {

		double dailyInterestRate = (amount * dailyInterestRatePercent) / 100;
		// IE 14$ * 365 days of deposit = 5,110$
		double totalInterest = dailyInterestRate * days;
		// IE 10,000$ + 5,110$ = 15,110$ == estimated balance
		return amount + totalInterest;

	}
	
	private void checkDepositTimeLegal(Deposit deposit, int totalDaysOfDeposit)
			throws MBankException {
		if (totalDaysOfDeposit < 1) {
			throw new MBankException("illegal deposit time");
		} else if (totalDaysOfDeposit <= 365) {
			deposit.setDepositType(DepositType.SHORT);
		} else
			deposit.setDepositType(DepositType.LONG);
	}

	private double dailyInterestRateChecker(Client client)
			throws MBankException {
		try {
			if (client.getType().equals(ClientType.REGULAR)) {
				return propertiesUtil.getRegularDailyInterest();
			} else if (client.getType().equals(ClientType.GOLD)) {
				return propertiesUtil.getGoldDailyInterest();
			} else if (client.getType().equals(ClientType.PLATINUM)) {
				return propertiesUtil.getPlatinumDailyInterest();
			} else {
				throw new MBankException("cannot get daiky interest rate");
			}

		} catch (MBankException e) {
			throw e;
		}
	}

	@Override
	public Account depositToAccount(double depositAmount) throws MBankException {
		// Account account = new Account();
		if (depositAmount <= 0)
			throw new MBankException(
					"error. ammount to deposit must be positive");

		try {
			getConnection().setAutoCommit(false);
			// getting the account
			Account account = accountManager.viewAccountDetails(clientID);
			// setting a new balance
			account.setBalance(account.getBalance()
					- (propertiesUtil.getCommissionRate()));
			// updating database
			accountManager.updateAccountDetails(account);
			// writing an activity
			Activity activity = new Activity();
			activity.setClientID(clientID);
			activity.setActivityDate(new Date());
			activity.setCommission(propertiesUtil.getCommissionRate());
			activity.setDescription("Commission charged due to deposit to account");
			//adding activity to database
			activityManager.addActivity(activity);
			getConnection().commit();
			//return an updated account
			return accountManager.viewAccountDetails(clientID);
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

	@Override
	public Account withdrawFromAccount(double withdraw) throws MBankException {

		try {
			getConnection().setAutoCommit(false);

			Account account = accountManager.viewAccountDetails(clientID);

			double commissionRate = propertiesUtil.getCommissionRate();

			double balance = account.getBalance();
			double afterWithdrow = (balance - withdraw) - commissionRate;
			
			account.setBalance(afterWithdrow);

			// checking if there is credit limit issue
			if (!client.getType().equals(ClientType.PLATINUM)) {
				if (account.getBalance() < -account.getCreditLimit()) {
					throw new MBankException(
							"Your Credit limit not allowed you to Withdraw");
				}
			}

			Activity activity = new Activity();
			accountManager.updateAccountDetails(account);

			activity.setClientID(clientID);
			activity.setAmount(account.getBalance());
			activity.setActivityDate(new Date());
			activity.setCommission(commissionRate);
			activity.setDescription("Commission charged due account withdraw");

			activityManager.addActivity(activity);

			getConnection().commit();
			return account;
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

	@Override
	public Client updateClientDetails(String address, String email, String phone)
			throws MBankException {

		try {
			getConnection().setAutoCommit(false);		

		if (address != null && !address.isEmpty() && !address.equals("")) {
			client.setAddress(address);
		}
		if (email != null && !email.isEmpty() && !email.equals("")) {
			client.setEmail(email);
		}
		if (phone != null && !phone.isEmpty() && !phone.equals("")) {
			client.setPhone(phone);
		}

		clientManager.updateClientDetails(client);

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

}
