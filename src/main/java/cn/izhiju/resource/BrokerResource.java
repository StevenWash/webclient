package cn.izhiju.resource;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.BrokerService;
import cn.izhiju.service.impl.BrokerServiceImpl;
import cn.izhiju.utils.PropertiesUtils;

@Path("/broker")
public class BrokerResource{
	private BrokerService brokerService = new BrokerServiceImpl();
	@Context HttpServletRequest request;  
	@Context HttpSession session;
	private ConnectProperties connPro;

	@GET
	@Path("/getDefaultConnProp")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDefaultConnProper() {
		Properties props = PropertiesUtils.getProperties("G:/huaxin/documents/work file/git/webclient/webclient/src/main/resources/conn.properties");
		
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
			@QueryParam("retained") String retained, @QueryParam("data") String data) throws MqttSecurityException, MqttException {
		connPro = new ConnectProperties(ipAddress, port, tracestart, clientId, cleanSession,
				keepAlive, retryInterval, usePersistence, directory, topic, qos, retained, data);
		System.out.println(connPro.toString());
		if (brokerService.connectBroker(connPro)) {
			session=request.getSession();
			session.setAttribute("connPro",connPro);
			return "connect success";
		}
		return "connect failed";
	}

	@GET
	@Path("/subscribe")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String subscribe(@QueryParam("topic") String topic) throws MqttException{
		session=request.getSession();
		connPro=(ConnectProperties) session.getAttribute("connPro");
		String msg=brokerService.subscribeTopic(topic, connPro,"subscribe");
		return msg;
	}
	
	@GET
	@Path("/publish")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String publish(@QueryParam("topic") String topic,@QueryParam("message") String message){
		System.out.println("topic:"+topic+"  message:"+message);
		session=request.getSession();
		connPro=(ConnectProperties) session.getAttribute("connPro");
		int qos=Integer.parseInt((connPro.getQos()==""?"0":connPro.getQos()));
		boolean retained=connPro.getRetained().equals("1");
		System.out.println("qos:"+qos+" retained:"+retained);
		String msg=brokerService.publishTopic(topic, qos, retained, message);
		return msg;
	}
	
	@GET
	@Path("/disconnect")
	@Produces(MediaType.TEXT_PLAIN)
	public String disconnect(){
		return brokerService.disconnectBroker();
	}
}

