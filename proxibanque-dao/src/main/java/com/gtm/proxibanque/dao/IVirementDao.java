package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Virement;

/**
 * Interface fournissant les methodes de la couche DAO pour les objets de type Virement.
 * Cette interface herite de l'interface IGenericDao<Virement>
 */
@Repository("virementDao")
public interface IVirementDao extends IGenericDao<Virement>{

}
