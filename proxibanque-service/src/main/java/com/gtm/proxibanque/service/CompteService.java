package com.gtm.proxibanque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.ICompteDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.domaine.Compte;
import com.gtm.proxibanque.service.interfaces.ICompteService;

@Service("compteService")
public class CompteService extends GenericService<Compte> implements ICompteService {

	@Autowired
	@Qualifier("compteDao")
	private ICompteDao dao;
	
	@Override
	public IGenericDao<Compte> getDao() {
		return dao;
	}
}
