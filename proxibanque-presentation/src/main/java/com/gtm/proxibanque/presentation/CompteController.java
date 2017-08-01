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

	@PostConstruct
	public void init() {
		//compte = new Compte();
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

	public ArrayList<String> getListeNumeroCompte() {
		ArrayList<String> listeNumero = (ArrayList<String>) compteService.listerComptes().stream()
				.map(c -> c.getNumeroCompte()).sorted().collect(Collectors.toList());
		return listeNumero;
	}

//	public String ajouterCompteCourant() {
//		compteService.creerCompte(compteCourant);
//		return "gestionCompte";
//	}
//
//	public String ajouterCompteEpargne() {
//		compteService.creerCompte(compteEpargne);
//		return "gestionCompte";
//	}
}
