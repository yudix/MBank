package commands;

import helpers.MBankException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ClientActionInterface;
import beans.Account;
import beans.Client;

public class WithdrawFromAccountPage implements Command {

	private Account account;

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {

		try {
			account = clientAction.viewAccountDetails();
			session.setAttribute("account", account);

			String forwardUrl = "/withdrawFromAccount.jsp";
			Forward.forward(request, response, forwardUrl);
		
		} catch (MBankException e) {		
			String errorMessage = e.getMessage();// "Problem getting account from DataBase";
			String url = "withdrawFromAccount.jsp";
			
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}
	}

}
