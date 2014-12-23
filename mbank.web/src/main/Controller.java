package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ClientActionInterface;
import beans.Client;

import commands.Command;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String REDIRECT = "/mbank.web/";

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		session.removeAttribute("error");
		session.removeAttribute("success");

		ClientActionInterface clientAction = (ClientActionInterface) session
				.getAttribute("clientAction");
		Client client = (Client) session.getAttribute("client");
		String command = "commands." + request.getParameter("command");

		try {
			((Command) (Class.forName(command).newInstance())).
			service(request, response, session, clientAction, client);
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
