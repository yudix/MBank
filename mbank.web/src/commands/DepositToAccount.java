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

public class DepositToAccount implements Command {

	private static final String DEPOSIT_TO_ACCOUNT_JSP = "depositToAccount.jsp";
	private double depositAmount;
	private Account account;

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {
		try {
			depositAmount = Double.parseDouble(request
					.getParameter("depositAmount"));
		} catch (NumberFormatException e1) {
			String errorMessage = "You must enter valid numbers";
			String url = DEPOSIT_TO_ACCOUNT_JSP;
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}
		
		try {
			account = clientAction.depositToAccount( depositAmount);
			String success = "your account was updated<br/> your deposit: "
					+ depositAmount;
			session.setAttribute("account", account);
			session.setAttribute("success", success);
			String forwardUrl = "/depositToAccount.jsp";
			Forward.forward(request, response, forwardUrl);

		} catch (MBankException e1) {
			String errorMessage = e1.getMessage();
			String url = DEPOSIT_TO_ACCOUNT_JSP;
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}

	}

}
