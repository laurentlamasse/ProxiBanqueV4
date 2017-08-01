package com.gtm.proxibanque.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.proxibanque.domaine.Compte;

@Repository("compteDao")
public interface ICompteDao extends IGenericDao<Compte>{
}
