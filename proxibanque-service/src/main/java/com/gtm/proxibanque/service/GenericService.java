package com.gtm.proxibanque.service;

import java.util.List;

import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.service.interfaces.IGenericService;

public abstract class GenericService<T> implements IGenericService<T> {

	public abstract IGenericDao<T> getDao();

	public boolean exists(int id) {
		return getDao().exists(id);
	}

	public T findOne(int id) {
		return getDao().findOne(id);
	}

	public List<T> findAll() {
		return getDao().findAll();
	}

	public T save(T input) {
		return getDao().save(input);
	}
	
	public void delete(int id) {
		getDao().delete(id);
	}
	
	public void delete(T input)
	{
		getDao().delete(input);
	}
	
	public void deleteAll() {
		getDao().deleteAll();
	}

	public long count() {
		return getDao().count();
	}
}
