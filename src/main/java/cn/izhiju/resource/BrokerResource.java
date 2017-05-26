package cn.izhiju.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.BrokerService;
import cn.izhiju.service.impl.BrokerServiceImpl;
import cn.izhiju.utils.PropertiesUtils;

@Path("/broker")
public class BrokerResource {

	BrokerService brokerService = new BrokerServiceImpl();

	@GET
	@Path("/getDefaultConnProp")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDefaultConnProper() {
		Properties props = PropertiesUtils.getProperties("C:/Users/鑫/git/webclient/src/main/resources/conn.properties");
		
		String ipAddress = props.getProperty("ipAddress");
		String port = props.getProperty("port");
		String tracestart = props.getProperty("tracestart");
		String clientId = props.getProperty("clientId");
		String cleanSession = props.getProperty("cleanSession");
		String keepAlive = props.getProperty("keepAlive");
		String retryInterval = props.getProperty("retryInterval");
		String usePersistence = props.getProperty("usePersistence");
		String directory = props.getProperty("directory");
		String topic = props.getProperty("topic");
		String qos = props.getProperty("qos");
		String retained = props.getProperty("retained");
		String data = props.getProperty("data");
		
		ConnectProperties conn=new ConnectProperties(ipAddress, port, tracestart, clientId, cleanSession, keepAlive, retryInterval,
				usePersistence, directory, topic, qos, retained, data);
		System.out.println("conn:"+conn.toString());
		return conn.toString();
	}

	@GET
	@Path("/connect")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String connect(@QueryParam("ipAddress") String ipAddress, @QueryParam("port") String port,
			@QueryParam("tracestart") String tracestart, @QueryParam("clientId") String clientId,
			@QueryParam("cleanSession") String cleanSession, @QueryParam("keepAlive") String keepAlive,
			@QueryParam("retryInterval") String retryInterval, @QueryParam("usePersistence") String usePersistence,
			@QueryParam("directory") String directory, @QueryParam("topic") String topic, @QueryParam("qos") String qos,
			@QueryParam("retained") String retained, @QueryParam("data") String data) {
		
		
		ConnectProperties connPro = new ConnectProperties(ipAddress, port, tracestart, clientId, cleanSession,
				keepAlive, retryInterval, usePersistence, directory, topic, qos, retained, data);
		System.out.println(connPro.toString());
		if (connPro.getIpAddress().equals("127.0.0.1") && connPro.getPort().equals("1883")) {
			brokerService.connectBroker(connPro);

			return "connect sunccess";
		}
		return "connect failed";
	}

}
