package commands;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Controller;

public class ErrorMessage {
	public static void setErrorRedirectMessage(HttpServletResponse response,
			HttpSession session, String errorMessage, String url)
			throws IOException {
		session.setAttribute("error", errorMessage);
		response.sendRedirect(Controller.REDIRECT + url);
	}
}
