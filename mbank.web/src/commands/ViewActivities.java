package commands;

import helpers.MBankException;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ClientActionInterface;
import beans.Activity;
import beans.Client;

public class ViewActivities implements Command {

	private List<Activity> activities;

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ClientActionInterface clientAction, Client client)
			throws ServletException, IOException {

		try {
			activities = clientAction.viewClientActivities();
			session.setAttribute("activities", activities);

			String forwardUrl = "/viewActivities.jsp";
			Forward.forward(request, response, forwardUrl);

		} catch (MBankException e1) {
			String errorMessage = e1.getMessage();
			String url = "viewActivities.jsp";

			ErrorMessage.setErrorRedirectMessage(response, session,
					errorMessage, url);
		}
	}

}
