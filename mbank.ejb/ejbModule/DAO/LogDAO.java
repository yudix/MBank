package DAO;

import helper.Log;

import java.util.List;

import javax.ejb.Local;
@Local
public interface LogDAO {	
	public void addLog(Log log);
	public List<Log> getAll();
	public List<Log> getLogById(Long id);
}
