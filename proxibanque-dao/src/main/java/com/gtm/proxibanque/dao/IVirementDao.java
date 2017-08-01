package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Virement;

@Repository("virementDao")
public interface IVirementDao extends IGenericDao<Virement>{

}
