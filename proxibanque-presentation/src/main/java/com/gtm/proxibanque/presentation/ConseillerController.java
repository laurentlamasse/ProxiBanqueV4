package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Conseiller;

@Controller
@Scope("session")
public class ConseillerController {

	// Propriétés
	private Conseiller conseiller;
	private ArrayList<Conseiller> listeConseiller;

	@Autowired
	private ConseillerService conseillerService;

	// Constructeur
	public ConseillerController() {
		}

	@PostConstruct
	public void init() {
		conseiller = new Conseiller();
		listeConseiller = new ArrayList<Conseiller>();
		listeConseiller = (ArrayList<Conseiller>) conseillerService.listerConseillers();
	}

	// Getters & Setters
	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public ArrayList<Conseiller> getListeConseiller() {
		return (ArrayList<Conseiller>) conseillerService.listerConseillers();
	}

	public void setListeConseiller(ArrayList<Conseiller> listeConseiller) {
		this.listeConseiller = listeConseiller;
	}

	public String ajouterConseiller() {
		conseillerService.initialiserListe(conseiller);
		conseillerService.creerConseiller(conseiller);
		return "gestion";
	}

	// public String reinitialiserListeConseiller() {
	// conseillerService.reinitialiserListe();
	// return "primefaces";
	// }
}
