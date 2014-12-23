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

public class PreOpenDeposit implements Command {

	private long depositId;

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {
		
		// after submitting pre-opening deposit
		try {
			// validating deposit id number
			depositId = (Integer.parseInt(request.getParameter("depositId")));

		} catch (NumberFormatException e1) {

			String errorMessage = "You must enter valid deposit id";
			String url = "preOpenDeposit.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}
		
		try {

			clientAction.preOpenDeposit(depositId);
			Account account = clientAction.viewAccountDetails();
			session.setAttribute("account", account);
			session.setAttribute("success", "Your deposit has been pre-opened");
			LoadDeposits.loadDeposits(session, clientAction);

			String forwardUrl = "/preOpenDeposit.jsp";
			Forward.forward(request, response, forwardUrl);
		
		} catch (MBankException e1) {
			String errorMessage = e1.getMessage();
			String url = "preOpenDeposit.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}
	}
}
