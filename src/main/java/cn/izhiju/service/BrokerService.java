package cn.izhiju.service;

import com.google.inject.ImplementedBy;

import cn.izhiju.entity.ConnectProperties;
import cn.izhiju.service.impl.BrokerServiceImpl;

@ImplementedBy(BrokerServiceImpl.class)
public interface BrokerService {
	/**
	 * ��������broker
	 * @param connUrl ���ӵ�url��ip+port��
	 * @param connPro
	 * @return �Ƿ����ӳɹ�
	 */
	public boolean connectBroker(ConnectProperties connPro);

}
