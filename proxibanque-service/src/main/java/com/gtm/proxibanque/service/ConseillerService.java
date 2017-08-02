package com.gtm.proxibanque.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.IConseillerDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.service.interfaces.IConseillerService;

/**
 * Classe implementant l'interface IConseillerService et heritant de GenericService<Conseiller>
 * Cette classe fournit les methodes du service lie a la gestion des comptes.
 *
 */
@Service("ConseillerService")
public class ConseillerService extends GenericService<Conseiller> implements IConseillerService {

	@Autowired
	@Qualifier("conseillerDao")
	private IConseillerDao dao;

	@Override
	public IGenericDao<Conseiller> getDao() {
		return dao;
	}

	public Conseiller trouverConseiller(int id) {
		return dao.findOne(id);
	}

	public void modifierConseiller(Conseiller conseiller) {
		dao.save(conseiller);
	}

	/**
	 * Recupere via la DAO une List contenant tous les conseillers enregistres en
	 * base.
	 *
	 * @return List<Conseiller> - List contenant tous les conseillers
	 */
	public List<Conseiller> listerConseillers() {
		return dao.findAll();
	}

	/**
	 * Insere via la DAO un Conseiller en base.
	 *
	 * @param conseiller
	 *            - objet de type Conseiller a inserer en base
	 */
	public void creerConseiller(Conseiller conseiller) {
		dao.save(conseiller);
	}

	public void initialiserListe(Conseiller conseiller) {
		conseiller.setListeClients(new HashSet<Client>());
	}
	
	/**
	 * Recupere le conseiller a partir de son login
	 */
	public Conseiller trouverConseillerParLogin(String login) {
		return dao.getConseillerByLogin(login);
	}

}
