package session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "SessionFilter", urlPatterns = { "/Controller",
		"/main.jsp" })
public class SessionFilter implements Filter {

	private static final String REDIRECT = "/mbank.web/welcome.jsp";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		
		if (null == session.getAttribute("clientAction")){ 
			((HttpServletResponse) response).sendRedirect(REDIRECT);
		} else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {}
	public void destroy() {}
}
