package mBank;

import helpers.ClientType;
import helpers.MBankException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import action.AdminAction;
import beans.Account;
import beans.Activity;
import beans.Client;
import beans.Deposit;
import beans.Property;

public class AdminLogin {

	/**
	 * allows the logged-in administrator user to use all the actions which are
	 * defined for administrator using the console for each option the user
	 * chooses, the method invokes the corresponding method in the AdminAction
	 * object that was received as a parameter code 99 = exit
	 * 
	 * @param adminAction
	 * @throws IOException
	 */
	public void login(AdminAction adminAction) {

		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		int answerIntNum = 0;
		try {

			while (answerIntNum != 99) {

				System.out.println("to Add new Client Enter 1");
				System.out.println("to Update Client Details Enter 2");
				System.out.println("to Remove Client Enter 3 ");
				System.out.println("to View Client Details Enter 4");
				System.out.println("to View All Clients Details Enter 5");

				System.out.println("to View Account Details Enter 6");
				System.out.println("to View All Accounts Details Enter 7 ");
				System.out.println("to View Client Deposits Enter 8 ");
				System.out.println("to View All Clients Deposits Enter 9 ");
				System.out.println("to View Client Activities Enter 10 ");
				System.out.println("to View All Activities  Enter 11 ");
				System.out.println("to Update System Property Enter 12 ");
				System.out.println("to View System Property Enter 13 ");
				System.out.println("to Exit Enter 99");
				String answerNumber = reader.readLine().toLowerCase();
				answerIntNum = Integer.parseInt(answerNumber);
				switch (answerIntNum) {
				case 13:
					System.out.println("Enter the Property Key:");
					String prop_key = reader.readLine();

					try {
						String prop_value = adminAction.viewProperty(prop_key).getPropValue();
//								.viewSystemProperty(prop_key);
						System.out.println("property " + prop_key + " = "
								+ prop_value);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 12:
					System.out.println("Enter Property Key: ");
					prop_key = reader.readLine();

					System.out.println("Enter Property Value: ");
					String prop_value = reader.readLine();

					try {
						adminAction.updateSystemProperty(new Property(prop_key, prop_value));
						System.out.println("property " + prop_key
								+ " was updated to: " + prop_value);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 11:
					try {
						List<Activity> activities = adminAction
								.viewAllActivities();
						System.out.println("All Activities:");
						for (Activity a : activities) {
							System.out.println(a);
						}
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 10:
					System.out.println("Enter the Client ID:");
					String clientID = reader.readLine();
					long clientIDLong = Long.parseLong(clientID);

					try {
						List<Activity> activities = adminAction								
								.viewClientActivities(clientIDLong);
						System.out.println("Activities for client: "
								+ clientIDLong);
						for (Activity a : activities) {
							System.out.println(a);
						}
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 9:
					try {
						List<Deposit> deposits = adminAction.viewAllDeposits();
						System.out.println("All Deposits:");
						for (Deposit d : deposits) {
							System.out.println(d);
						}
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 8:
					System.out.println("Enter the Client ID:");
					clientID = reader.readLine();
					clientIDLong = Long.parseLong(clientID);

					try {
						List<Deposit> deposits = adminAction.viewClientDeposits(clientIDLong);

						System.out.println("Deposits for client: "
								+ clientIDLong);
						for (Deposit d : deposits) {
							System.out.println(d);
						}
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 7:
					try {
						List<Account> accounts = adminAction.viewAllAccounts();
						System.out.println("All accounts:");
						for (Account a : accounts){
							System.out.println(a);
						}
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 6:
					System.out.println("Enter the Client ID:");
					clientID = reader.readLine();
					clientIDLong = Long.parseLong(clientID);

					try {
						Account account = adminAction
								.viewAccount(clientIDLong);
						System.out.println("clinet " + clientIDLong
								+ " account details:");
						System.out.println(account);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 5:
					try {
						List<Client> clients = adminAction.viewAllClients();
						System.out.println("All clients:");
						for (Client c : clients) {
							System.out.println(c);
						}
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 4:
					System.out.println("Enter the Client ID:");
					clientID = reader.readLine();
					clientIDLong = Long.parseLong(clientID);

					try {
						Client client = adminAction
								.viewClientDetails(clientIDLong);
						System.out.println("client details: " + client);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 3:
					System.out.println("Enter the Client ID:");
					clientID = reader.readLine();
					clientIDLong = Long.parseLong(clientID);

					try {
						adminAction.removeClient(clientIDLong);
						System.out
								.println("client "
										+ clientIDLong
										+ " has been removed.\n"
										+ "the client's accounts and deposits have been removed as well");
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 2:
					ClientType clientTypes = null;

					System.out
							.println("Enter The Client ID you want to Update:");
					clientID = reader.readLine();
					clientIDLong = Long.parseLong(clientID);

					System.out.println("To Update The Client Type: \n"
							+ "For Regular type Enter 1 \n"
							+ "For Gold type Enter 2 \n"
							+ "For Platinum type Enter 3 \n");
					String clientType = reader.readLine();
					int clientTypeInt = Integer.parseInt(clientType);

					if (clientTypeInt == 1) {
						clientTypes = ClientType.REGULAR;
					} else if (clientTypeInt == 2) {
						clientTypes = ClientType.GOLD;
					} else if (clientTypeInt == 3) {
						clientTypes = ClientType.PLATINUM;
					}

					System.out.println("Enter The Comment you want to Update");
					String clientComment = reader.readLine();

					try {
						adminAction.updateClientDetails(
								clientIDLong, clientTypes, clientComment);
						Client client = adminAction.viewClientDetails(clientIDLong);
						System.out.println("client details of client "
								+ clientIDLong + " were updated:\n" + client);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 1:
					System.out.println("Enter Client Name:");
					String clientName = reader.readLine();

					System.out.println("Enter Client Password:");
					String clientPassword = reader.readLine();

					System.out.println("Enter Client Address:");
					String clientAddress = reader.readLine();

					System.out.println("Enter Client E-Mail:");
					String clientEmail = reader.readLine();

					System.out.println("Enter Client Phone:");
					String clientPhone = reader.readLine();

					System.out.println("Enter Comment:");
					String comment = reader.readLine();

					System.out.println("Enter Client Initial Deposit:");
					String inDeposit = reader.readLine();

					double initialDeposit = Double.parseDouble(inDeposit);

					Client client = new Client();
					client.setClientName(clientName);
					client.setPassword(clientPassword);
					client.setAddress(clientAddress);
					client.setEmail(clientEmail);
					client.setPhone(clientPhone);
					client.setComments(comment);

					Account account = new Account();
					account.setBalance(initialDeposit);

					try {
						client = adminAction.addNewClient(clientName, clientPassword, clientAddress, clientPhone, clientEmail, comment, initialDeposit);
						
						account = adminAction
								.viewAccount(client
										.getClientID());
						System.out.println("client added. \n client details: "
								+ client + "\n account details:" + account);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 99:
					System.out.println("Bye Bye ");
					MBank.INSTANCE.logout(adminAction);
					break;
				default:
					System.err.println("\nillegal value. please try again\n");
					break;

				}
			}
		} catch (IOException e) {
			System.err.println("IO error");
		}
	}
}
