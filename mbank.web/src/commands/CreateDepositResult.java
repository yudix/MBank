package commands;

import helpers.MBankException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Controller;
import action.ClientActionInterface;
import beans.Client;

public class CreateDepositResult implements Command {

	private double initialDeposit;
	private int yearsOfDeposit;
	private int monthOfDeposit;

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {
		try {
			// checking if the parameters is a number
			isParameterNAN(request);
		} catch (NumberFormatException e) {
			// sending Redirect with error
			String errorMessage = "You must enter valid numbers";
			String url = "createDeposit.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
			return;
		}
		try {
			// creating the deposit and sending back
			// all the deposits of client including the new one.
			clientAction.createNewDeposit( initialDeposit,
					 monthOfDeposit, yearsOfDeposit);
			LoadDeposits.loadDeposits(session, clientAction);
			response.sendRedirect(Controller.REDIRECT + "viewDeposits.jsp");
		} catch (MBankException e1) {
			// if there is any other error sending the message to the client
			// and sending redirect to the same page.
			String errorMessage = e1.getMessage();
			String url = "createDeposit.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
			return;
		}
	}

	private void isParameterNAN(HttpServletRequest request) {
		initialDeposit = (Double.parseDouble(request.getParameter("sum")));
		yearsOfDeposit = (Integer.parseInt(request.getParameter("years")));
		monthOfDeposit = (Integer.parseInt(request.getParameter("months")));
	}
}
