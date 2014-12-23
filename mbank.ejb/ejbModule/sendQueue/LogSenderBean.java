package sendQueue;

import helper.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

@Stateless
public class LogSenderBean implements LogSender {
	private static final String lookupFactory = "java:/ConnectionFactory";
	private static final String lookupDestination = "java:/queue/test";

//	private InitialContext ctx;
	@Resource(mappedName=lookupFactory)
	private QueueConnectionFactory factory;
	private QueueConnection connection;
	private QueueSession session;
	@Resource(mappedName=lookupDestination)
	private Queue destination;
	private TextMessage message;
	private QueueSender sender;

	public LogSenderBean() {
	}

	@PostConstruct
	public void init() {
		try {

			connection = factory.createQueueConnection();
			session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
			sender = session.createSender(destination);
			message = session.createTextMessage();
		} catch (Exception e) {
		}
	}

	@Override
	public void addLog(Long clientId, String action) {
		try {
			Log log = new Log(clientId, action);
			this.message.setText(log.toXMl());
			this.sender.send(message);
			System.out.println("LogSenderBean.addLog() was sended Log");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PreDestroy
	public void destory() {
		try {
			this.connection.stop();

			this.sender.close();
			this.session.close();
			this.connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
