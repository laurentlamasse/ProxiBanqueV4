package com.gtm.proxibanque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.IConseillerDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.service.interfaces.IConseillerService;

@Service("ConseillerService")
public class ConseillerService extends GenericService<Conseiller> implements IConseillerService {

	@Autowired
	@Qualifier("conseillerDao")
	private IConseillerDao dao;
	
	@Override
	public IGenericDao<Conseiller> getDao() {
		return dao;
	}


}
