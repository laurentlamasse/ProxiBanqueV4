package com.gtm.proxibanque.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Client;

/**
 * Interface fournissant les methodes de la couche DAO pour les objets de type Client.
 * Cette interface herite de l'interface IGenericDao<Client>
 */
@Repository("clientDao")
public interface IClientDao extends IGenericDao<Client> {
}
