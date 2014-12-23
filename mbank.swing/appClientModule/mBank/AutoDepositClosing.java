package mBank;

import helpers.MBankException;

import java.util.concurrent.TimeUnit;

import action.Action;

/**
 * AutoDepositClosing is a thread (implements Runnable) which runs once a day
 * and closes all the deposits which needs to be closed (according to
 * close_date) after its done the thread goes to sleep until the next day
 */
public class AutoDepositClosing implements Runnable {
	private Action action;

	public AutoDepositClosing(Action action) {
		super();
		this.action = action;
	}

	@Override
	public void run() {
		while (action != null) {
			try {
				action.closeDepositAutomatic(action.getConnection());
			} catch (MBankException e) {
				System.err.println("AutoDepositClosing.run()\n"
						+ e.getLocalizedMessage());
				e.printStackTrace();
			}
			try {
				TimeUnit.DAYS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
