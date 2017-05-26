package cn.izhiju.module;

import com.google.inject.Binder;
import com.google.inject.Module;

import cn.izhiju.service.BrokerService;
import cn.izhiju.service.impl.BrokerServiceImpl;

public class BrokerModule implements Module{

	@Override
	public void configure(Binder binder) {
		binder.bind(BrokerService.class).to(BrokerServiceImpl.class);
	}

}
