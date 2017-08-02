package com.gtm.proxibanque.presentation;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Compte;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;
import com.gtm.proxibanque.service.interfaces.ICompteService;

/**
 * Classe Bean qui sera instancie par JSF et sera initialise a partir des
 * informations founies par la page JSF (exemple : les valeurs des champs d'un
 * formulaire). Cette classe permet la gestion du binding c'est a dire le
 * branchement entre l'univers web et l'univers java.
 * 
 * CompteController injecte un service utilise pour la gestion des comptes : -
 * ICompteService compteService
 */
@Controller
@Scope("session")
public class CompteController {

	// Propriétés
	private Compte compte;
	private CompteCourant compteCourant;
	private CompteEpargne compteEpargne;
	private ArrayList<Compte> listeCompte;

	@Autowired
	private ICompteService compteService;

	// Constructeur
	public CompteController() {
	}

	/**
	 * Methode d'initialisation appelee automatiquement apres l'instanciation de la
	 * classe CompteController.
	 */
	@PostConstruct
	public void init() {
		// compte = new Compte();
		compteCourant = new CompteCourant();
		compteEpargne = new CompteEpargne();
		listeCompte = new ArrayList<Compte>();
		listeCompte = (ArrayList<Compte>) compteService.listerComptes();
	}

	// Getters & Setters
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte Compte) {
		this.compte = Compte;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

	public CompteEpargne getCompteEpargne() {
		return compteEpargne;
	}

	public void setCompteEpargne(CompteEpargne compteEpargne) {
		this.compteEpargne = compteEpargne;
	}

	public ArrayList<Compte> getListeCompte() {
		return (ArrayList<Compte>) compteService.listerComptes();
	}

	public void setListeCompte(ArrayList<Compte> listeCompte) {
		this.listeCompte = listeCompte;
	}

	/**
	 * Permet de recuperer la liste des numeros de comptes enregistre dans la base de donnees
	 * @return Liste des numero de comptes (courant et epargne)
	 */
	public ArrayList<String> getListeNumeroCompte() {
		ArrayList<String> listeNumero = (ArrayList<String>) compteService.listerComptes().stream()
				.map(c -> c.getNumeroCompte()).sorted().collect(Collectors.toList());
		return listeNumero;
	}
}
