package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.domaine.Gerant;
import com.gtm.proxibanque.service.interfaces.IGerantService;

@Controller
@Scope("session")
public class GerantController {

	// Propriétés
	private Gerant gerant;
	private ArrayList<Conseiller> listeConseiller;

	@Autowired
	private IGerantService gerantService;

	// Constructeur
	public GerantController() {
		}

	@PostConstruct
	public void init() {
		gerant = new Gerant();
		listeConseiller = new ArrayList<Conseiller>();
	}

	// Getters & Setters
	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

//	public void setListeGerant(ArrayList<Gerant> listeGerant) {
//		this.listeConseiller = listeConseiller;
//	}

	public String ajouterGerant() {
		gerantService.initialiserListe(gerant);
		gerantService.creerGerant(gerant);
		return "gestion";
	}
}
