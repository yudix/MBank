package commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ClientActionInterface;
import beans.Client;



public interface Command 
 {
	public void service(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException ;
	
}
