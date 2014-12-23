package ws;

import java.util.List;

import helper.Log;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import DAO.LogDAO;

@WebService
@Stateless
public class WSBean {
	
	public WSBean(){}
	
	@EJB
	private LogDAO logDAO;
    @Lock(LockType.READ)
	@WebResult(name="AllUseresLog")
	public Log[] getAll() {	
		List<Log> logs = logDAO.getAll();
		return logs.toArray(new Log[0]);
	}
    
    @Lock(LockType.READ)
	@WebResult(name="UserLog")
	public Log[] getAllByClientID(@WebParam(name="clientID")Long clientID) {
		List<Log> logs = logDAO.getLogById(clientID);
		return logs.toArray(new Log[0]);
	}
}
