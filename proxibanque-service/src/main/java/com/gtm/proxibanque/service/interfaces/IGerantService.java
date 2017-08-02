package com.gtm.proxibanque.service.interfaces;

import com.gtm.proxibanque.domaine.Gerant;

/**
 * Interface fournissant les signatures des methodes pour la gestion des gerants.
 */
public interface IGerantService {

	
	public void initialiserListe(Gerant gerant);
	public void modifierGerant(Gerant gerant);
	public void creerGerant(Gerant gerant);
}
