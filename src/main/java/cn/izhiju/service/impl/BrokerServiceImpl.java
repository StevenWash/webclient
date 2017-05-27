package cn.izhiju.service.impl;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.BrokerService;

public class BrokerServiceImpl implements BrokerService, MqttCallback {
	private final static int RETRYINTERVAL=1000;
	
	private MqttClient mqtt;
	private MqttConnectOptions opts;
	private static boolean connected;
	private Object connLostWait=new Object();

	
	public boolean connectBroker(ConnectProperties connPro) throws MqttSecurityException, MqttException {
		// Connect to the broker
		// If we have a MqttClient object and the new ip address
		// or port number is not equal to the previous, or the persistence flag
		// changes between
		// off and on then we need a new object.
		String connStr = "tcp://" + connPro.getIpAddress() + ":" + connPro.getPort();
		if ((mqtt != null) && (!connStr.equals(
				mqtt.getServerURI()) )) {
			mqtt = null;
		}
		if (mqtt == null) {
			MqttClientPersistence persistence = null;
			if (connPro.getUsePersistence().equals("1")) {
				persistence = new MqttDefaultFilePersistence(connPro.getDirectory());
			}
			mqtt = new MqttClient(connStr, connPro.getClientId(), persistence);
			mqtt.setCallback(this);
			// Carry the trace setting over to the new MqttClient object
			if (connPro.getTarcestart().equals("1")) {
				System.out.println("start the trace.......");
				// 启动trace
				startTrace();
			}
		}
		opts = new MqttConnectOptions();
		opts.setCleanSession(connPro.getCleanSession().equals("1"));
		opts.setKeepAliveInterval(Integer.parseInt(connPro.getKeepAlive() == "" ? "0" : connPro.getKeepAlive()));
		opts.setUserName("kapua-sys");
		opts.setPassword("kapua-password".toCharArray());

		//System.out.println("test1:"+opts.toString());
		if (connPro.getRetained().equals("1")) {
			opts.setWill(mqtt.getTopic("topic"), connPro.getData().getBytes(),
					Integer.parseInt(connPro.getQos()), connPro.getRetained().equals("1"));
		}
		//System.out.println("test2:"+opts.toString());
		System.out.println("connPro:" + connPro.toString());
		try {
			mqtt.connect(opts);
			connected=true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();;
			return false;
		}
	}
	
	public String subscribeTopic(String topic ,ConnectProperties connPro,String method){
		
		if ( connected ) {
    		try {
       		    String[] theseTopics = new String[1];
    		    int[] theseQoS = new int[1];
    		    theseTopics[0] = topic;
    		    theseQoS[0] = Integer.parseInt((connPro.getQos()==""?"0":connPro.getQos()));

        	    synchronized(this) { // Grab the log synchronisation lock
                  if ( method.equals("subscribe") ) {
                	  mqtt.subscribe( theseTopics, theseQoS );
            	      System.out.println( "  --> SUBSCRIBE,        TOPIC:" + topic + ", Requested QoS:" + connPro.getQos() );
                  } else {
                	  mqtt.unsubscribe( theseTopics );
                	  System.out.println( "  --> UNSUBSCRIBE,      TOPIC:" + topic );
                  }	  
        	    }
        	    return "subscribe";
    		} catch ( Exception ex ) {
    			System.out.println("MQTT subscription exception caught !");
        		/*setTitleText( "MQTT subscription exception caught !" );
        		JOptionPane.showMessageDialog( frame, ex.getMessage(), "MQTT Subscription Exception", JOptionPane.ERROR_MESSAGE );*/
    		}	
    	} else {
    		System.out.println("MQTT client not connected !");
    		return "unconnected";
    		//setTitleText( "MQTT client not connected !" );
    	}
		return "error";
	}
	
	/**
     * This method calls the MQTT startTrace method to produce trace of the protocol flows
     * @throws MqttException on error
     */    
    public void startTrace() throws MqttException {
    	System.out.println("starttrace....");
    }

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("connectionLost....");
		int rc = -1;
		System.out.println( "Connection Lost!....Reconnecting" );
   	    synchronized(this) { // Grab the log synchronisation lock
   	    	System.out.println( "MQTT Connection Lost!....Reconnecting to " + mqtt.getServerURI() );
   	    }	
    	try {
    		// While we have failed to reconnect and disconnect hasn't
    		// been called by another thread retry to connect
    		while ( (rc == -1) && connected ) {
           		try {
           			synchronized(connLostWait) {
               			connLostWait.wait(RETRYINTERVAL);
           			}
           		} catch (InterruptedException iex) {
       	    		// Don't care if we are interrupted
       		    }		
        	    synchronized(this) { // Grab the log synchronisation lock
        	    	if ( connected ) {
        	    		System.out.println( "MQTT reconnecting......" );
        	    		System.out.println("reconnect....");
						//connectBroker(connPro);
						rc = 0;		
        	    		if ( rc == -1 ) {
        	    			System.out.println( "failed" );
        	    		} else {
        	    			System.out.println( "success !" );
        	    		}		
        	    	}
        	    }
    		}	
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		//disconnect();
    	} finally {
    		// Set the flashing off whatever happens
    	}	
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("deliveryComplete....");
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		System.out.println("messageArrived....");
	}

}
