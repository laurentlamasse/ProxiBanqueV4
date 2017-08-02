package com.gtm.proxibanque.service.interfaces;

import java.util.List;

import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.Compte;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;

/**
 * Interface fournissant les signatures des methodes pour la gestion des comptes.
 * Cette interface herite de IGenericService<Compte>
 */
public interface ICompteService extends IGenericService<Compte>{

	public void creerCompte(CompteCourant compte, Client client);
	public void creerCompte(CompteEpargne compte, Client client);
	public List<Compte> listerComptes();
	public Compte trouverCompteAvecNumero(String numero);
	
}
