package com.gtm.proxibanque.dao;

import org.springframework.stereotype.Repository;

import com.gtm.proxibanque.domaine.Gerant;

@Repository("gerantDao")
public interface IGerantDao extends IGenericDao<Gerant>{

}
