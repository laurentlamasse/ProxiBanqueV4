package com.gtm.proxibanque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.ICompteCourantDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.service.interfaces.ICompteCourant;

@Service("compteCourantService")
public class CompteCourantService extends GenericService<CompteCourant >implements ICompteCourant {

	@Autowired
	@Qualifier("compteCourantDao")
	private ICompteCourantDao dao;
	
	
	@Override
	public IGenericDao<CompteCourant> getDao() {
		return dao;
	}

}
