package mBank;

import helpers.MBankException;
import helpers.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import action.Action;
import action.AdminAction;
import action.ClientAction;
import beans.Client;
import db.managers.ClientManager;
import db.managers.ClientManagerJDBC;
import db.managers.PropertiesManager;
import db.managers.PropertiesManagerJDBC;

/**
 * 
 * this class is the core of the MBank util it is a singleton so only one object
 * will be created and used by all users (admins/clients) it contains a set of
 * connections which is the pool connection to the MBank DB. each client/admin
 * who log into the system will get a connection and when loging out will return
 * it. the pool size is set to 100 but can be changed using POOL_SIZE
 * 
 */
public enum MBank {
	INSTANCE;
	private static final int POOL_SIZE = 20;
	private Set<Connection> connections = new HashSet<Connection>();
	private Set<Connection> UsedConnections = new HashSet<Connection>();

	/**
	 * the MBank constractor - will only run once as MBank is a singleton it
	 * creates the pool connection and starts the thread that closes deposits
	 * every day
	 */
	private MBank() {
		this.connections = createConnectionPool();
	}

	/**
	 * creates the pool connection by creating connections to the database and
	 * storing them on the connections set of MBank
	 */
	public Set<Connection> createConnectionPool() {
		for (int i = 0; i < POOL_SIZE; i++) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306", "root", "admin");
				connections.add(con);
			} catch (Exception e) {
				System.err.println(e.getMessage()
						+ "\ncannot establish connection to database");
			}

		}

		return connections;
	}

	public void destroyConnectionPool() {
		if (!connections.isEmpty()) {
			for (Connection connection : connections) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (!UsedConnections.isEmpty()) {
			for (Iterator<Connection> iterator = UsedConnections.iterator(); iterator
					.hasNext();) {
				Connection connection = (Connection) iterator.next();
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * checks if there are available connection to the DB in the pool connection
	 * and if so checks the username and password of the admin: 1. if wrong
	 * returns an AdminAction object but with no connection (thus useless) 2. if
	 * correct returns an AdminAction object with a connection from the pool
	 * connection of MBank
	 * 
	 * @param username
	 * @param password
	 * @return AdminAction
	 * @throws MBankException
	 */
	public AdminAction adminLogin(String username, String password)
			throws MBankException {
		// Connection connection = null;
		AdminAction adminAction = new AdminAction(connections.iterator().next());
		if (connections.isEmpty()) {
			throw new MBankException("no avalible connections");
		} else {
			// connection = connections.iterator().next();
			// adminAction.setConnection(connection);
			connections.remove(adminAction.getConnection());
			UsedConnections.add(adminAction.getConnection());

			PropertiesManager propertiesManager = new PropertiesManagerJDBC(
					adminAction.getConnection());
			PropertiesUtil propertiesUtil = new PropertiesUtil(
					propertiesManager);
			try {
				if (username.equals(propertiesUtil.getAdminUserName())
						&& password.equals(propertiesUtil.getAdminPassword())) {
					return adminAction;
				} else {
					connections.add(adminAction.getConnection());
					UsedConnections.remove(adminAction.getConnection());
					adminAction.setConnection(null);
					throw new MBankException("Name or Password is incorrect");
				}
			} catch (MBankException e) {
				throw e;
			}

		}
	}

	/**
	 * checks if there is an available connection to the DB in the pool
	 * connection and if so checks the username and password of the client: 1.
	 * if wrong returns a ClientAction object but with no connection (thus
	 * useless) 2. if correct returns a ClientAction object with a connection
	 * from the pool connection of MBank (also initializes the password and
	 * user_name attributes of the ClientAction object)
	 * 
	 * 
	 * @param user_name
	 * @param password
	 * @return
	 * @throws MBankException
	 */
	public ClientAction clientLogin(String user_name, String password)
			throws MBankException {
		if (connections.isEmpty()) {
			throw new MBankException(
					"no connection available, please try again later");
		} else {
			Connection connection = connections.iterator().next();

			ClientManager clientDBM = new ClientManagerJDBC(connection);
			System.out.println(clientDBM.toString());
			try {
				Client client = clientDBM
						.viewClientDetails(user_name, password);
				System.out.println("client = " + client);
				System.out.println("Connection = " + connection);
				ClientAction clientAction = new ClientAction(connection, client);
				System.out.println("clientAction = " + clientAction);
				connections.remove(clientAction.getConnection());
				UsedConnections.add(clientAction.getConnection());
				return clientAction;
			} catch (MBankException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * returns the connection to the pool connection of MBank
	 * 
	 * @param action
	 */
	public void logout(Action action) {
		connections.add(action.getConnection());
		UsedConnections.remove(action.getConnection());
		action.setConnection(null);
		return;
	}

}