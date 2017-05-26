package cn.izhiju.service.impl;

import com.google.inject.Singleton;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.BrokerService;

@Singleton
public class BrokerServiceImpl implements BrokerService {

	@Override
	public boolean connectBroker(String connUrl, ConnectProperties connPro) {
		System.out.println("connUrl:"+connUrl+"  connPro:"+connPro.toString());
		return false;
	}

}
