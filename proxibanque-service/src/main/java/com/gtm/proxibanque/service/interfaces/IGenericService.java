package com.gtm.proxibanque.service.interfaces;

import java.util.List;

public interface IGenericService<T> {
	boolean exists(int id);
	T findOne(int id);
	List<T> findAll();
	T save(T input);
	void delete(int id);
	void deleteAll();
	long count();
}
