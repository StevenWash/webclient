package cn.izhiju.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import cn.izhiju.module.BrokerModule;

public class IzhijuGuiceServletContextListener extends GuiceServletContextListener{

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new BrokerModule());
		//如果绑定多个module，需要像下面这样就可以了
		//return Guice.createInjector(new UserModule(),new UserModule());
	}

}
