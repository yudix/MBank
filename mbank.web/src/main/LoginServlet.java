package main;

import helpers.MBankException;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import proxy.LoggerProxy;
import mBank.MBank;
import action.ClientAction;
import action.ClientActionInterface;
import beans.Client;

@WebServlet({ "/LoginServlet", "/Login" })
public class LoginServlet extends HttpServlet {
	private static final String REDIRECT = "/mbank.web/login.jsp";
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private ClientAction ca;

	public LoginServlet() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ClientActionInterface clientAction = null;
		HttpSession session = request.getSession(false);
		Client client = null;

		String action = request.getParameter("action");

		if (action != null && action.equals("login")) {

			// invalidate session if any
			if (session != null) {
				session.invalidate();
			}
			String userName = request.getParameter("user");
			String password = request.getParameter("pass");
			try {
				ca = MBank.INSTANCE.clientLogin(userName, password);
				connection = ca.getConnection();
				clientAction = ca;
			} catch (MBankException e1) {
				session = request.getSession(true);
				session.setAttribute("error", e1.getMessage());
				response.sendRedirect(REDIRECT);
				return;
			}

			if (clientAction != null) {
				try {

					client = clientAction.viewClientdetails();
					session = request.getSession(true);
					// Capitalizing the first Letter of client name
					String name = client.getClientName();
					String nameCapitalizingFirstLetter = name.substring(0, 1)
							.toUpperCase() + name.substring(1);
					client.setClientName(nameCapitalizingFirstLetter);
					LoggerProxy proxy = new LoggerProxy(connection, clientAction, client);

					session.setAttribute("client", client);
					session.setAttribute("clientAction", proxy);
				} catch (MBankException e) {
					session = request.getSession(true);
					session.setAttribute("error", e.getMessage());
					response.sendRedirect(REDIRECT);
				}

				session.setAttribute("user", userName);
				// last visit date
				String lastVisitDate = null;
				Cookie[] cookies = request.getCookies();
				// //search if there is Cookies
				if (cookies != null && cookies.length > 0) {
					// search if there is Cookie lastVisit
					for (Cookie c : cookies) {
						if (c.getName().equals("lastVisit")) {
							lastVisitDate = c.getValue();
							break;
						}
					}
				}
				// setting "lastVisit" Cookie with current Date
				Cookie c = new Cookie("lastVisit", new Date().toString());
				// cookie max age is a week
				c.setMaxAge(1000 * 60 * 60 * 24 * 7);
				// sending cookie to client
				response.addCookie(c);
				// if there isn't cookie lastVisit creating new one
				if (lastVisitDate == null)
					lastVisitDate = new Date().toString();
				session.setAttribute("lastVisit", lastVisitDate);
				// forwarding the 'user name' and 'last visit date' to main page
				getServletContext().getRequestDispatcher("/main.jsp").forward(
						request, response);
			} else {

				// if client 'user name' and 'password' combination is incorrect
				session = request.getSession(true);
				session.setAttribute("error", "Incorrect username or password");
				response.sendRedirect(REDIRECT);
			}
		} else {
			session = request.getSession(false);
			response.sendRedirect("/mbank.web/main.jsp");
		}
	}

}
