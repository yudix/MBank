package db.managers;

import java.util.List;

import helpers.MBankException;
import beans.Property;

public interface PropertiesManager {

	public Property viewProperty(String prop_key) throws MBankException;

	public void updateProperty(Property property) throws MBankException;

	public List<Property> viewAllProperties() throws MBankException;

}
