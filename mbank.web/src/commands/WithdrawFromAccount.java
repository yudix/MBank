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

public class WithdrawFromAccount implements Command {

	private double withdraw;

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {

		try {
			withdraw = Double.parseDouble(request.getParameter("withdraw"));

		} catch (NumberFormatException e1) {

			String errorMessage = "You must enter valid numbers";
			String url = "withdrawFromAccount.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}

		try {
			Account account = clientAction
					.withdrawFromAccount(withdraw);
			String successMessage = "your account was updated<br/> your withdraw: "
					+ withdraw;

			session.setAttribute("account", account);
			session.setAttribute("success", successMessage);

			String forwardUrl = "/withdrawFromAccount.jsp";
			Forward.forward(request, response, forwardUrl);

		} catch (MBankException e1) {
			String errorMessage = e1.getMessage() + " amount of: "
					+ withdraw;
			String url = "withdrawFromAccount.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}
	}
}
