package helper;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import DAO.LogDAO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "java:/queue/test"),
		@ActivationConfigProperty(
				propertyName = "destinationType", 
				propertyValue = "javax.jms.Queue") })
public class LogMDB implements MessageListener {

	@EJB
	private LogDAO logDAO;

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("LogMDB.onMessage()");
			if (message instanceof TextMessage) {
				String text = ((TextMessage) message).getText();
				System.out.println(text);
				Log log = Log.fromXML(text);
				logDAO.addLog(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
