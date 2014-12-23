package commands;

import helpers.MBankException;

import java.util.List;

import javax.servlet.http.HttpSession;

import action.ClientActionInterface;
import beans.Deposit;

public class LoadDeposits {
	
	public static void loadDeposits(HttpSession session,
			ClientActionInterface clientAction) throws MBankException {
		List<Deposit> deposits = clientAction.viewClientDeposits();
		session.setAttribute("deposits", deposits);
	}

}
