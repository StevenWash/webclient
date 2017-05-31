package cn.izhiju.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConnectProperties {
	private String ipAddress;
	private String port;
	private String tarcestart;//0��ʾstop ,1��ʾstart
	private String clientId;
	private String cleanSession;//on��ʾûѡ�У� off��ʾѡ��
	private String keepAlive;
	private String retryInterval;
	private String usePersistence;//0��ʾûѡ�У� 1��ʾѡ��
	private String directory;
	private String topic;
	private String qos;//ȡֵΪ0 ��1 ��2
	private String retained;//0��ʾûѡ�У� 1��ʾѡ��
	private String data;
	
	public ConnectProperties() {
		// TODO Auto-generated constructor stub
	}
	
	public ConnectProperties(String ipAddress, String port, String tarcestart,
			String clientId, String cleanSession, String keepAlive,
			String retryInterval, String usePersistence, String directory,
			String topic, String qos, String retained, String data) {
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

	@Override
	public String toString() {
		return "ConnectProperties [ipAddress=" + ipAddress + ", port=" + port + ", tarcestart=" + tarcestart
				+ ", clientId=" + clientId + ", cleanSession=" + cleanSession + ", keepAlive=" + keepAlive
				+ ", retryInterval=" + retryInterval + ", usePersistence=" + usePersistence + ", directory=" + directory
				+ ", topic=" + topic + ", qos=" + qos + ", retained=" + retained + ", data=" + data + "]";
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


	public String getTarcestart() {
		return tarcestart;
	}


	public void setTarcestart(String tarcestart) {
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


	public String getKeepAlive() {
		return keepAlive;
	}


	public void setKeepAlive(String keepAlive) {
		this.keepAlive = keepAlive;
	}


	public String getRetryInterval() {
		return retryInterval;
	}


	public void setRetryInterval(String retryInterval) {
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


	public String getQos() {
		return qos;
	}


	public void setQos(String qos) {
		this.qos = qos;
	}


	public String getRetained() {
		return retained;
	}


	public void setRetained(String retained) {
		this.retained = retained;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

}
