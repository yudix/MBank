package helpers;

import db.managers.PropertiesManager;

public class PropertiesUtil {
	// private Connection connection;
	private PropertiesManager mgr;

	// = new PropertiesManagerJDBC(connection);
	public PropertiesUtil(PropertiesManager propertiesManager) {
		// this.connection = connection;
		this.mgr = propertiesManager;
	}

	// 1
	public double getRegularDepositRate() throws MBankException {
		try {
			String prop_key = "regular_deposit_rate";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException | MBankException e) {
			throw new MBankException(e.getMessage()
					+ " unable to determine deposit rate");
		}
	}

	// 2
	public double getGoldDepositRate() throws MBankException {
		try {
			String prop_key = "gold_deposit_rate";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine deposit rate");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 3
	public double getPlatinumDepositRate() throws MBankException {
		try {
			String prop_key = "platinum_deposit_rate";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine deposit rate");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 7
	public double getRegularCreditLimit() throws MBankException {
		try {
			String prop_key = "regular_credit_limit";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine client credit limit");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 8
	public double getGoldCreditLimit() throws MBankException {
		try {
			String prop_key = "gold_credit_limit";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine client credit limit");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 9
	public String getPlatinumCreditLimit() throws MBankException {
		try {
			String prop_key = "platinum_credit_limit";
			String result = mgr.viewProperty(prop_key).getPropValue();
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine client credit limit");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 4
	public double getRegularDepositCommission() throws MBankException {
		// double result;
		try {
			String prop_key = "regular_deposit_commission";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine deposit commission");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 5
	public double getGoldDepositCommission() throws MBankException {
		// double result;
		try {
			String prop_key = "gold_deposit_commission";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine deposit commission");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 6
	public double getPlatinumDepositCommission() throws MBankException {
		double result;
		try {
			String prop_key = "platinum_deposit_commission";
			result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine deposit commission");
		} catch (MBankException e) {
			throw e;
		}
		return result;

	}

	public double getPreOpenFee() throws MBankException {
		try {
			String prop_key = "pre_open_fee";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException("unable to determine pre open fee");
		} catch (MBankException e) {
			throw e;
		}
	}

	public double getCommissionRate() throws MBankException {
		try {
			String prop_key = "commission_rate";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException(
					"unable to determine client Commission Rate");
		} catch (MBankException e) {
			throw e;
		}
	}

	public double getRegularDailyInterest() throws MBankException {

		try {
			String prop_key = "regular_daily_interest";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException(
					"unable to determine deposit daily interset rate");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 12
	public double getGoldDailyInterest() throws MBankException {
		try {
			String prop_key = "gold_daily_interest";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException(
					"unable to determine deposit daily interset rate");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 13
	public double getPlatinumDailyInterest() throws MBankException {
		try {
			String prop_key = "platinum_daily_interest";
			double result = Double.parseDouble(mgr.viewProperty(prop_key)
					.getPropValue());
			// ("").getPropValue());
			return result;
		} catch (NumberFormatException e) {
			throw new MBankException(
					"unable to determine deposit daily interset rate");
		} catch (MBankException e) {
			throw e;
		}
	}

	// 15
	public String getAdminUserName() throws MBankException {
		try {
			String prop_key = "admin_username";
			return mgr.viewProperty(prop_key).getPropValue();
		} catch (MBankException e) {
			throw e;
		}
	}

	// 16
	public String getAdminPassword() throws MBankException {
		try {
			String prop_key = "admin_password";
			return mgr.viewProperty(prop_key).getPropValue();
		} catch (MBankException e) {
			throw e;
		}
	}

}
