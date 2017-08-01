package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.service.ClientService;

@Controller
@Scope("session")
public class ClientController {

	// Propriétés
	private Client client;
	private ArrayList<Client> listeClient;
	private boolean selectCC;
	private boolean selectCE;
	private double soldeCC;
	private double soldeCE;
	private double decouvertCC;
	private double tauxCE;

	@Autowired
	private ClientService clientService;
	@Autowired
	private CompteService compteService;
//	@Autowired
//	private ConseillerService conseillerService;

	// Constructeur
	public ClientController() {
		}

	@PostConstruct
	public void init() {
		client = new Client();
		listeClient = new ArrayList<Client>();
		listeClient = (ArrayList<Client>) clientService.listerClients();
	}

	// Getters & Setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client Client) {
		this.client = Client;
	}

	public ArrayList<Client> getListeClient() {
		return (ArrayList<Client>) clientService.listerClients();
	}

	public void setListeClient(ArrayList<Client> listeClient) {
		this.listeClient = listeClient;
	}

	public String ajouterClient() {
		Client nouveauClient = clientService.creerClient(client);
		// TODO remplacer l'id en dur du conseiller
		Conseiller conseiller = conseillerService.trouverConseiller(2);
		conseiller.getListeClients().add(nouveauClient);
		conseillerService.modifierConseiller(conseiller);

		if (selectCC) {
			CompteCourant compte = new CompteCourant();
			compte.setSolde(soldeCC);
			compte.setDecouvertAutorise(decouvertCC);
			compteService.creerCompte(compte);
		}
		if (selectCE) {
			CompteEpargne compte = new CompteEpargne();
			compte.setSolde(soldeCE);
			compte.setTauxRemuneration(tauxCE);
			compteService.creerCompte(compte);
		}
		return "gestionClientBS";
	}

	public boolean isSelectCC() {
		return selectCC;
	}

	public void setSelectCC(boolean selectCC) {
		this.selectCC = selectCC;
	}

	public boolean isSelectCE() {
		return selectCE;
	}

	public void setSelectCE(boolean selectCE) {
		this.selectCE = selectCE;
	}

	public double getSoldeCC() {
		return soldeCC;
	}

	public void setSoldeCC(double soldeCC) {
		this.soldeCC = soldeCC;
	}

	public double getSoldeCE() {
		return soldeCE;
	}

	public void setSoldeCE(double soldeCE) {
		this.soldeCE = soldeCE;
	}

	public double getDecouvertCC() {
		return decouvertCC;
	}

	public void setDecouvertCC(double decouvertCC) {
		this.decouvertCC = decouvertCC;
	}

	public double getTauxCE() {
		return tauxCE;
	}

	public void setTauxCE(double tauxCE) {
		this.tauxCE = tauxCE;
	}
}
