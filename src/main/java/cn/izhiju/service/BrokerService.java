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
	 * 断开连接的操作
	 * @return 有关操作结果的提示信息
	 */
	public String disconnectBroker();
	
	/**
	 * 订阅某一个主题
	 * @param topic
	 * @param connPro
	 * @param method 判断是进行订阅还是取消订阅
	 * @return 一个有关连接的字符串
	 * @throws MqttException 
	 */
	public String subscribeTopic(String topic ,ConnectProperties connPro,String method) throws MqttException;
	
	/**
	 * 发布消息到某个主题
	 * @param topic  消息发布的主题
	 * @param connPro  用户的连接信息
	 * @param message  消息的内容
	 * @return  一个有关消息发送是否成功的字符串
	 */
	public String publishTopic(String topic, int qos, boolean retained,String message);

}
