package action;

import helpers.ClientType;
import helpers.MBankException;
import helpers.PropertiesUtil;

import java.sql.Connection;
import java.util.List;

import mBank.AutoDepositClosing;

import org.joda.time.DateTime;

import beans.Account;
import beans.Client;
import db.managers.AccountManager;
import db.managers.AccountManagerJDBC;
import db.managers.ActivityManager;
import db.managers.ActivityManagerJDBC;
import db.managers.ClientManager;
import db.managers.ClientManagerJDBC;
import db.managers.DepositManager;
import db.managers.DepositManagerJDBC;
import db.managers.PropertiesManager;
import db.managers.PropertiesManagerJDBC;

public class Action {

	protected ClientManager clientManager;
	protected AccountManager accountManager;
	protected ActivityManager activityManager;
	protected DepositManager depositManager;
	protected PropertiesManager propertiesManager;
	protected PropertiesUtil propertiesUtil;
	private Connection connection;

	public Action(Connection connection) {
		this.setConnection(connection);
		clientManager = new ClientManagerJDBC(connection);
		accountManager = new AccountManagerJDBC(connection);
		activityManager = new ActivityManagerJDBC(connection);
		depositManager = new DepositManagerJDBC(connection);
		propertiesManager = new PropertiesManagerJDBC(connection);
		propertiesUtil = new PropertiesUtil(propertiesManager);
		autoDepositClosing(this);
	}

	// Close deposits automatic
	// Money is transferred to clients account
	public void closeDepositAutomatic(Connection connection)
			throws MBankException {
		List<beans.Deposit> deposits;
		try {
			deposits = depositManager.viewAllDeposits();
			for (beans.Deposit d : deposits) {
				DateTime closing = new DateTime(d.getClosingDate());
				if (closing.isAfterNow()) {
					long clientID = d.getClientID();
					Client client = clientManager.viewClientDetails(clientID);
					Account account = accountManager
							.viewAccountDetails(clientID);

					double fee = depositsOpeningFeeChecker(client);
					account.setBalance((account.getBalance() + d.getBalance())
							- fee);

					accountManager.updateAccountDetails(account);
					depositManager.removeDeposit(d.getDepositID());
					System.out.println("Deposit " + d.getDepositID()
							+ " has been automatic removed");
				}
			}
		} catch (MBankException e) {
			throw e;
		}

	}

	// for automatic closing deposits
	public double depositsOpeningFeeChecker(Client client)
			throws MBankException {
		double openingFee = 0;
		if (client.getType().equals(ClientType.REGULAR)) {
			openingFee = (propertiesUtil.getRegularDepositCommission());
		} else if (client.getType().equals(ClientType.GOLD)) {
			openingFee = (propertiesUtil.getGoldDepositCommission());
		} else if (client.getType().equals(ClientType.PLATINUM)) {
			openingFee = (propertiesUtil.getPlatinumDepositCommission());
		} else {
			throw new MBankException("can not get preOpenFee");
		}
		return openingFee;
	}

	protected void autoDepositClosing(Action action) {
		Thread thread = new Thread(new AutoDepositClosing(action));
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
