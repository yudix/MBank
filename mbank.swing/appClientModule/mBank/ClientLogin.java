package mBank;

import helpers.MBankException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import action.ClientAction;
import beans.Account;
import beans.Client;
import beans.Deposit;

public class ClientLogin {

	/**
	 * allows the logged-in client user to use all the actions which are defined
	 * for client using the console for each option the user chooses, the method
	 * invokes the corresponding method in the ClientACtion object that was
	 * received as a parameter code 99 = exit
	 * 
	 * @param clientAction
	 * @param client
	 * @throws IOException
	 */
	public static void login(ClientAction clientAction, Client client){

		System.out.println("Hello " + client.getClientName()
				+ " Your client id is " + client.getClientID());
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		int answerIntNum = 0;
		BufferedReader reader = new BufferedReader(inputStreamReader);
		try {

			while (answerIntNum != 99) {

				System.out.println("to Create new Deposit	 Enter 1");
				System.out.println("to Deposit To Account	 Enter 2");
				System.out.println("to Withdraw From Account Enter 3");
				System.out.println("to Pre Open Deposit	     Enter 4");
				System.out.println("to Update Client Details Enter 5");
				System.out.println("to View Client details	 Enter 6");
				System.out.println("to View Account Details	 Enter 7");
				System.out.println("to View Client Deposits  Enter 8");
				System.out.println("to View System Property  Enter 9");
				System.out.println("to Exit Enter 99");

				String answerNumber = reader.readLine().toLowerCase();
				answerIntNum = Integer.parseInt(answerNumber);

				switch (answerIntNum) {
				case 9:
					System.out.println("Enter Property Key: ");
					String prop_key = reader.readLine();
					try {
						String prop_value = clientAction
								.viewSystemProperty(prop_key);
						System.out.println("value of propery " + prop_key
								+ " is: " + prop_value);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 8:
					try {
						List<Deposit> deposits = clientAction.viewClientDeposits();
						System.out.println("Deposits for client: "
								+ client.getClientID());
						for (Deposit d : deposits){
							System.out.println(d);
						}
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 7:
					try {
						Account account = clientAction
								.viewAccountDetails();
						System.out.println("clinet " + client.getClientID()
								+ " account details:");
						System.out.println(account);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 6:
					try {
						client = clientAction.viewClientdetails();
						System.out.println("your details:" + client);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 5:
					System.out.println("Enter Address:");
					String address = reader.readLine();
					System.out.println("Enter Email:");
					String email = reader.readLine();
					System.out.println("Enter Phone:");
					String phone = reader.readLine();
					try {
						client = clientAction.updateClientDetails(address,
								email, phone);
						System.out.println("your updated details:" + client);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 4:
					System.out.println("Enter Deposit ID: ");
					String depositIdString = reader.readLine();
					long depositId = Long.parseLong(depositIdString);
					try {
						Deposit deposit = clientAction
								.preOpenDeposit(depositId);
						System.out.println
						("Deposit was pre opened: " + deposit);
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 3:
					System.out
							.println
							("Enter the amount would you like to Withdraw;");
					String withdrawString = reader.readLine();
					double withdraw = Double.parseDouble(withdrawString);
					try {
						Account account = clientAction.withdrawFromAccount(withdraw);
						System.out
								.println("withdrawal from account completed. new balance in account is:"
										+ account.getBalance());
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 2:
					System.out.println("Enter Amount to Deposit:");
					String depositAmountString = reader.readLine();
					double depositAmount = Double
							.parseDouble(depositAmountString);
					try {
						Account account = clientAction.depositToAccount(depositAmount);
						System.out
								.println("deposit to account completed. new balance in account is:"
										+ account.getBalance());
					} catch (MBankException e) {
						System.err.println(e.getMessage());
					}
					break;

				case 1:
					try {
						System.out.println("Please Enter Initial Deposit");
						String initialDep = reader.readLine();
						double initialDeposit = Double.parseDouble(initialDep);
						System.out
								.println("For how long? Enter Years of deposit");
						String year = reader.readLine();
						int yearsOfDeposit = Integer.parseInt(year);
						System.out
								.println("For how long? Enter Monthes of deposit");
						String month = reader.readLine();
						int monthOfDeposit = Integer.parseInt(month);

						try {
							Deposit deposit = clientAction.createNewDeposit(initialDeposit,
									monthOfDeposit, yearsOfDeposit);
							Account account = clientAction.viewAccountDetails();
							System.out.println("new deposit was created: "
									+ deposit
									+ "\n the new balance of your account is: "
									+ account.getBalance());
						} catch (MBankException e) {
							System.err.println(e.getMessage());
						}
					} catch (IllegalArgumentException e) {
						System.err
								.println("illegal Argument, please enter a number");
					}
					break;

				case 99:
					System.out.println("Bye Bye " + client.getClientName());
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
