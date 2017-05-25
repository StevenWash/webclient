package cn.izhiju.register;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;

public class RestJaxRsApplication extends ResourceConfig {
	public RestJaxRsApplication() throws JAXBException {
		packages("cn.izhiju.resource");
		register(JacksonFeatures.class);
		
		Set<Class<?>> resources = new HashSet<>();  
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);  
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);  
        registerClasses(resources); 
	}

}
