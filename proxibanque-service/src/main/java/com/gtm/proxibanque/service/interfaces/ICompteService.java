package com.gtm.proxibanque.service.interfaces;

import java.util.List;

import com.gtm.proxibanque.domaine.Compte;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;

public interface ICompteService extends IGenericService<Compte>{

	public void creerCompte(CompteCourant compte);
	public void creerCompte(CompteEpargne compte);
	public List<Compte> listerComptes();
	public Compte trouverCompteAvecNumero(String numero);
	
}
