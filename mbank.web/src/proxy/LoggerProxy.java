package proxy;

import helpers.MBankException;

import java.sql.Connection;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import sendQueue.LogSender;
import action.Action;
import action.ClientActionInterface;
import beans.Account;
import beans.Activity;
import beans.Client;
import beans.Deposit;
import beans.Property;

public class LoggerProxy extends Action implements ClientActionInterface {
	private Connection connection;

	private LogSender logSenderStub;
	private ClientActionInterface cAInterface;
	private Long clientID;
	private static final String logSenderJNDI = "java:global/mbank.app/mbank.ejb/LogSenderBean!sendQueue.LogSender";

	/**
	 * <h3>CTOR of LoggerProxy</h3> Initializing injected stub of LogSender from
	 * the server<br/>
	 * @param connection
	 * @param caInterface
	 * @param client
	 */
	public LoggerProxy (Connection connection,ClientActionInterface clientActionInterface,Client client) {
		super(connection);
		try {
			InitialContext context = new InitialContext();
			logSenderStub = (LogSender) context
					.lookup(LoggerProxy.logSenderJNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		this.cAInterface = clientActionInterface;
		this.setConnection(connection);
		this.clientID = client.getClientID();
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
	public Deposit createNewDeposit(double initialDeposit, 
			int monthOfDeposit, int yearsOfDeposit) throws MBankException {
		return cAInterface.createNewDeposit(initialDeposit, 
				monthOfDeposit, yearsOfDeposit);
	}



	@Override
	public List<Activity> viewClientActivities() throws MBankException {
		return cAInterface.viewClientActivities();
	}

	@Override
	public String viewSystemProperty(String prop_key) throws MBankException {
		return cAInterface.viewSystemProperty(prop_key);
	}

	@Override
	public List<Deposit> viewClientDeposits() throws MBankException {
		return cAInterface.viewClientDeposits();
	}

	@Override
	public Account viewAccountDetails() throws MBankException {
		return cAInterface.viewAccountDetails();
	}

	@Override
	public Client viewClientdetails() throws MBankException {
		return cAInterface.viewClientdetails();
	}

	@Override
	public Deposit preOpenDeposit(long depositId) throws MBankException {
		return cAInterface.preOpenDeposit(depositId);
	}

	@Override
	public Account depositToAccount(double depositAmount) throws MBankException {
		Account rv = cAInterface.depositToAccount(depositAmount);
		String action = "Deposit amount of: " + depositAmount;
		this.logSenderStub.addLog(this.clientID, action);
		return rv;
	}

	@Override
	public Account withdrawFromAccount(double withdraw) throws MBankException {
		Account rv = cAInterface.withdrawFromAccount(withdraw);
		String action = "Withdraw amount of: " + withdraw;
		this.logSenderStub.addLog(this.clientID, action);
		return rv;

	}

	@Override
	public Client updateClientDetails(String address, String email, String phone)
			throws MBankException {
		return cAInterface.updateClientDetails(address, email, phone);
	}



	@Override
	public List<Property> viewSystemProperties() throws MBankException {
		return cAInterface.viewSystemProperties();
	}

	@Override
	public void logut() throws MBankException {
		cAInterface.logut();

		
	}

	

}