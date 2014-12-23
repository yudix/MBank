package gui;

import helpers.ClientType;
import helpers.Connector;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import mBank.DatabaseConnection;
//import mBank.MBankException;
//import mBankAction.*;
//import mBankBeans.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import action.AdminAction;
import action.AdminActionInterface;
import javax.swing.JMenuBar;

public class MBankSwing {

	Connection con;
	AdminActionInterface action;
	MBankSwingExternals child;
	JFrame frame;
	JTextField client_name;
	JTextField client_pass;
	JTextField ini_deposit;
	JTextField address;
	JTextField email;
	JTextField phone;
	JTextField comment;
	JTextField edt_client_name;
	JTextField edt_client_pass;
	JTextField edt_client_address;
	JTextField edt_client_email;
	JTextField edt_client_phone;
	JTextField edt_client_comment;
	JTextField edt_client_id;
	JTable deposits_List_table;
	JPanel panel;
	JPanel add_Client;
	JPanel edit_Client;
	JPanel deposits_List;
	JComboBox<ClientType> edt_combo_client_type;
	JTable clients_List_table;
	JPanel clients_List;
	JTable accounts_List_table;
	JPanel accounts_List;
	JTable system_properties_table;
	JPanel system_properties;
	JPanel edit_Account;
	JTextField accnt_Comment;
	JTextField accnt_Credit;
	JTextField accnt_Balance;
	JTextField accnt_CID;
	JTextField accnt_AID;
	JTable client_Deposits_table;
	JPanel client_Deposits;
	JTable activity_List_table;
	JPanel activity_List;
	JLabel lblActivityList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MBankSwing window = new MBankSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JPanel(), "Fatal Error!\nCannot Initialize UI!\nNow Terminating!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MBankSwing() {
		con=Connector.getConnection();
		action  = new AdminAction(con);
		child=new MBankSwingExternals(this);
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			JOptionPane.showMessageDialog(new JPanel(), "Fatal Error!\nCannot Initialize UI!\nNow Terminating!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblMbank = new JLabel("MBank");
		lblMbank.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblMbank, BorderLayout.NORTH);

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));

		add_Client = new JPanel();
		add_Client.setName("add_Client");
		panel.add(add_Client, add_Client.getName());
		GridBagLayout gbl_add_Client = new GridBagLayout();
		gbl_add_Client.columnWidths = new int[] {36, 81, 0, 30, 0};
		gbl_add_Client.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_add_Client.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_add_Client.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		add_Client.setLayout(gbl_add_Client);

		JLabel lblAddNewClient = new JLabel("Add New Client");
		GridBagConstraints gbc_lblAddNewClient = new GridBagConstraints();
		gbc_lblAddNewClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddNewClient.gridx = 2;
		gbc_lblAddNewClient.gridy = 0;
		add_Client.add(lblAddNewClient, gbc_lblAddNewClient);

		JLabel lblNewLabel = new JLabel("Client Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add_Client.add(lblNewLabel, gbc_lblNewLabel);

		client_name = new JTextField();
		GridBagConstraints gbc_client_name = new GridBagConstraints();
		gbc_client_name.insets = new Insets(0, 0, 5, 5);
		gbc_client_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_client_name.gridx = 2;
		gbc_client_name.gridy = 1;
		add_Client.add(client_name, gbc_client_name);
		client_name.setColumns(10);

		JLabel lblClientPassword = new JLabel("Client Password");
		GridBagConstraints gbc_lblClientPassword = new GridBagConstraints();
		gbc_lblClientPassword.anchor = GridBagConstraints.EAST;
		gbc_lblClientPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientPassword.gridx = 1;
		gbc_lblClientPassword.gridy = 2;
		add_Client.add(lblClientPassword, gbc_lblClientPassword);

		client_pass = new JTextField();
		GridBagConstraints gbc_client_pass = new GridBagConstraints();
		gbc_client_pass.insets = new Insets(0, 0, 5, 5);
		gbc_client_pass.fill = GridBagConstraints.HORIZONTAL;
		gbc_client_pass.gridx = 2;
		gbc_client_pass.gridy = 2;
		add_Client.add(client_pass, gbc_client_pass);
		client_pass.setColumns(10);

		JLabel lblInitialDeposit = new JLabel("Initial Deposit");
		GridBagConstraints gbc_lblInitialDeposit = new GridBagConstraints();
		gbc_lblInitialDeposit.anchor = GridBagConstraints.EAST;
		gbc_lblInitialDeposit.insets = new Insets(0, 0, 5, 5);
		gbc_lblInitialDeposit.gridx = 1;
		gbc_lblInitialDeposit.gridy = 3;
		add_Client.add(lblInitialDeposit, gbc_lblInitialDeposit);

		ini_deposit = new JTextField();
		ini_deposit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Integer keyTest=Integer.valueOf(arg0.getKeyChar());
				if (keyTest<46 || keyTest>57 || keyTest==47){
					arg0.setKeyChar((char)(0));
				}
			}
		});
		GridBagConstraints gbc_ini_deposit = new GridBagConstraints();
		gbc_ini_deposit.insets = new Insets(0, 0, 5, 5);
		gbc_ini_deposit.fill = GridBagConstraints.HORIZONTAL;
		gbc_ini_deposit.gridx = 2;
		gbc_ini_deposit.gridy = 3;
		add_Client.add(ini_deposit, gbc_ini_deposit);
		ini_deposit.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.EAST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 4;
		add_Client.add(lblAddress, gbc_lblAddress);

		address = new JTextField();
		GridBagConstraints gbc_address = new GridBagConstraints();
		gbc_address.insets = new Insets(0, 0, 5, 5);
		gbc_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_address.gridx = 2;
		gbc_address.gridy = 4;
		add_Client.add(address, gbc_address);
		address.setColumns(10);

		JLabel lblEmail = new JLabel("E-Mail");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 5;
		add_Client.add(lblEmail, gbc_lblEmail);

		email = new JTextField();
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 2;
		gbc_email.gridy = 5;
		add_Client.add(email, gbc_email);
		email.setColumns(10);

		JLabel lblPhone = new JLabel("Phone");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 1;
		gbc_lblPhone.gridy = 6;
		add_Client.add(lblPhone, gbc_lblPhone);

		phone = new JTextField();
		GridBagConstraints gbc_phone = new GridBagConstraints();
		gbc_phone.insets = new Insets(0, 0, 5, 5);
		gbc_phone.fill = GridBagConstraints.HORIZONTAL;
		gbc_phone.gridx = 2;
		gbc_phone.gridy = 6;
		add_Client.add(phone, gbc_phone);
		phone.setColumns(10);

		JLabel lblComment = new JLabel("Comment");
		GridBagConstraints gbc_lblComment = new GridBagConstraints();
		gbc_lblComment.anchor = GridBagConstraints.EAST;
		gbc_lblComment.insets = new Insets(0, 0, 5, 5);
		gbc_lblComment.gridx = 1;
		gbc_lblComment.gridy = 7;
		add_Client.add(lblComment, gbc_lblComment);

		comment = new JTextField();
		GridBagConstraints gbc_comment = new GridBagConstraints();
		gbc_comment.insets = new Insets(0, 0, 5, 5);
		gbc_comment.fill = GridBagConstraints.HORIZONTAL;
		gbc_comment.gridx = 2;
		gbc_comment.gridy = 7;
		add_Client.add(comment, gbc_comment);
		comment.setColumns(10);

		JButton btnAddNewClient = new JButton("Add new Client");
		btnAddNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.add_New_Client();
			}
		});
		GridBagConstraints gbc_btnAddNewClient = new GridBagConstraints();
		gbc_btnAddNewClient.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddNewClient.gridx = 2;
		gbc_btnAddNewClient.gridy = 8;
		add_Client.add(btnAddNewClient, gbc_btnAddNewClient);

		edit_Client = new JPanel();
		edit_Client.setName("edit_Client");
		panel.add(edit_Client, edit_Client.getName());
		GridBagLayout gbl_edit_Client = new GridBagLayout();
		gbl_edit_Client.columnWidths = new int[]{36, 81, 0, 30, 0, 0};
		gbl_edit_Client.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_edit_Client.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_edit_Client.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		edit_Client.setLayout(gbl_edit_Client);

		JLabel lblViewAndManage = new JLabel("View and Manage client Detailes");
		GridBagConstraints gbc_lblViewAndManage = new GridBagConstraints();
		gbc_lblViewAndManage.insets = new Insets(0, 0, 5, 5);
		gbc_lblViewAndManage.gridx = 2;
		gbc_lblViewAndManage.gridy = 0;
		edit_Client.add(lblViewAndManage, gbc_lblViewAndManage);

		JLabel lblClientId = new JLabel("Client ID  (RO)");
		GridBagConstraints gbc_lblClientId = new GridBagConstraints();
		gbc_lblClientId.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientId.anchor = GridBagConstraints.EAST;
		gbc_lblClientId.gridx = 1;
		gbc_lblClientId.gridy = 1;
		edit_Client.add(lblClientId, gbc_lblClientId);

		edt_client_id = new JTextField();
		edt_client_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Integer keyTest=Integer.valueOf(arg0.getKeyChar());
				if (keyTest<48 || keyTest>57){
					arg0.setKeyChar((char)(0));
				}
			}
		});
		GridBagConstraints gbc_edt_client_id = new GridBagConstraints();
		gbc_edt_client_id.insets = new Insets(0, 0, 5, 5);
		gbc_edt_client_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_client_id.gridx = 2;
		gbc_edt_client_id.gridy = 1;
		edit_Client.add(edt_client_id, gbc_edt_client_id);
		edt_client_id.setColumns(10);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.load_Client();
			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoad.gridx = 3;
		gbc_btnLoad.gridy = 1;
		edit_Client.add(btnLoad, gbc_btnLoad);

		JLabel label = new JLabel("Client Name");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		edit_Client.add(label, gbc_label);

		edt_client_name = new JTextField();
		edt_client_name.setColumns(10);
		GridBagConstraints gbc_edt_client_name = new GridBagConstraints();
		gbc_edt_client_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_client_name.insets = new Insets(0, 0, 5, 5);
		gbc_edt_client_name.gridx = 2;
		gbc_edt_client_name.gridy = 2;
		edit_Client.add(edt_client_name, gbc_edt_client_name);

		JLabel label_1 = new JLabel("Client Password");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		edit_Client.add(label_1, gbc_label_1);

		edt_client_pass = new JTextField();
		edt_client_pass.setEditable(false);
		edt_client_pass.setColumns(10);
		GridBagConstraints gbc_edt_client_pass = new GridBagConstraints();
		gbc_edt_client_pass.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_client_pass.insets = new Insets(0, 0, 5, 5);
		gbc_edt_client_pass.gridx = 2;
		gbc_edt_client_pass.gridy = 3;
		edit_Client.add(edt_client_pass, gbc_edt_client_pass);

		JLabel lblClientType = new JLabel("Client Type");
		GridBagConstraints gbc_lblClientType = new GridBagConstraints();
		gbc_lblClientType.anchor = GridBagConstraints.EAST;
		gbc_lblClientType.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientType.gridx = 1;
		gbc_lblClientType.gridy = 4;
		edit_Client.add(lblClientType, gbc_lblClientType);

		edt_combo_client_type = new JComboBox<ClientType>();
		edt_combo_client_type.setModel(new DefaultComboBoxModel<ClientType>(ClientType.values()));
		edt_combo_client_type.setSelectedIndex(-1);
		GridBagConstraints gbc_edt_combo_client_type = new GridBagConstraints();
		gbc_edt_combo_client_type.insets = new Insets(0, 0, 5, 5);
		gbc_edt_combo_client_type.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_combo_client_type.gridx = 2;
		gbc_edt_combo_client_type.gridy = 4;
		edit_Client.add(edt_combo_client_type, gbc_edt_combo_client_type);

		JLabel label_3 = new JLabel("Address");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 5;
		edit_Client.add(label_3, gbc_label_3);

		edt_client_address = new JTextField();
		edt_client_address.setEditable(false);
		edt_client_address.setColumns(10);
		GridBagConstraints gbc_edt_client_address = new GridBagConstraints();
		gbc_edt_client_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_client_address.insets = new Insets(0, 0, 5, 5);
		gbc_edt_client_address.gridx = 2;
		gbc_edt_client_address.gridy = 5;
		edit_Client.add(edt_client_address, gbc_edt_client_address);

		JLabel label_4 = new JLabel("E-Mail");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 6;
		edit_Client.add(label_4, gbc_label_4);

		edt_client_email = new JTextField();
		edt_client_email.setEditable(false);
		edt_client_email.setColumns(10);
		GridBagConstraints gbc_edt_client_email = new GridBagConstraints();
		gbc_edt_client_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_client_email.insets = new Insets(0, 0, 5, 5);
		gbc_edt_client_email.gridx = 2;
		gbc_edt_client_email.gridy = 6;
		edit_Client.add(edt_client_email, gbc_edt_client_email);

		JLabel label_5 = new JLabel("Phone");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 7;
		edit_Client.add(label_5, gbc_label_5);

		edt_client_phone = new JTextField();
		edt_client_phone.setEditable(false);
		edt_client_phone.setColumns(10);
		GridBagConstraints gbc_edt_client_phone = new GridBagConstraints();
		gbc_edt_client_phone.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_client_phone.insets = new Insets(0, 0, 5, 5);
		gbc_edt_client_phone.gridx = 2;
		gbc_edt_client_phone.gridy = 7;
		edit_Client.add(edt_client_phone, gbc_edt_client_phone);

		JLabel label_6 = new JLabel("Comment");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 8;
		edit_Client.add(label_6, gbc_label_6);

		edt_client_comment = new JTextField();
		edt_client_comment.setColumns(10);
		GridBagConstraints gbc_edt_client_comment = new GridBagConstraints();
		gbc_edt_client_comment.fill = GridBagConstraints.HORIZONTAL;
		gbc_edt_client_comment.insets = new Insets(0, 0, 5, 5);
		gbc_edt_client_comment.gridx = 2;
		gbc_edt_client_comment.gridy = 8;
		edit_Client.add(edt_client_comment, gbc_edt_client_comment);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.update_Client();
			}
		});

		JButton btnViewAccount = new JButton("View Account");
		btnViewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.view_Account();
			}
		});
		GridBagConstraints gbc_btnViewAccount = new GridBagConstraints();
		gbc_btnViewAccount.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewAccount.gridx = 1;
		gbc_btnViewAccount.gridy = 9;
		edit_Client.add(btnViewAccount, gbc_btnViewAccount);
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 9;
		edit_Client.add(btnUpdate, gbc_btnUpdate);

		JButton btnViewDeposits = new JButton("View Deposits");
		btnViewDeposits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.view_Client_Deposits();
			}
		});
		GridBagConstraints gbc_btnViewDeposits = new GridBagConstraints();
		gbc_btnViewDeposits.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewDeposits.gridx = 3;
		gbc_btnViewDeposits.gridy = 9;
		edit_Client.add(btnViewDeposits, gbc_btnViewDeposits);

		JButton btnRemoveClient = new JButton("Remove Client");
		btnRemoveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.remove_Client();
			}
		});

		JButton btnViewActivity = new JButton("View Activity");
		btnViewActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				child.load_Client_Activity_List();
			}
		});
		GridBagConstraints gbc_btnViewActivity = new GridBagConstraints();
		gbc_btnViewActivity.insets = new Insets(0, 0, 0, 5);
		gbc_btnViewActivity.gridx = 1;
		gbc_btnViewActivity.gridy = 10;
		edit_Client.add(btnViewActivity, gbc_btnViewActivity);
		GridBagConstraints gbc_btnRemoveClient = new GridBagConstraints();
		gbc_btnRemoveClient.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveClient.gridx = 2;
		gbc_btnRemoveClient.gridy = 10;
		edit_Client.add(btnRemoveClient, gbc_btnRemoveClient);

		deposits_List = new JPanel();
		deposits_List.setName("deposit_List");
		panel.add(deposits_List, deposits_List.getName());
		GridBagLayout gbl_deposits_List = new GridBagLayout();
		gbl_deposits_List.columnWidths = new int[] {36, 0, 0, 0};
		gbl_deposits_List.rowHeights = new int[]{0, 0, 0, 0};
		gbl_deposits_List.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_deposits_List.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		deposits_List.setLayout(gbl_deposits_List);

		JLabel lblListOfAll = new JLabel("List of all Deposits");
		GridBagConstraints gbc_lblListOfAll = new GridBagConstraints();
		gbc_lblListOfAll.insets = new Insets(0, 0, 5, 5);
		gbc_lblListOfAll.gridx = 1;
		gbc_lblListOfAll.gridy = 0;
		deposits_List.add(lblListOfAll, gbc_lblListOfAll);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		deposits_List.add(scrollPane, gbc_scrollPane);

		deposits_List_table = new JTable();
		deposits_List_table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null, null, null},
				},
				new String[] {
						"Deposit ID", "Client ID", "Balance", "Deposit Type", "Estimated Balance", "Opening Date", "Closeing Date"
				}
				) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7508002816172188603L;
			Class<?>[] columnTypes = new Class[] {
					Float.class, Long.class, Double.class, Object.class, Double.class, Object.class, Object.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		deposits_List_table.setFillsViewportHeight(true);
		scrollPane.setViewportView(deposits_List_table);

		clients_List = new JPanel();
		clients_List.setName("client_List");
		panel.add(clients_List, clients_List.getName());
		GridBagLayout gbl_clients_List = new GridBagLayout();
		gbl_clients_List.columnWidths = new int[]{36, 0, 0, 0};
		gbl_clients_List.rowHeights = new int[]{0, 0, 0, 0};
		gbl_clients_List.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_clients_List.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		clients_List.setLayout(gbl_clients_List);

		JLabel lblClientsList = new JLabel("Clients List");
		GridBagConstraints gbc_lblClientsList = new GridBagConstraints();
		gbc_lblClientsList.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientsList.gridx = 1;
		gbc_lblClientsList.gridy = 0;
		clients_List.add(lblClientsList, gbc_lblClientsList);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		clients_List.add(scrollPane_1, gbc_scrollPane_1);

		clients_List_table = new JTable();
		clients_List_table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null, null, null, null},
				},
				new String[] {
						"Client ID", "Name", "Password", "Client Type", "Address", "Email", "Phone", "Comment"
				}
				) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -521355388589635809L;
			Class<?>[] columnTypes = new Class[] {
					Long.class, String.class, String.class, Object.class, String.class, String.class, String.class, String.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		clients_List_table.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(clients_List_table);

		accounts_List = new JPanel();
		accounts_List.setName("account_List");
		panel.add(accounts_List, accounts_List.getName());
		GridBagLayout gbl_accounts_List = new GridBagLayout();
		gbl_accounts_List.columnWidths = new int[] {36, 0, 30, 0};
		gbl_accounts_List.rowHeights = new int[]{0, 0, 0, 0};
		gbl_accounts_List.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_accounts_List.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		accounts_List.setLayout(gbl_accounts_List);

		JLabel lblListOfAll_1 = new JLabel("List of all Accounts");
		GridBagConstraints gbc_lblListOfAll_1 = new GridBagConstraints();
		gbc_lblListOfAll_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblListOfAll_1.gridx = 1;
		gbc_lblListOfAll_1.gridy = 0;
		accounts_List.add(lblListOfAll_1, gbc_lblListOfAll_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 1;
		accounts_List.add(scrollPane_2, gbc_scrollPane_2);




		accounts_List_table = new JTable();
		accounts_List_table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null},
				},
				new String[] {
						"Account ID", "Client ID", "Balance", "Credit Limit", "Comment"
				}
				) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2456161927392301047L;
			Class<?>[] columnTypes = new Class[] {
					Long.class, Long.class, Double.class, Double.class, String.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		accounts_List_table.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(accounts_List_table);

		system_properties = new JPanel();
		system_properties.setName("system_properties");
		panel.add(system_properties, system_properties.getName());
		GridBagLayout gbl_system_properties = new GridBagLayout();
		gbl_system_properties.columnWidths = new int[]{36, 0, 0, 0};
		gbl_system_properties.rowHeights = new int[]{0, 0, 0, 0};
		gbl_system_properties.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_system_properties.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		system_properties.setLayout(gbl_system_properties);

		JLabel lblSystemProperties = new JLabel("System Properties");
		GridBagConstraints gbc_lblSystemProperties = new GridBagConstraints();
		gbc_lblSystemProperties.insets = new Insets(0, 0, 5, 5);
		gbc_lblSystemProperties.gridx = 1;
		gbc_lblSystemProperties.gridy = 0;
		system_properties.add(lblSystemProperties, gbc_lblSystemProperties);

		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 1;
		system_properties.add(scrollPane_3, gbc_scrollPane_3);

		system_properties_table = new JTable();
		system_properties_table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null},
				},
				new String[] {
						"Key", "Value"
				}
				) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4864149112901243083L;
			Class<?>[] columnTypes = new Class[] {
					String.class, String.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		system_properties_table.setFillsViewportHeight(true);
		scrollPane_3.setViewportView(system_properties_table);

		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.system_Properties_Update();

			}
		});
		GridBagConstraints gbc_btnUpdate_1 = new GridBagConstraints();
		gbc_btnUpdate_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate_1.gridx = 1;
		gbc_btnUpdate_1.gridy = 2;
		system_properties.add(btnUpdate_1, gbc_btnUpdate_1);

		edit_Account = new JPanel();
		edit_Account.setName("edit_Account");
		panel.add(edit_Account, edit_Account.getName());
		GridBagLayout gbl_edit_Account = new GridBagLayout();
		gbl_edit_Account.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_edit_Account.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_edit_Account.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_edit_Account.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		edit_Account.setLayout(gbl_edit_Account);

		JLabel lblCliensAccountInfo = new JLabel("Clien's Account Info");
		GridBagConstraints gbc_lblCliensAccountInfo = new GridBagConstraints();
		gbc_lblCliensAccountInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliensAccountInfo.gridx = 2;
		gbc_lblCliensAccountInfo.gridy = 0;
		edit_Account.add(lblCliensAccountInfo, gbc_lblCliensAccountInfo);

		JLabel lblAccountId = new JLabel("Account ID");
		GridBagConstraints gbc_lblAccountId = new GridBagConstraints();
		gbc_lblAccountId.anchor = GridBagConstraints.EAST;
		gbc_lblAccountId.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccountId.gridx = 1;
		gbc_lblAccountId.gridy = 1;
		edit_Account.add(lblAccountId, gbc_lblAccountId);

		accnt_AID = new JTextField();
		accnt_AID.setEditable(false);
		GridBagConstraints gbc_accnt_AID = new GridBagConstraints();
		gbc_accnt_AID.insets = new Insets(0, 0, 5, 5);
		gbc_accnt_AID.fill = GridBagConstraints.HORIZONTAL;
		gbc_accnt_AID.gridx = 2;
		gbc_accnt_AID.gridy = 1;
		edit_Account.add(accnt_AID, gbc_accnt_AID);
		accnt_AID.setColumns(10);

		JLabel lblClientId_1 = new JLabel("Client ID");
		GridBagConstraints gbc_lblClientId_1 = new GridBagConstraints();
		gbc_lblClientId_1.anchor = GridBagConstraints.EAST;
		gbc_lblClientId_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientId_1.gridx = 1;
		gbc_lblClientId_1.gridy = 2;
		edit_Account.add(lblClientId_1, gbc_lblClientId_1);

		accnt_CID = new JTextField();
		accnt_CID.setEditable(false);
		GridBagConstraints gbc_accnt_CID = new GridBagConstraints();
		gbc_accnt_CID.insets = new Insets(0, 0, 5, 5);
		gbc_accnt_CID.fill = GridBagConstraints.HORIZONTAL;
		gbc_accnt_CID.gridx = 2;
		gbc_accnt_CID.gridy = 2;
		edit_Account.add(accnt_CID, gbc_accnt_CID);
		accnt_CID.setColumns(10);

		JLabel lblBalance = new JLabel("Balance");
		GridBagConstraints gbc_lblBalance = new GridBagConstraints();
		gbc_lblBalance.anchor = GridBagConstraints.EAST;
		gbc_lblBalance.insets = new Insets(0, 0, 5, 5);
		gbc_lblBalance.gridx = 1;
		gbc_lblBalance.gridy = 3;
		edit_Account.add(lblBalance, gbc_lblBalance);

		accnt_Balance = new JTextField();
		accnt_Balance.setEditable(false);
		GridBagConstraints gbc_accnt_Balance = new GridBagConstraints();
		gbc_accnt_Balance.insets = new Insets(0, 0, 5, 5);
		gbc_accnt_Balance.fill = GridBagConstraints.HORIZONTAL;
		gbc_accnt_Balance.gridx = 2;
		gbc_accnt_Balance.gridy = 3;
		edit_Account.add(accnt_Balance, gbc_accnt_Balance);
		accnt_Balance.setColumns(10);

		JLabel lblCredit = new JLabel("Credit");
		GridBagConstraints gbc_lblCredit = new GridBagConstraints();
		gbc_lblCredit.anchor = GridBagConstraints.EAST;
		gbc_lblCredit.insets = new Insets(0, 0, 5, 5);
		gbc_lblCredit.gridx = 1;
		gbc_lblCredit.gridy = 4;
		edit_Account.add(lblCredit, gbc_lblCredit);

		accnt_Credit = new JTextField();
		accnt_Credit.setEditable(false);
		GridBagConstraints gbc_accnt_Credit = new GridBagConstraints();
		gbc_accnt_Credit.insets = new Insets(0, 0, 5, 5);
		gbc_accnt_Credit.fill = GridBagConstraints.HORIZONTAL;
		gbc_accnt_Credit.gridx = 2;
		gbc_accnt_Credit.gridy = 4;
		edit_Account.add(accnt_Credit, gbc_accnt_Credit);
		accnt_Credit.setColumns(10);

		JLabel lblComment_1 = new JLabel("Comment");
		GridBagConstraints gbc_lblComment_1 = new GridBagConstraints();
		gbc_lblComment_1.anchor = GridBagConstraints.EAST;
		gbc_lblComment_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblComment_1.gridx = 1;
		gbc_lblComment_1.gridy = 5;
		edit_Account.add(lblComment_1, gbc_lblComment_1);

		accnt_Comment = new JTextField();
		accnt_Comment.setEditable(false);
		GridBagConstraints gbc_accnt_Comment = new GridBagConstraints();
		gbc_accnt_Comment.insets = new Insets(0, 0, 0, 5);
		gbc_accnt_Comment.fill = GridBagConstraints.HORIZONTAL;
		gbc_accnt_Comment.gridx = 2;
		gbc_accnt_Comment.gridy = 5;
		edit_Account.add(accnt_Comment, gbc_accnt_Comment);
		accnt_Comment.setColumns(10);

		client_Deposits = new JPanel();
		client_Deposits.setName("client_Deposits");
		panel.add(client_Deposits, client_Deposits.getName());
		GridBagLayout gbl_client_Deposits = new GridBagLayout();
		gbl_client_Deposits.columnWidths = new int[]{0, 0, 0, 0};
		gbl_client_Deposits.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_client_Deposits.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_client_Deposits.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		client_Deposits.setLayout(gbl_client_Deposits);

		JLabel lblCliensDepositsInfo = new JLabel("Clien's Deposits Info");
		GridBagConstraints gbc_lblCliensDepositsInfo = new GridBagConstraints();
		gbc_lblCliensDepositsInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliensDepositsInfo.gridx = 1;
		gbc_lblCliensDepositsInfo.gridy = 0;
		client_Deposits.add(lblCliensDepositsInfo, gbc_lblCliensDepositsInfo);

		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridx = 1;
		gbc_scrollPane_4.gridy = 1;
		client_Deposits.add(scrollPane_4, gbc_scrollPane_4);

		client_Deposits_table = new JTable();
		client_Deposits_table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null, null, null},
				},
				new String[] {
						"Deposit ID", "Client ID", "Balance", "Deposit Type", "Estimated Balance", "Opening Date", "Closeing Date"
				}
				) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4462160450204977748L;
			Class<?>[] columnTypes = new Class[] {
					Long.class, Long.class, Double.class, Object.class, Double.class, Object.class, Object.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_4.setViewportView(client_Deposits_table);

		JButton btnCloseSelected = new JButton("Close Selected");
		btnCloseSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				child.close_Selected_Deposit();
			}
		});
		GridBagConstraints gbc_btnCloseSelected = new GridBagConstraints();
		gbc_btnCloseSelected.insets = new Insets(0, 0, 5, 5);
		gbc_btnCloseSelected.gridx = 1;
		gbc_btnCloseSelected.gridy = 2;
		client_Deposits.add(btnCloseSelected, gbc_btnCloseSelected);

		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				child.close_All_Client_Deposits();
			}
		});
		GridBagConstraints gbc_btnRemoveAll = new GridBagConstraints();
		gbc_btnRemoveAll.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveAll.gridx = 1;
		gbc_btnRemoveAll.gridy = 3;
		client_Deposits.add(btnRemoveAll, gbc_btnRemoveAll);

		activity_List = new JPanel();
		activity_List.setName("activity_List");
		panel.add(activity_List, activity_List.getName());
		GridBagLayout gbl_activity_List = new GridBagLayout();
		gbl_activity_List.columnWidths = new int[]{0, 0, 0, 0};
		gbl_activity_List.rowHeights = new int[]{0, 0, 0, 0};
		gbl_activity_List.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_activity_List.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		activity_List.setLayout(gbl_activity_List);

		lblActivityList = new JLabel("Activity List");
		GridBagConstraints gbc_lblActivityList = new GridBagConstraints();
		gbc_lblActivityList.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivityList.gridx = 1;
		gbc_lblActivityList.gridy = 0;
		activity_List.add(lblActivityList, gbc_lblActivityList);

		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridx = 1;
		gbc_scrollPane_5.gridy = 1;
		activity_List.add(scrollPane_5, gbc_scrollPane_5);

		activity_List_table = new JTable();
		activity_List_table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null, null},
				},
				new String[] {
						"Activity ID", "Client ID", "Amount", "Date", "Comission", "Description"
				}
				) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5334639155819874638L;
			Class<?>[] columnTypes = new Class[] {
					Long.class, Long.class, Double.class, Object.class, Double.class, String.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		activity_List_table.setFillsViewportHeight(true);
		scrollPane_5.setViewportView(activity_List_table);

		JPanel west_1 = new JPanel();
		frame.getContentPane().add(west_1, BorderLayout.WEST);
		GridBagLayout gbl_west_1 = new GridBagLayout();
		gbl_west_1.columnWidths = new int[]{0, 0};
		gbl_west_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_west_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_west_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		west_1.setLayout(gbl_west_1);

		JButton btnNewButton = new JButton("Add Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout)panel.getLayout();
				cardLayout.show(panel, add_Client.getName());
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		west_1.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Manage Client");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout)panel.getLayout();
				cardLayout.show(panel, edit_Client.getName());
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		west_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
				
						JButton btnClientsList = new JButton("Clients List");
						menuBar.add(btnClientsList);
						btnClientsList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								child.load_Clients_List();
							}
						});
				
						JButton btnAccountsList = new JButton("Accounts List");
						menuBar.add(btnAccountsList);
						btnAccountsList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								child.load_Accounts_List();				
							}
						});
				
						JButton btnDeposits = new JButton("Deposits List");
						menuBar.add(btnDeposits);
						btnDeposits.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								child.load_Deposits_List();
							}
						});
				
						JButton btnActivityList = new JButton("Activity List");
						menuBar.add(btnActivityList);
						btnActivityList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								child.load_Activity_List();
							}
						});
		
				JButton btnSystemProp = new JButton("System Prop.");
				menuBar.add(btnSystemProp);
				btnSystemProp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						child.load_System_Properties();
					}
				});
	}
}
