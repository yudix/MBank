package db.managers;

import helpers.ClientType;
import helpers.Connector;
import helpers.MBankException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Client;

public class ClientManagerJDBC implements ClientManager {

	private Connection connection;

	public ClientManagerJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Client addClient(Client client) throws MBankException {
		String sql = "INSERT INTO `mbank`.`clients` ( `client_name`, `client_password`, `type`, `address`, `email`, `phone`, `comment`) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try (PreparedStatement ps = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, client.getClientName());
			ps.setString(2, client.getPassword());
			ps.setString(3, client.getType().toString());
			ps.setString(4, client.getAddress());
			ps.setString(5, client.getEmail());
			ps.setString(6, client.getPhone());
			ps.setString(7, client.getComments());
			ps.executeUpdate();

			ResultSet id = ps.getGeneratedKeys();
			id.next();
			int autoGeneratedID = id.getInt(1);
			client.setClientID(autoGeneratedID);

			return client;
		} catch (SQLException e) {
			if (e.getMessage().startsWith("Duplicate entry")) {
				throw new MBankException("A Client with name "
						+ client.getClientName() + " is already exist!");
			} else {
				throw new MBankException(e.getMessage());
			}
		}
	}

	@Override
	public Client viewClientDetails(long clientID) throws MBankException {
		String selectSQL = "SELECT * FROM mbank.clients where client_id=?";
		try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
			pstmt.setLong(1, clientID);
			ResultSet resultset = pstmt.executeQuery();

			// if (!(resultset.next())) {
			// throw new MBankException("Client: " + clientID + " not found");
			// }
			while (resultset.next()) {
				Client client = new Client(clientID,
						resultset.getString("client_name"),
						resultset.getString("client_password"),
						ClientType.valueOf(resultset.getString("type")),
						resultset.getString("address"),
						resultset.getString("email"),
						resultset.getString("phone"),
						resultset.getString("comment"));
				return client;
			}
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
		throw new MBankException("Client: " + clientID + " not found");
	}

	@Override
	public Client viewClientDetails(String clientName, String password)
			throws MBankException {
		String sql = "SELECT * FROM mbank.clients where client_name ="
				+ "? and client_password = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, clientName);
			pstmt.setString(2, password);// ;(1, clientID);
			ResultSet resultset = pstmt.executeQuery();
			pstmt.closeOnCompletion();
			while (resultset.next()) {
				return new Client(resultset.getLong("client_id"),
						resultset.getString("client_name"),
						resultset.getString("client_password"),
						ClientType.valueOf(resultset.getString("type")),
						resultset.getString("address"),
						resultset.getString("email"),
						resultset.getString("phone"),
						resultset.getString("comment"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		// String sql =
		// "SELECT * FROM mbank.clients where client_name = ? and client_password = ?;";
		// try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		// pstmt.setString(1, clientName);
		// pstmt.setString(2, password);// ;(1, clientID);
		// ResultSet resultset = pstmt.executeQuery();
		// pstmt.closeOnCompletion();
		// // try (Statement statement = connection.createStatement()) {
		// // ResultSet resultset = statement.executeQuery(sql);
		// while (resultset.next()) {
		// return new Client(resultset.getLong("client_id"),
		// resultset.getString("client_name"),
		// resultset.getString("client_password"),
		// ClientType.valueOf(resultset.getString("type")),
		// resultset.getString("address"),
		// resultset.getString("email"),
		// resultset.getString("phone"),
		// resultset.getString("comment"));
		// }
		// } catch (SQLException e) {
		// throw new MBankException(e.getMessage());
		//
		// }
		// throw new MBankException("Client: " + clientName + " not found");

	}

	public Client viewStringClientDetails(String clientName, String password)
			throws MBankException {
		String sql = "SELECT * FROM mbank.clients where client_name = '"
				+ clientName + "' and client_password = '" + password + "';";
		// try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		// pstmt.setString(1, clientName);
		// pstmt.setString(2, password);// ;(1, clientID);
		// ResultSet resultset = pstmt.executeQuery();
		// pstmt.closeOnCompletion();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				return new Client(resultset.getLong("client_id"),
						resultset.getString("client_name"),
						resultset.getString("client_password"),
						ClientType.valueOf(resultset.getString("type")),
						resultset.getString("address"),
						resultset.getString("email"),
						resultset.getString("phone"),
						resultset.getString("comment"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateClientDetails(Client client) throws MBankException {
		String sql = "UPDATE `mbank`.`clients` SET "
				+ "`type`=?, `address`=?, `email`=?, `phone`=?, `comment`=? "
				+ "WHERE  `client_id`= ?;";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, client.getType().toString());
			pstmt.setString(2, client.getAddress());
			pstmt.setString(3, client.getEmail());
			pstmt.setString(4, client.getPhone());
			pstmt.setString(5, client.getComments());
			pstmt.setLong(6, client.getClientID());
			pstmt.executeUpdate();
			pstmt.closeOnCompletion();
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}

	@Override
	public void removeClient(long clientID) throws MBankException {
		String sql = "DELETE FROM `mbank`.`clients` WHERE `client_id`=?;";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setLong(1, clientID);
			pstmt.executeUpdate();
			pstmt.closeOnCompletion();
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}

	}

	@Override
	public List<Client> viewAllClients() throws MBankException {
		String sql = "SELECT * FROM mbank.clients;";
		List<Client> clients = new ArrayList<>();

		try (Statement statement = connection.createStatement()) {
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				clients.add(new Client(resultset.getLong("client_id"),
						resultset.getString("client_name"), resultset
								.getString("client_password"), ClientType
								.valueOf(resultset.getString("type")),
						resultset.getString("address"), resultset
								.getString("email"), resultset
								.getString("phone"), resultset
								.getString("comment")));
			}
			return clients;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}
//	public static void main(String[] args) {
//		Connection connection = Connector.getConnection();
//		ClientManagerJDBC m = new ClientManagerJDBC(connection);
//		Client c = new Client("z", "z", "z", "z", "z", "zz");
//		c.setType(ClientType.GOLD);
//		try {
////		c = 	m.addClient(c);
////		c = m.viewClientDetails(5);
////			c = m.viewClientDetails("z","z");
////			c.setComments("scvdsdscvd");
////			m.updateClientDetails(c);
////			for(Client c1 : m.viewAllClients())
////			System.out.println(c1);
////			m.removeClient(c.getClientID());
////			System.out.println((c!=null)?"removed":"not removed");
//		} catch (MBankException e) {
//			e.printStackTrace();
//		}
//	}
}