package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.domaine.Gerant;
import com.gtm.proxibanque.service.interfaces.IConseillerService;
import com.gtm.proxibanque.service.interfaces.IGerantService;

@Controller
@Scope("session")
public class EmployeController {

	// Propriétés
	private Gerant gerant;
	private Conseiller conseiller;
	private ArrayList<Conseiller> listeConseiller;

	@Autowired
	private IConseillerService conseillerService;

	@Autowired
	private IGerantService gerantService;

	public EmployeController() {

	}

	@PostConstruct
	public void init() {
		gerant = new Gerant();
		conseiller = new Conseiller();
		listeConseiller = new ArrayList<Conseiller>();
		listeConseiller = (ArrayList<Conseiller>) conseillerService.listerConseillers();
	}

	public String ajouterConseiller() {
		conseillerService.initialiserListe(conseiller);
		conseillerService.creerConseiller(conseiller);
		return "listeConseiller";
	}

	public String ajouterGerant() {
		gerantService.initialiserListe(gerant);
		gerantService.creerGerant(gerant);
		return "gestion";
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public ArrayList<Conseiller> getListeConseiller() {
		return listeConseiller;
	}

	public void setListeConseiller(ArrayList<Conseiller> listeConseiller) {
		this.listeConseiller = listeConseiller;
	}

	public IConseillerService getConseillerService() {
		return conseillerService;
	}

	public void setConseillerService(IConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

	public IGerantService getGerantService() {
		return gerantService;
	}

	public void setGerantService(IGerantService gerantService) {
		this.gerantService = gerantService;
	}
}
