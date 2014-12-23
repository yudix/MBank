package sendQueue;

import javax.ejb.Remote;

@Remote
public interface LogSender {
	public void addLog(Long clientId, String action);
}
