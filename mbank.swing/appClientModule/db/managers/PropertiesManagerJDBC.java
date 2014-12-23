package db.managers;

import helpers.MBankException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Property;

public class PropertiesManagerJDBC implements PropertiesManager {
	private Connection connection;

	public PropertiesManagerJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Property viewProperty(String prop_key) throws MBankException {
		String sql = "SELECT * FROM mbank.properties where prop_key = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, prop_key);
			ResultSet resultset = pstmt.executeQuery();
			pstmt.closeOnCompletion();

			while (resultset.next()) {
				Property property = new Property(
						resultset.getString("prop_key"),
						resultset.getString("prop_value"));

				return property;
			}
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
		throw new MBankException("no such a property");
	}

	@Override
	public void updateProperty(Property property) throws MBankException {
		String sql = "UPDATE `mbank`.`properties` SET `prop_value`=? WHERE `prop_key`=?;";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, property.getPropValue());
			try {
				property = viewProperty(property.getPropKey());
			} catch (MBankException e) {
				throw e;
			}
			pstmt.setString(2, property.getPropKey());

			pstmt.execute();
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}

	public List<Property> viewAllProperties() throws MBankException {
		String sql = "SELECT * FROM mbank.properties;";
		List<Property> properties = new ArrayList<>();

		try (Statement statement = connection.createStatement()) {
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				properties.add(new Property(resultset.getString("prop_key"),
						resultset.getString("prop_value")));
			}
			return properties;
		} catch (SQLException e) {
			throw new MBankException(e.getMessage());
		}
	}

}
