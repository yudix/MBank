package beans;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {

	private static final long serialVersionUID = 441833345848878848L;
	private long activityID;
	private long clientID;
	private double amount;
	private Date activityDate;
	private double commission;
	private String description;

	public Activity(long clientID, double amount, Date activityDate,
			double commission, String description) {
		super();
		this.clientID = clientID;
		this.amount = amount;
		this.activityDate = activityDate;
		this.commission = commission;
		this.description = description;
	}

	public Activity(long activityID, long clientID, double amount,
			Date activityDate, double commission, String description) {
		super();
		this.activityID = activityID;
		this.clientID = clientID;
		this.amount = amount;
		this.activityDate = activityDate;
		this.commission = commission;
		this.description = description;
	}

	public Activity() {
		super();
	}

	public long getActivityID() {
		return activityID;
	}

	public void setActivityID(long activityID) {
		this.activityID = activityID;
	}

	public long getClientID() {
		return clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Activity [activityID=" + activityID + ", clientID=" + clientID
				+ ", amount=" + amount + ", activityDate=" + activityDate
				+ ", commission=" + commission + ", description=" + description
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (activityID ^ (activityID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (activityID != other.activityID)
			return false;
		return true;
	}

}
