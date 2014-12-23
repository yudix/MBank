package commands;

import helpers.MBankException;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ClientActionInterface;
import beans.Client;
import beans.Deposit;

public class ViewDeposits implements Command {

	private List<Deposit> deposits;	
	
	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {
		
		try {
			deposits = clientAction.viewClientDeposits();
			session.setAttribute("deposits", deposits);
			
			String forwardUrl = "/viewDeposits.jsp";
			Forward.forward(request, response, forwardUrl);
		
		} catch (MBankException e) {
			String errorMessage = e.getMessage();
			String url = "viewDeposits.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}
	}
}
