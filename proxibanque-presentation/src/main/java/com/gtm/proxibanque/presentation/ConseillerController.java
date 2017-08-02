package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.service.interfaces.IConseillerService;

/**
 * Classe Bean qui sera instancie par JSF et sera initialise a partir des
 * informations founies par la page JSF (exemple : les valeurs des champs d'un
 * formulaire). Cette classe permet la gestion du binding c'est a dire le
 * branchement entre l'univers web et l'univers java.
 * 
 * ConseillerController injecte un service utilise pour la gestion des
 * conseillers : - IConseillerService conseillerService
 */
@Controller
@Scope("session")
public class ConseillerController {

	// Propriétés
	private Conseiller conseiller;
	private ArrayList<Conseiller> listeConseiller;

	@Autowired
	private IConseillerService conseillerService;

	// Constructeur
	public ConseillerController() {
	}
	
	/**
	 * Methode d'initialisation appelee automatiquement apres l'instanciation de la
	 * classe ConseillerController.
	 */
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

	/**
	 * Recupere la liste des conseillers de proxibanque
	 * @return Liste des conseillers enregistres en base.
	 */
	public ArrayList<Conseiller> getListeConseiller() {
		return (ArrayList<Conseiller>) conseillerService.listerConseillers();
	}

	public void setListeConseiller(ArrayList<Conseiller> listeConseiller) {
		this.listeConseiller = listeConseiller;
	}

	/**
	 * Cree un conseiller dans la base de donnees
	 * @return
	 */
	public String ajouterConseiller() {
		conseillerService.initialiserListe(conseiller);
		conseillerService.creerConseiller(conseiller);
		return "gestion";
	}

}
