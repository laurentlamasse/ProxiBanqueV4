package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Gerant;

/**
 * Interface fournissant les methodes de la couche DAO pour les objets de type Gerant.
 * Cette interface herite de l'interface IGenericDao<Gerant>
 */
@Repository("gerantDao")
public interface IGerantDao extends IGenericDao<Gerant>{

}
