package db.managers;

import helpers.MBankException;

import java.util.List;

import beans.Activity;

public interface ActivityManager {
	
	Activity addActivity(Activity activity) 
			throws MBankException;

	List<Activity> viewClientActivities(long clientID) 
			throws MBankException;
	
	List<Activity> viewAllActivities() 
			throws MBankException;
	
}
