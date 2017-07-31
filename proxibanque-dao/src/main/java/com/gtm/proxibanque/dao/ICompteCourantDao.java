package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.CompteCourant;

@Repository("compteCourantDao")
public interface ICompteCourantDao extends IGenericDao<CompteCourant> {

}
