package db.managers;

import helpers.MBankException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Account;

public class AccountManagerJDBC implements AccountManager {
	private Connection connection;

	public AccountManagerJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Account addAccount(Account account) throws MBankException {
		String sql = "INSERT INTO `mbank`.`accounts` ( `client_id`, `balance`, `credit_limit`, `comment`) VALUES ( ?, ?, ?, ?);";

		try (PreparedStatement pstmt = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setLong(1, account.getClientID());
			pstmt.setDouble(2, account.getBalance());
			pstmt.setDouble(3, account.getCreditLimit());
			pstmt.setString(4, account.getComment());
			pstmt.executeUpdate();

			ResultSet id = pstmt.getGeneratedKeys();
			id.next();
			int autoGeneratedID = id.getInt(1);
			account.setAccountID(autoGeneratedID);

			return account;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}

	@Override
	public Account viewAccountDetails(long clientID) throws MBankException {
		String sql ="SELECT * FROM mbank.accounts where client_id = ?";
		
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setLong(1, clientID);
			ResultSet resultset = pstmt.executeQuery();
			pstmt.closeOnCompletion();

//			Account account = null;
//			if(!(resultset.next())){
//				throw new MBankException("No Account found for Client :"+ clientID);
//			}
				while (resultset.next()) {
					Account	account = new Account(resultset.getLong("account_id"),
								clientID,
								resultset.getDouble("balance"),
								resultset.getDouble("credit_limit"),
								resultset.getString("comment")
							);
					return account;
				}
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
		throw new MBankException("No Account found for Client :"+ clientID);

	}

	@Override
	public void updateAccountDetails(Account account) throws MBankException {
		String sql = "UPDATE `mbank`.`accounts` SET `balance`=? WHERE `client_id`=?;";
		
		try (PreparedStatement pstmt = connection.prepareStatement(sql)){	
		pstmt.setDouble	(1, account.getBalance());
		pstmt.setLong	(2, account.getClientID());

		pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}

	@Override
	public void removeAccount(long clientID) throws MBankException {
		String sql = "DELETE FROM `mbank`.`accounts` WHERE `client_id`=?;";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setLong(1, clientID);
			pstmt.executeUpdate();
			pstmt.closeOnCompletion();

		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}

	@Override
	public List<Account> viewAllAccounts() throws MBankException {
		String sql = "SELECT * FROM mbank.accounts;";
		List<Account> accounts = new ArrayList<>();
		
		try (Statement statement = connection.createStatement()) {
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				accounts.add(new Account(
						resultset.getLong("account_id"),
						resultset.getLong("client_id"),
						resultset.getDouble("balance"),
						resultset.getDouble("credit_limit"),
						resultset.getString("comment")
						));
			}
			return accounts;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}	
}
