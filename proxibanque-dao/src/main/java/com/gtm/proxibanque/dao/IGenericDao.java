package com.gtm.proxibanque.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericDao<T> extends JpaRepository<T, Integer> {

}
