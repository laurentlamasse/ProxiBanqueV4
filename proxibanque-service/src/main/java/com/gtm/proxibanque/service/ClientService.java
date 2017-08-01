package com.gtm.proxibanque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.IClientDao;
import com.gtm.proxibanque.dao.ICompteDao;
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

	@Autowired
	@Qualifier("compteDao")
	private ICompteDao compteDao;

	@Override
	public IGenericDao<Client> getDao() {
		return dao;
	}

	@Override
	public Client save(Client input) {
		CompteCourant compteCourant = input.getCompteCourant();
		if (compteCourant != null)
			compteCourant.setProprietaire(input);
		CompteEpargne compteEpargne = input.getCompteEpargne();
		if (compteEpargne != null)
			compteEpargne.setProprietaire(input);
		return super.save(input);
	}

	public void deleteCompteCourant(Client client) {
		if (client != null) {
			CompteCourant compte = client.getCompteCourant();
			client.setCompteCourant(null);
			dao.save(client);
			if (compte != null)
				compteDao.delete(compte);
		}
	}

	public void deleteCompteEpargne(Client client) {
		if (client != null) {
			CompteEpargne compte = client.getCompteEpargne();
			client.setCompteEpargne(null);
			dao.save(client);
			if (compte != null)
				compteDao.delete(compte);
		}

	}

	public List<Client> listerClients() {
		return dao.findAll();
	}

	/**
	 * Insere via la DAO un client en base.
	 *
	 * @param client
	 *            - objet de type client a inserer en base
	 */
	public Client creerClient(Client client) {
		return dao.save(client);
	}

}
