package com.gtm.proxibanque.presentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.PieChartModel;
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
	private PieChartModel camembert;
	private Date date1;
	private Date date2;
	private Calendar c1;
	private Calendar c2;
	
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
		c1 = Calendar.getInstance();
		c2 = Calendar.getInstance();
		c1.setTime(new Date());
		date2 = c1.getTime();
		c2.setTime(new Date());
		c2.add(Calendar.DATE, -30);
		date1 = c2.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("dans le controller" + dateFormat.format(date1));
		System.out.println("dans le controller" + dateFormat.format(date2));
		createCamembert();
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
	
	 public PieChartModel getCamembert() {
	        return camembert;
	    }
	 
	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
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
	
	private void createCamembert() {
        camembert = new PieChartModel();
        ArrayList<Long> listeSection = virementService.getSectionPourCamembert(date1, date2);
        camembert.set("entre 0 et 200€", listeSection.get(0));
        camembert.set("entre 200 et 500€", listeSection.get(1));
        camembert.set("entre 500 et 1000€", listeSection.get(2));
        camembert.set("entre 1000 et 5000€", listeSection.get(3));
        camembert.set("plus de 5000€", listeSection.get(4));

        camembert.setTitle("Répartition du montant des virement effectués");
        camembert.setLegendPosition("w");
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
