package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Conseiller;

@Repository("conseillerDao")
public interface IConseillerDao extends IGenericDao<Conseiller>{

}
