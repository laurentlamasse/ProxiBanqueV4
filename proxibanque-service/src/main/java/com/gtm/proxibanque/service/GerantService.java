package com.gtm.proxibanque.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.IConseillerDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.dao.IGerantDao;
import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.domaine.Gerant;
import com.gtm.proxibanque.service.interfaces.IGerantService;

/**
 * Classe implementant l'interface IGerantService et heritant de GenericService<Gerant>
 * Cette classe fournit les methodes du service lie a la gestion des gerants.
 *
 */
@Service("GerantService")
public class GerantService extends GenericService<Gerant> implements IGerantService {

	@Autowired
	@Qualifier("gerantDao")
	private IGerantDao dao;

	@Override
	public IGenericDao<Gerant> getDao() {
		return dao;
	}

	public void initialiserListe(Gerant gerant) {
		gerant.setListeConseillers(new HashSet<Conseiller>());
	}

	public void modifierGerant(Gerant gerant) {
		dao.save(gerant);
	}

	public void creerGerant(Gerant gerant) {
		dao.save(gerant);
	}

}
