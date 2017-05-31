package cn.izhiju.service;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import com.google.inject.ImplementedBy;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.impl.BrokerServiceImpl;

@ImplementedBy(BrokerServiceImpl.class)
public interface BrokerService {
	
	/**
	 * 进行连接操作，传入的参数是所有的连接属性值，包括ip和port
	 * @param connPro 包含所有的连接属性
	 * @return 是否连接成功
	 * @throws MqttException 
	 * @throws MqttSecurityException 
	 */
	public boolean connectBroker(ConnectProperties connPro) throws MqttSecurityException, MqttException;
	
	/**
	 * 订阅某一个主题
	 * @param topic
	 * @param connPro
	 * @param method 判断是进行订阅还是取消订阅
	 * @return 一个有关连接的字符串
	 * @throws MqttException 
	 */
	public String subscribeTopic(String topic ,ConnectProperties connPro,String method) throws MqttException;

}
