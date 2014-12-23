package commands;

import helpers.MBankException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ClientActionInterface;
import beans.Client;

public class UpdateClient implements Command {
	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {

		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		try {
			clientAction.updateClientDetails(address, email, phone);
			client = clientAction.viewClientdetails();
			
			String forwardUrl = "/updateClient.jsp";
			String succesMessage = "Your details have been updated";

			session.setAttribute("client", client);
			session.setAttribute("success", succesMessage);
			
			Forward.forward(request, response, forwardUrl);
		
		} catch (MBankException e) {
			String errorMessage = e.getMessage();
			String url = "updateClient.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);

		}

	}
}
