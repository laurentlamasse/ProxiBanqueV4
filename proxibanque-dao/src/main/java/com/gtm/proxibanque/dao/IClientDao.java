package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Client;

@Repository("clientDao")
public interface IClientDao extends IGenericDao<Client> {
	
}
