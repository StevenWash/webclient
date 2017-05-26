package cn.izhiju.resource;

import java.util.List;

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
	@Produces(MediaType.APPLICATION_JSON)
	public ConnectProperties getDefaultConnProper() {
		List<String> props = PropertiesUtils.getValues("classpath:default-conn.properties");
		String ipAddress = props.get(0);
		String port = props.get(1);
		int tarcestart = Integer.parseInt(props.get(2));// 0表示stop ,1表示start
		String clientId = props.get(3);
		String cleanSession = props.get(4);// on表示没选中， off表示选中
		int keepAlive = Integer.parseInt(props.get(5));
		int retryInterval = Integer.parseInt(props.get(6));
		String usePersistence = props.get(7);// 0表示没选中， 1表示选中
		String directory = props.get(8);
		String topic = props.get(9);
		int qos = Integer.parseInt(props.get(10));// 取值为0 ，1 ，2
		int retained = Integer.parseInt(props.get(11));// 0表示没选中， 1表示选中
		String data = props.get(12);
		System.out.println("test");
		return new ConnectProperties(ipAddress, port, tarcestart, clientId, cleanSession, keepAlive, retryInterval,
				usePersistence, directory, topic, qos, retained, data);
	}

	@GET
	@Path("/connect")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String connect(@QueryParam("ipAddress") String ipAddress, @QueryParam("port") String port,
			@QueryParam("tarcestart") int tarcestart, @QueryParam("clientId") String clientId,
			@QueryParam("cleanSession") String cleanSession, @QueryParam("keepAlive") int keepAlive,
			@QueryParam("retryInterval") int retryInterval, @QueryParam("usePersistence") String usePersistence,
			@QueryParam("directory") String directory, @QueryParam("topic") String topic, @QueryParam("qos") int qos,
			@QueryParam("retained") int retained, @QueryParam("data") String data) {

		ConnectProperties connPro = new ConnectProperties(ipAddress, port, tarcestart, clientId, cleanSession,
				keepAlive, retryInterval, usePersistence, directory, topic, qos, retained, data);
		System.out.println(connPro.toString());
		if (connPro.getIpAddress().equals("127.0.0.1") && connPro.getPort().equals("1883")) {
			// brokerService.connectBroker(, connPro);

			return "connect sunccess";
		}
		return "connect failed";
	}

}
