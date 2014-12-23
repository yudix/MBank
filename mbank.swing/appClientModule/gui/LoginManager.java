package gui;

import helpers.Connector;
import helpers.MBankException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.Property;
import db.managers.PropertiesManager;
import db.managers.PropertiesManagerJDBC;


public class LoginManager {

	public Boolean login(String username, String password) {
		try (Connection con=Connector.getConnection();){
			if (con==null) throw new MBankException("swing - no connection");
			String x,y;
			PropertiesManager pdbm = new PropertiesManagerJDBC(con);
			Property pau =pdbm.viewProperty( "admin_username");
			Property pap =pdbm.viewProperty("admin_password");
			x= pau.getPropValue();
			y=pap.getPropValue();
			if (username.equals(x) &&
					password.equals(y)) {
				return true;
			};
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MBankException e) {
			JOptionPane.showMessageDialog(new JPanel(), "Error code: "+e.getMessage()+"\n"+e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}
