package db.managers;

import helpers.MBankException;

import java.util.List;

import beans.Client;

public interface ClientManager {

	Client addClient(Client client) 
			throws MBankException;

	Client viewClientDetails(long clientID) 
			throws MBankException;

	Client viewClientDetails(String clientName, String password) 
			throws MBankException;

	void updateClientDetails(Client client) 
			throws MBankException;

	void removeClient(long clientID) 
			throws MBankException;

	List<Client> viewAllClients() 
			throws MBankException;

}
