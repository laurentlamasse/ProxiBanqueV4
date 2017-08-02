package com.gtm.proxibanque.service.interfaces;

import java.util.List;

import com.gtm.proxibanque.domaine.Client;

/**
 * Interface pour les services lies au client.
 * Cette interface herite de IGenericService<Client>
 *
 */
public interface IClientService extends IGenericService<Client>{

	void deleteCompteCourant(Client client);
	void deleteCompteEpargne(Client client);
	public List<Client> listerClients();
	public Client creerClient(Client client);
}
