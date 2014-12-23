package gui;

import helpers.ClientType;
import helpers.MBankException;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import beans.Account;
import beans.Activity;
import beans.Client;
import beans.Deposit;
import beans.Property;

public class MBankSwingExternals {
	MBankSwing parent;

	public MBankSwingExternals(MBankSwing parent) {
		this.parent = parent;
	}

	public void view_Account() {
		try {
			CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
			cardLayout.show(parent.panel, parent.edit_Account.getName());
			Account account = parent.action.viewAccount(Long
					.valueOf(parent.edt_client_id.getText()));
			parent.accnt_AID.setText(String.valueOf(account.getAccountID()));
			parent.accnt_CID.setText(String.valueOf(account.getClientID()));
			parent.accnt_Balance.setText(String.valueOf(account.getBalance()));
			parent.accnt_Credit.setText(String.valueOf(account
					.getCreditLimit()));
			parent.accnt_Comment.setText(account.getComment());
		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void update_Client() {
		int ans = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to update this client?",
				"Update Client?", JOptionPane.OK_CANCEL_OPTION);
		if (ans == 2) {
			return;
		}
		try {
			parent.action.updateClientDetails(Long.valueOf(parent.edt_client_id
					.getText()), ClientType
					.valueOf(parent.edt_combo_client_type.getSelectedItem()
							.toString()), parent.edt_client_comment.getText());

		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void add_New_Client() {
		try {
			Client client = new Client();
			Account account = new Account();
			client.setClientName(parent.client_name.getText());
			client.setAddress(parent.address.getText());
			client.setComments(parent.comment.getText());
			client.setEmail(parent.email.getText());
			client.setPassword(parent.client_pass.getText());
			client.setPhone(parent.phone.getText());
			double initialDeposit = new Double(parent.ini_deposit.getText());
			account.setBalance(initialDeposit);
			parent.action.addNewClient(
					client.getClientName(),
					client.getPassword(),
					client.getAddress(),
					client.getEmail(),
					client.getPhone(),
					client.getComments(),					
					initialDeposit);
			JOptionPane.showMessageDialog(new JPanel(),
					"Successfuly added new client.", "Success!",
					JOptionPane.PLAIN_MESSAGE);
		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void load_Client() {
		try {
			Client clientbean = parent.action.viewClientDetails(Long
					.valueOf(parent.edt_client_id.getText()));
			parent.edt_client_id.setText(String.valueOf(clientbean
					.getClientID()));
			parent.edt_client_name.setText(clientbean.getClientName());
			parent.edt_client_pass.setText(clientbean.getPassword());
			parent.edt_combo_client_type.setSelectedItem(clientbean
					.getType());
			parent.edt_client_address.setText(clientbean.getAddress());
			parent.edt_client_email.setText(clientbean.getEmail());
			parent.edt_client_phone.setText(clientbean.getPhone());
			parent.edt_client_comment.setText(clientbean.getComments());
		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void view_Client_Deposits() {
		CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
		cardLayout.show(parent.panel, parent.client_Deposits.getName());
		DefaultTableModel model = (DefaultTableModel) parent.client_Deposits_table
				.getModel();
		model.setRowCount(0);
		try {
			List<Deposit> deposits = parent.action.viewClientDeposits(Long
					.valueOf(parent.edt_client_id.getText()));
			for (Deposit d : deposits) {
				model.addRow(new Object[] { d.getDepositID(),
						d.getClientID(), d.getBalance(),
						d.getDepositType(),
						d.getEstimatedBalance(),
						d.getOpeningDate(),
						d.getClosingDate() });
			}

		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void remove_Client() {
		int ans = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to remove this client?",
				"Remove Client?", JOptionPane.OK_CANCEL_OPTION);
		if (ans == 2) {
			return;
		}
		try {
			parent.action.removeClient(Long.valueOf(parent.edt_client_id
					.getText()));
			parent.edt_client_id.setText("");
			parent.edt_client_name.setText("");
			parent.edt_client_pass.setText("");
			parent.edt_combo_client_type.setSelectedIndex(-1);
			parent.edt_client_address.setText("");
			parent.edt_client_email.setText("");
			parent.edt_client_phone.setText("");
			parent.edt_client_comment.setText("");
			JOptionPane.showMessageDialog(new JPanel(),
					"Successfuly removed client.", "Success!",
					JOptionPane.PLAIN_MESSAGE);
		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void system_Properties_Update() {
		if (parent.system_properties_table.getCellEditor() != null) {
			JOptionPane.showMessageDialog(new JPanel(),
					"Can't update table while editing!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int ans = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to update System Properties?",
				"Update Properties?", JOptionPane.OK_CANCEL_OPTION);
		if (ans == 2) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) parent.system_properties_table
				.getModel();
		try {
			int rowcount = model.getRowCount();
			for (int i = 0; i < rowcount; i++) {
				Property property = new Property();
				property.setPropKey((String) model.getValueAt(i, 0));
				property.setPropValue((String) model.getValueAt(i, 1));
				parent.action.updateSystemProperty(property);
			}
			JOptionPane.showMessageDialog(new JPanel(),
					"Successfuly updated System Propoerties.", "Success!",
					JOptionPane.PLAIN_MESSAGE);
		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void close_Selected_Deposit() {
		int ans = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to close selected Deposit?",
				"Close Deposit?", JOptionPane.OK_CANCEL_OPTION);
		if (ans == 2) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) parent.client_Deposits_table
				.getModel();
		try {
			parent.action.preOpenDeposit((
					(long) model.getValueAt(
							parent.client_Deposits_table.getSelectedRow(), 0)),
					(long) model.getValueAt(
							parent.client_Deposits_table.getSelectedRow(), 1));
			JOptionPane.showMessageDialog(new JPanel(),
					"Successfuly closed Deposit.", "Success!",
					JOptionPane.PLAIN_MESSAGE);
			view_Client_Deposits();
		} catch (MBankException e) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error \n"
							+ e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void close_All_Client_Deposits() {
		int ans = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to close all Client's Deposit?",
				"Close All Deposits?", JOptionPane.OK_CANCEL_OPTION);
		if (ans == 2) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) parent.client_Deposits_table
				.getModel();
		try {
			int rowcount = model.getRowCount();
			for (int i = 0; i < rowcount; i++) {
				parent.action.preOpenDeposit((long) model.getValueAt(i, 0),
						(long) model.getValueAt(i, 1));
			}
			JOptionPane.showMessageDialog(new JPanel(),
					"Successfuly closed all deposits.", "Success!",
					JOptionPane.PLAIN_MESSAGE);
			view_Client_Deposits();
		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void load_Deposits_List() {
		CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
		cardLayout.show(parent.panel, parent.deposits_List.getName());
		DefaultTableModel model = (DefaultTableModel) parent.deposits_List_table
				.getModel();
		model.setRowCount(0);
		
		try {
			List<Deposit> deposits = parent.action.viewAllDeposits();
			for (Deposit d : deposits) {
				model.addRow(new Object[] { d.getDepositID(),
						d.getClientID(), d.getBalance(),
						d.getDepositType(),
						d.getEstimatedBalance(),
						d.getOpeningDate(),
						d.getClosingDate() });
			}
		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void load_Clients_List() {
		CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
		cardLayout.show(parent.panel, parent.clients_List.getName());
		DefaultTableModel model = (DefaultTableModel) parent.clients_List_table
				.getModel();
		model.setRowCount(0);
		try {
			List<Client> clients = parent.action.viewAllClients();
			for (Client c : clients) {
				model.addRow(new Object[] { c.getClientID(),
						c.getClientName(), c.getPassword(),
						c.getType(), c.getAddress(),
						c.getEmail(), c.getPhone(),
						c.getComments() });
			}

		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void load_Accounts_List() {
		CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
		cardLayout.show(parent.panel, parent.accounts_List.getName());
		DefaultTableModel model = (DefaultTableModel) parent.accounts_List_table
				.getModel();
		model.setRowCount(0);
		try {
			List<Account> accounts = parent.action.viewAllAccounts();
			for (Account a :accounts) {
				model.addRow(new Object[] { a.getAccountID(),
						a.getClientID(), a.getBalance(),
						a.getCreditLimit(),
						a.getComment() });
			}

		} catch (MBankException e1) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error : \n"
							+ e1.getLocalizedMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void load_System_Properties() {
		CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
		cardLayout.show(parent.panel, parent.system_properties.getName());
		DefaultTableModel model = (DefaultTableModel) parent.system_properties_table
				.getModel();
		model.setRowCount(0);
		try {
			model.addRow(new Object[] { "admin_password",
					parent.action.viewProperty("admin_password").getPropValue() });
			model.addRow(new Object[] { "admin_username",
					parent.action.viewProperty("admin_username").getPropValue() });
			model.addRow(new Object[] { "commission_rate",
					parent.action.viewProperty("commission_rate").getPropValue() });
			model.addRow(new Object[] { "gold_credit_limit",
					parent.action.viewProperty("gold_credit_limit").getPropValue() });
			model.addRow(new Object[] { "gold_daily_interest",
					parent.action.viewProperty("gold_daily_interest").getPropValue() });
			model.addRow(new Object[] { "gold_deposit_commission",
					parent.action.viewProperty("gold_deposit_commission").getPropValue() });
			model.addRow(new Object[] { "gold_deposit_rate",
					parent.action.viewProperty("gold_deposit_rate").getPropValue() });
			model.addRow(new Object[] { "platinum_credit_limit",
					parent.action.viewProperty("platinum_credit_limit").getPropValue() });
			model.addRow(new Object[] { "platinum_daily_interest",
					parent.action.viewProperty("platinum_daily_interest").getPropValue() });
			model.addRow(new Object[] {
					"platinum_deposit_commission",
					parent.action
							.viewProperty("platinum_deposit_commission").getPropValue() });
			model.addRow(new Object[] { "platinum_deposit_rate",
					parent.action.viewProperty("platinum_deposit_rate").getPropValue() });
			model.addRow(new Object[] { "pre_open_fee",
					parent.action.viewProperty("pre_open_fee").getPropValue() });
			model.addRow(new Object[] { "regular_credit_limit",
					parent.action.viewProperty("regular_credit_limit").getPropValue() });
			model.addRow(new Object[] { "regular_daily_interest",
					parent.action.viewProperty("regular_daily_interest").getPropValue() });
			model.addRow(new Object[] {
					"regular_deposit_commission",
					parent.action
							.viewProperty("regular_deposit_commission").getPropValue() });
			model.addRow(new Object[] { "regular_deposit_rate",
					parent.action.viewProperty("regular_deposit_rate").getPropValue() });
		} catch (MBankException e) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error \n"
							+ e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void load_Activity_List() {
		CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
		cardLayout.show(parent.panel, parent.activity_List.getName());
		DefaultTableModel model = (DefaultTableModel) parent.activity_List_table
				.getModel();
		model.setRowCount(0);
		parent.lblActivityList.setText("Activity List");
		try {
			List<Activity> activities = parent.action.viewAllActivities();
			for (Activity a : activities) {
				model.addRow(new Object[] { a.getActivityID(),
						a.getClientID(), a.getAmount(),
						a.getActivityDate(),
						a.getCommission(), a.getDescription() });
			}
		} catch (MBankException e) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error \n"
							+ e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void load_Client_Activity_List() {
		CardLayout cardLayout = (CardLayout) parent.panel.getLayout();
		cardLayout.show(parent.panel, parent.activity_List.getName());
		DefaultTableModel model = (DefaultTableModel) parent.activity_List_table
				.getModel();
		model.setRowCount(0);
		parent.lblActivityList.setText("Client Activity List");
		try {
			List<Activity> activities = parent.action.viewClientActivities(Long
					.valueOf(parent.edt_client_id.getText()));
			for (Activity a : activities) {
				model.addRow(new Object[] { a.getActivityID(),
						a.getClientID(), a.getAmount(),
						a.getActivityDate(),
						a.getCommission(), a.getDescription() });
			}
		} catch (MBankException e) {
			JOptionPane.showMessageDialog(
					new JPanel(),
					"Error \n"
							+ e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
