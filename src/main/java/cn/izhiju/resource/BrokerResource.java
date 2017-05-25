package cn.izhiju.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/broker")
public class BrokerResource {
	
	@GET
	@Path("/connect")
	@Consumes("application/json")
	public String connect(@QueryParam("address") String address,@QueryParam("port")String port){
		System.out.println("address:"+address+" prot:"+port);
		if(address.equals("127.0.0.1")&&port.equals("1883"))
			return "connect sunccess";
		return "connect failed";
	}

}
