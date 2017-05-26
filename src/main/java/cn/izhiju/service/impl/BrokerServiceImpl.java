package cn.izhiju.service.impl;

import com.google.inject.Singleton;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.BrokerService;

@Singleton
public class BrokerServiceImpl implements BrokerService {

	@Override
	public boolean connectBroker(ConnectProperties connPro) {
		System.out.println("  connPro:"+connPro.toString());
		return false;
	}

}
