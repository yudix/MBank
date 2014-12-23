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
import beans.Property;

public class ViewProperty implements Command {

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {

		try {
			List<Property> properties = clientAction.viewSystemProperties();
			request.setAttribute("prop", properties);

			String forwardUrl = "/viewProperty.jsp";
			Forward.forward(request, response, forwardUrl);

		} catch (MBankException e) {
			String errorMessage = e.getMessage();
			String url = "viewProperty.jsp";
			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}

	}

}
