package cn.izhiju.service;

import com.google.inject.ImplementedBy;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.impl.BrokerServiceImpl;

@ImplementedBy(BrokerServiceImpl.class)
public interface BrokerService {
	/**
	 * 进行连接broker
	 * @param connUrl 连接的url（ip+port）
	 * @param connPro
	 * @return 是否连接成功
	 */
	public boolean connectBroker(String connUrl,ConnectProperties connPro);

}
