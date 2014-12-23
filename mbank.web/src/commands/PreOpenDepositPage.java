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

public class PreOpenDepositPage implements Command {

	private Account account;

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {

		try {
			// before pre-open showing account balance
			// and all deposits of client,
			LoadDeposits.loadDeposits(session, clientAction);
			setAccountSessionAttribute(request, response, session, clientAction);
		
		} catch (MBankException e2) {
			String errorMessage = e2.getMessage();
			String url = "preOpenDeposit.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
			return;
		}

	}

	private void setAccountSessionAttribute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction) throws MBankException,
			ServletException, IOException {

		if (null == session.getAttribute("account")) {
			account = clientAction.viewAccountDetails();
			session.setAttribute("account", account);
		}
		String forwardUrl = "/preOpenDeposit.jsp";
		Forward.forward(request, response, forwardUrl);
	}

}
