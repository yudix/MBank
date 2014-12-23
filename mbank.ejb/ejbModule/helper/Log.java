package helper;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
@NamedQuery(
	name = "LogGetAll", 
	query = "SELECT l FROM Log AS l ORDER BY l.timeStamp DESC"),
@NamedQuery(
	name = "LogGetAllByID", 
	query = "SELECT l FROM Log AS l WHERE l.clientId LIKE :clientId ORDER BY l.timeStamp DESC")
})
@XmlRootElement
@Entity
@Table(name = "MBANK_LOGS")
public class Log {

	private Long logId;
	private Long clientId;
	private String action;
	private Date timeStamp;

	// CTOR empty
	public Log() {
	}

	// CTOR
	public Log(Long clientId, String action) {
		super();
		this.clientId = clientId;
		this.action = action;
		this.timeStamp = new Date();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "LOG_ID")
	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "CLIENT_ID")
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	@Column(name = "ACTION")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP")
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String toXMl() {
		StringWriter writer = new StringWriter();
		JAXB.marshal(this, writer);
		return writer.toString();
	}

	public static Log fromXML(String xml) {
		StringReader reader = new StringReader(xml);
		return JAXB.unmarshal(reader, Log.class);
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", clientId=" + clientId + ", action="
				+ action + ", timeStamp=" + timeStamp + "]";
	}
}
