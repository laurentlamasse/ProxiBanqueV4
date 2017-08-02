package com.gtm.proxibanque.service.interfaces;

import java.util.List;

/**
 * Interface generique fournissant l'ensemble des methodes par defaut fournies
 * par la couche DAO.
 * 
 * @param <T>
 *            La couche DAO nous fournit des methodes CRUD (create, read,
 *            update, delete) sur le type d'objet T.
 */
public interface IGenericService<T> {
	boolean exists(int id);

	T findOne(int id);

	List<T> findAll();

	T save(T input);

	void delete(int id);

	void delete(T input);

	void deleteAll();

	long count();
}
