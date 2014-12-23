package mBank;

import helpers.MBankException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import action.AdminAction;
import action.ClientAction;
import action.ClientActionInterface;
import beans.Client;
import db.managers.ClientManager;
import db.managers.ClientManagerJDBC;

public class Login {
	private static String name;
	private static String password;

	public Login() {
		init();
	}

	private void init() {
		try {
			mainLogin();
		} catch (MBankException e) {
			System.err.println(e.getMessage());
			init();
		}
	}

	/**
	 * runs MBank through console - creates MBank singleton and call the
	 * mainLogin method
	 * 
	 * @param args
	 * @throws MBankException
	 */
	public static void main(String[] args) throws MBankException {
		new Login();
	}

	/**
	 * asks for the user type (admin or client) and creates the
	 * AdminAction/ClientAction object by calling
	 * Login.adminLogin/Login.clientLogin and then runs the console based MBank
	 * by calling AdminLogin.Login or ClientLogin.Login
	 * 
	 * @throws MBankException
	 * 
	 */
	public void mainLogin() throws MBankException {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		boolean loggedIn = false;
		System.out.println("Hello");
		while (!loggedIn) {
			System.out.println("If you are Admin Enter 1  ");
			System.out.println("If you are Client Enter 2");
			try {
				String answerNumber = bufferedReader.readLine().toLowerCase();
				int answerIntNum = Integer.parseInt(answerNumber);

				if (answerIntNum == 1) {
					AdminAction adminAction = Login.adminLogin();
					if (adminAction == null) {
						return;
					}
					AdminLogin adminLogin = new AdminLogin();
					adminLogin.login(adminAction);
					loggedIn = true;
					MBank.INSTANCE.logout(adminAction);
				} else if (answerIntNum == 2) {
					ClientAction clientAction = Login.clientLogin();
					if (clientAction == null) {
						return;
					}
					ClientManager clientManager = new ClientManagerJDBC(
							clientAction.getConnection());

					try {
						System.out.println();
						Client client = clientManager.viewClientDetails(name,
								password);
						System.out.println("client= " + client);
						ClientLogin.login(clientAction, client);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					loggedIn = true;
					MBank.INSTANCE.logout(clientAction);

				} else {
					System.err
							.println("please enter 1 for Admin or enter 2 for Client");
				}
			} catch (NumberFormatException e) {
				System.err
						.println("please enter 1 for Admin or enter 2 for Client");
			} catch (IOException e1) {
				throw new MBankException(e1.getMessage());
			}
		}
		return;

	}

	/**
	 * asks for the admin's username and password and if they are correct
	 * creates an AdminACtion object with connection to the DB and returns it
	 * 
	 * @return AdminAction
	 */
	private static AdminAction adminLogin() throws MBankException {
		System.out.println("\nHello Admin Welcome to Mbank");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		AdminAction adminAction = null;
		// try {
		boolean loggedIn = false;
		while (!loggedIn) {
			String name;
			try {
				System.out.println("your username please:  ");

				name = bufferedReader.readLine().toLowerCase();
				System.out.println("and your password is:  ");
				String password = bufferedReader.readLine().toLowerCase();
				MBank mBank = MBank.INSTANCE;
				adminAction = mBank.adminLogin(name, password);
			} catch (IOException e) {
				throw new MBankException(e.getMessage());
			}
			if (adminAction == null) {
				throw new MBankException(
						"Wrong name or password. please try again\n");
			} else {
				if (adminAction.getConnection() == null) {
					throw new MBankException(
							"no available connection, please try again later");
				} else {
					System.out.println("Hello " + name + "");
					loggedIn = true;
					return adminAction;
				}
			}
		}
		throw new MBankException("can not access");

	}

	/**
	 * asks for the client's username and password and if they are correct
	 * creates a ClientACtion object with connection to the DB and returns it
	 * 
	 * @return
	 * @throws MBankException
	 */
	private static ClientAction clientLogin() throws MBankException {

		System.out.println("Hello Client Welcome to Mbank");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		MBank mBank = MBank.INSTANCE;

		try {
			System.out.println("Enter Your Name:  ");
			name = bufferedReader.readLine().toLowerCase();
			System.out.println(name);
			System.out.println("Enter Your Password:  ");
			password = bufferedReader.readLine().toLowerCase();
			System.out.println(password);
		} catch (IOException e1) {
			throw new MBankException(e1.getMessage());
		}
		try {
			return mBank.clientLogin(name, password);
		} catch (MBankException e) {
			throw e;
		}

	}

	public static ClientActionInterface webLogin(String userName,
			String password) throws MBankException {
		try {
			MBank mBank = MBank.INSTANCE;
			ClientActionInterface clientAction = mBank.clientLogin(userName,
					password);
			return clientAction;
		} catch (MBankException e) {
			throw e;
		}
	}
}