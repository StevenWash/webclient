package cn.izhiju.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConnectProperties {
	private String ipAddress;
	private String port;
	private int tarcestart;//0表示stop ,1表示start
	private String clientId;
	private String cleanSession;//on表示没选中， off表示选中
	private int keepAlive;
	private int retryInterval;
	private String usePersistence;//0表示没选中， 1表示选中
	private String directory;
	private String topic;
	private int qos;//取值为0 ，1 ，2
	private int retained;//0表示没选中， 1表示选中
	private String data;
	
	public ConnectProperties(String ipAddress, String port, int tarcestart, String clientId, String cleanSession,
			int keepAlive, int retryInterval, String usePersistence, String directory, String topic, int qos, int retained,
			String data) {
		super();
		this.ipAddress = ipAddress;
		this.port = port;
		this.tarcestart = tarcestart;
		this.clientId = clientId;
		this.cleanSession = cleanSession;
		this.keepAlive = keepAlive;
		this.retryInterval = retryInterval;
		this.usePersistence = usePersistence;
		this.directory = directory;
		this.topic = topic;
		this.qos = qos;
		this.retained = retained;
		this.data = data;
	}


	//setter  getter
	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public int getTarcestart() {
		return tarcestart;
	}


	public void setTarcestart(int tarcestart) {
		this.tarcestart = tarcestart;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String getCleanSession() {
		return cleanSession;
	}


	public void setCleanSession(String cleanSession) {
		this.cleanSession = cleanSession;
	}


	public int getKeepAlive() {
		return keepAlive;
	}


	public void setKeepAlive(int keepAlive) {
		this.keepAlive = keepAlive;
	}


	public int getRetryInterval() {
		return retryInterval;
	}


	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}


	public String getUsePersistence() {
		return usePersistence;
	}


	public void setUsePersistence(String usePersistence) {
		this.usePersistence = usePersistence;
	}


	public String getDirectory() {
		return directory;
	}


	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public int getQos() {
		return qos;
	}


	public void setQos(int qos) {
		this.qos = qos;
	}


	public int getRetained() {
		return retained;
	}


	public void setRetained(int retained) {
		this.retained = retained;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "ConnectProperties [ipAddress=" + ipAddress + ", port=" + port + ", tarcestart=" + tarcestart
				+ ", clientId=" + clientId + ", cleanSession=" + cleanSession + ", keepAlive=" + keepAlive
				+ ", retryInterval=" + retryInterval + ", usePersistence=" + usePersistence + ", directory=" + directory
				+ ", topic=" + topic + ", qos=" + qos + ", retained=" + retained + ", data=" + data + "]";
	}

}
