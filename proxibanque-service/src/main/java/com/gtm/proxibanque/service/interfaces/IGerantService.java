package com.gtm.proxibanque.service.interfaces;

import com.gtm.proxibanque.domaine.Gerant;

public interface IGerantService {

	
	public void initialiserListe(Gerant gerant);
	public void modifierGerant(Gerant gerant);
	public void creerGerant(Gerant gerant);
}
