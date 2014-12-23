package session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mBank.MBank;
import action.ClientAction;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ClientAction clientAction = (ClientAction) event.getSession().getAttribute("clientAction");
		if (clientAction != null) {
			if (clientAction.getConnection() != null) {
				MBank.INSTANCE.logout(clientAction);
			}
		}

	}

}