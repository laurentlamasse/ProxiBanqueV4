package com.gtm.proxibanque.service.interfaces;

import java.util.List;

import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.CompteCourant;

public interface IClientService extends IGenericService<Client>{

	void deleteCompteCourant(Client client);
	void deleteCompteEpargne(Client client);
	public List<Client> listerClients();
	public Client creerClient(Client client);
}
