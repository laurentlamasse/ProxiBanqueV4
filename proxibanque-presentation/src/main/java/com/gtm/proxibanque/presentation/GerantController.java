package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.domaine.Gerant;
import com.gtm.proxibanque.service.interfaces.IGerantService;

/**
 * Classe Bean qui sera instancie par JSF et sera initialise a partir des
 * informations founies par la page JSF (exemple : les valeurs des champs d'un
 * formulaire). Cette classe permet la gestion du binding c'est a dire le
 * branchement entre l'univers web et l'univers java.
 * 
 * GerantController injecte un service utilise pour la gestion des gerants : -
 * IGerantService gerantService
 */
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

	/**
	 * Methode d'initialisation appelee automatiquement apres l'instanciation de la
	 * classe GerantController.
	 */
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

	/**
	 * Enregistre un gerant dans la base de donnee
	 * @return
	 */
	public String ajouterGerant() {
		gerantService.initialiserListe(gerant);
		gerantService.creerGerant(gerant);
		return "gestion";
	}
}
