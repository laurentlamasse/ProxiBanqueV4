package com.gtm.proxibanque.service.interfaces;

import java.util.List;

import com.gtm.proxibanque.domaine.Conseiller;

public interface IConseillerService extends IGenericService<Conseiller>{

	public Conseiller trouverConseiller(int id);
	public void modifierConseiller(Conseiller conseiller);
	public List<Conseiller> listerConseillers();
	public void creerConseiller(Conseiller conseiller);
	public void initialiserListe(Conseiller conseiller);
	public Conseiller trouverConseillerParLogin(String login);
	public void updateConseiller(Conseiller conseiller);
}
