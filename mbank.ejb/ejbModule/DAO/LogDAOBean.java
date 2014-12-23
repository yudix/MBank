package DAO;

import helper.Log;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LogDAOBean implements LogDAO {
	
	@PersistenceContext(unitName = "Logger")
	private EntityManager entityManager;
	
	public LogDAOBean(){}

	@Override
	public void addLog(Log log) {
		System.out.println("LogDAOBean.addLog()");
		entityManager.persist(log);
	}

	@Override
	public List<Log> getAll() {
		System.out.println("LogDAOBean.getAll()");
		return this.entityManager.createNamedQuery("LogGetAll", Log.class)
				.getResultList();
	}

	@Override
	public List<Log> getLogById(Long clientID) {
		System.out.println("LogDAOBean.getLogById()");
		return this.entityManager
				.createNamedQuery("LogGetAllByID", Log.class)
				.setParameter("clientId", clientID).getResultList();
	}

}
