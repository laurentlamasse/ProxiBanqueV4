package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Compte;
import com.gtm.proxibanque.domaine.Virement;
import com.gtm.proxibanque.service.interfaces.ICompteService;
import com.gtm.proxibanque.service.interfaces.IVirementService;

@Controller
@Scope("session")
public class VirementController {

	// Propriétés
	private Virement virement;
	private ArrayList<Virement> listeVirement;
	private String numeroCompteDebiteur = "pas de numéro";
	private String numeroCompteCrediteur = "pas de numéro";
	private double montant;

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	@Autowired
	private ICompteService compteService;
	@Autowired
	private IVirementService virementService;

	// Constructeur
	public VirementController() {
		}

	@PostConstruct
	public void init() {
		virement = new Virement();
		listeVirement = new ArrayList<Virement>();
		listeVirement = (ArrayList<Virement>) virementService.listerVirements();
	}

	// Getters & Setters
	public Virement getVirement() {
		return virement;
	}

	public void setVirement(Virement Virement) {
		this.virement = Virement;
	}

	public ArrayList<Virement> getListeVirement() {
		return (ArrayList<Virement>) virementService.listerVirements();
	}

	public void setListeVirement(ArrayList<Virement> listeVirement) {
		this.listeVirement = listeVirement;
	}

	public String creerVirement() {
		Compte compteDebiteur = compteService.trouverCompteAvecNumero(numeroCompteDebiteur);
		Compte compteCrediteur = compteService.trouverCompteAvecNumero(numeroCompteCrediteur);
		Virement virement = new Virement(compteDebiteur, compteCrediteur, montant);
		virementService.createVirement(virement);
		return "gestionClientBS";
	}

	public String choixCompteDebite(String numeroCompteDebiteur) {
		this.numeroCompteDebiteur = numeroCompteDebiteur;
		Compte compteDebiteur = compteService.trouverCompteAvecNumero(numeroCompteDebiteur);
		virement.setCompteDebite(compteDebiteur);
		return "compteCrediteVirement";
	}

	public String choixCompteCredite(String numeroCompteCrediteur) {
		this.numeroCompteCrediteur = numeroCompteCrediteur;
		Compte compteCrediteur = compteService.trouverCompteAvecNumero(numeroCompteCrediteur);
		virement.setCompteCredite(compteCrediteur);
		return "montantVirement";
	}

	public String validationMontant() {
		virement.setMontant(montant);
		return "validationVirement";
	}

	public String getNumeroCompteDebiteur() {
		return numeroCompteDebiteur;
	}

	public void setNumeroCompteDebiteur(String numeroCompteDebiteur) {
		this.numeroCompteDebiteur = numeroCompteDebiteur;
	}

	public String getNumeroCompteCrediteur() {
		return numeroCompteCrediteur;
	}

	public void setNumeroCompteCrediteur(String numeroCompteCrediteur) {
		this.numeroCompteCrediteur = numeroCompteCrediteur;
	}

	public ICompteService getCompteService() {
		return compteService;
	}

	public void setCompteService(ICompteService compteService) {
		this.compteService = compteService;
	}

	public IVirementService getVirementService() {
		return virementService;
	}

	public void setVirementService(IVirementService virementService) {
		this.virementService = virementService;
	}
}
