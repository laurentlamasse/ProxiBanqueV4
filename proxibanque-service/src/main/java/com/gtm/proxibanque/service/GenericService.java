package com.gtm.proxibanque.service;

import java.util.List;

import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.service.interfaces.IGenericService;

/**
 * Implementation de l'interface IGenericService<T> Cette classe abstraite
 * fournie les methodes du CRUD (create, read, update, delete) pour un type de
 * donnee generique.
 * 
 * @param <T>
 * 
 *            La couche DAO nous fournit des methodes CRUD (create, read,
 *            update, delete) sur le type d'objet T.
 */
public abstract class GenericService<T> implements IGenericService<T> {

	public abstract IGenericDao<T> getDao();

	/**
	 * Indique si un objet d'identifiant "id" est enregistree dans la base de
	 * donnees.
	 * 
	 * @param id
	 *            Identifiant (cle primaire) d'un enregistrement correspondant a un
	 *            objet de type T
	 * @return Booleen indiquant si l'objet existe ou non dans la table de la base
	 *         de donnee
	 */
	public boolean exists(int id) {
		return getDao().exists(id);
	}

	/**
	 * Retourne l'objet de type T ayant pour identifiant (cle primaire) la valeur du
	 * parametre "id"
	 * 
	 * @param id
	 *            Identifiant (cle primaire) d'un enregistrement correspondant a un
	 *            objet de type T
	 * @return l'objet de type T recherche
	 */
	public T findOne(int id) {
		return getDao().findOne(id);
	}

	/**
	 * Retoure la liste des objet de type T
	 * 
	 * @return La liste des objets de type T enregistre en base.
	 */
	public List<T> findAll() {
		return getDao().findAll();
	}

	/**
	 * Insert ou met a jour l'objet "input" de type T dans la base de donnees. Cette
	 * methode permet de rendre un objet transcient ou detache en un objet
	 * persistant.
	 * 
	 * @param input
	 *            Objet a supprimer
	 * 
	 * @return L'objet persistant (cree ou mis a jour dans la table de la base de
	 *         donnees)
	 */
	public T save(T input) {
		return getDao().save(input);
	}

	/**
	 * Supprime l'enregistrement d'une table identifie par la cle primaire "id".
	 * L'objet n'est plus persistant.
	 * 
	 * @param id
	 *            Identifiant (cle primaire) d'un enregistrement correspondant a un
	 *            objet de type T
	 */
	public void delete(int id) {
		getDao().delete(id);
	}

	/**
	 * Supprime un objet enregistre en base. Le parametre input doit etre persitant.
	 * Une fois la methode executee, l'objet ne sera plus persitant en base.
	 * 
	 * @param input
	 *            Objet persistant de type Y
	 */
	public void delete(T input) {
		getDao().delete(input);
	}

	/**
	 * Permet de supprimer l'ensemble des enregistrements de la table correspondant
	 * aux objets de type T
	 */
	public void deleteAll() {
		getDao().deleteAll();
	}

	/**
	 * Indique le nombre d'enregistrements dans la table correspondant aux objets de
	 * type T
	 */
	public long count() {
		return getDao().count();
	}
}
