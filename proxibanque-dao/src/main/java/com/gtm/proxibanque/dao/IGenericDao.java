package com.gtm.proxibanque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface generique de la couche DAO heritant de la classe JpaRepository<T,
 * Integer> fournie par Spring Data.
 * 
 * La type de l'objet sera a definir dans une nouvelle interface heritant de cette derniere.
 * La cle primaire est une valeur entiere.
 * 
 * Cette interface fournie les methodes suivantes :
 * - boolean exists(int id) : indique si la cle primaire est enregistree en base.
 * - T findOne(int id) : recupere l'enregistrement identifie par la cle primaire 'id'.
 * - List<T> findAll() : recupere tous les enregistrements d'une table.
 * - T save(T input) : permet d'inserer en base et de mettre a jour.
 * - void delete(int id) : supprime un enregistrement d'une table en base.
 * - void delete(T input) : supprime un enregistrement d'une table.
 * - void deleteAll() : supprime tous les enregistrements d'une table en base.
 * - long count() : indique le nombre d'enregistrement d'une table.
}
 * 
 * @param <T> Il s'agit du type d'objet enregistre en base.
 */
@NoRepositoryBean
public interface IGenericDao<T> extends JpaRepository<T, Integer> {

}
