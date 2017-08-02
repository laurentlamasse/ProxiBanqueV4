package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Conseiller;

/**
 * Interface fournissant les methodes de la couche DAO pour les objets de type Conseiller.
 * Cette interface herite de l'interface IGenericDao<Conseiller>
 */
@Repository("conseillerDao")
public interface IConseillerDao extends IGenericDao<Conseiller>{

	public Conseiller getConseillerByLogin(String login);
}
