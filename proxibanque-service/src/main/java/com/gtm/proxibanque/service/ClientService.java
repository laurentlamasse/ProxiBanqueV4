package com.gtm.proxibanque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.IClientDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;
import com.gtm.proxibanque.service.interfaces.IClientService;

@Service("clientService")
public class ClientService extends GenericService<Client> implements IClientService {

	@Autowired
	@Qualifier("clientDao")
	private IClientDao dao;
	
	@Override
	public IGenericDao<Client> getDao() {
		return dao;
	}
	
	@Override
	public Client save(Client input) {
		CompteCourant compteCourant = input.getCompteCourant();
		if(compteCourant != null)
			compteCourant.setProprietaire(input);
		CompteEpargne compteEpargne = input.getCompteEpargne();
		if(compteEpargne != null)
			compteEpargne.setProprietaire(input);
		return super.save(input);
	}
}
