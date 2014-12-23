package commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Forward  {

	public static void forward(HttpServletRequest request,
			HttpServletResponse response, String forwardUrl)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher(forwardUrl)
				.forward(request, response);
	}
}
