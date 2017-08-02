package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;
import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.service.ClientService;
import com.gtm.proxibanque.service.interfaces.IClientService;
import com.gtm.proxibanque.service.interfaces.ICompteService;
import com.gtm.proxibanque.service.interfaces.IConseillerService;

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
	private IClientService clientService;
	@Autowired
	private ICompteService compteService;
	@Autowired
	private IConseillerService conseillerService;

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
	
	public ArrayList<Client> getListeClientConseiller() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
		String login = request.getRemoteUser();
		Conseiller conseiller = conseillerService.trouverConseillerParLogin(login);
		ArrayList<Client> listeTemp = new ArrayList<Client>(conseiller.getListeClients());
		return listeTemp;		
	}

	public ArrayList<Client> getListeClient() {
		return (ArrayList<Client>) clientService.listerClients();
	}

	public void setListeClient(ArrayList<Client> listeClient) {
		this.listeClient = listeClient;
	}

	public String ajouterClient() {
		Client clientCopie = new Client(client);
		clientCopie = clientService.creerClient(clientCopie);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
		String login = request.getRemoteUser();
		int id = conseillerService.trouverConseillerParLogin(login).getIdEmploye();
		Conseiller conseiller = conseillerService.trouverConseiller(id);
		conseiller.getListeClients().add(clientCopie);
		conseillerService.modifierConseiller(conseiller);

		if (selectCC) {
			CompteCourant compte = new CompteCourant();
			compte.setSolde(soldeCC);
			compte.setDecouvertAutorise(decouvertCC);
			compteService.creerCompte(compte, clientCopie);
		}
		clientCopie = clientService.findOne(clientCopie.getIdClient());
		if (selectCE) {
			CompteEpargne compte = new CompteEpargne();
			compte.setSolde(soldeCE);
			compte.setTauxRemuneration(tauxCE);
			compteService.creerCompte(compte, clientCopie);
		}
		return "listeClients";
	}
	

	public String modifierClient() {
		//client = clientService.findOne(idClient); 
		client = clientService.creerClient(client);

		if (selectCC) {
			CompteCourant compte = new CompteCourant();
			compte.setSolde(soldeCC);
			compte.setDecouvertAutorise(decouvertCC);
			compteService.creerCompte(compte, client);
		}
		client = clientService.findOne(client.getIdClient());
		if (selectCE) {
			CompteEpargne compte = new CompteEpargne();
			compte.setSolde(soldeCE);
			compte.setTauxRemuneration(tauxCE);
			compteService.creerCompte(compte, client);
		}
		return "listeClients";
		}
		
	public String supprimerClient(int idClient) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
		String login = request.getRemoteUser();
		int idConseiller = conseillerService.trouverConseillerParLogin(login).getIdEmploye();
		Conseiller conseiller = conseillerService.trouverConseiller(idConseiller);
		client = clientService.findOne(idClient);
		//TODO remove ne fonctionne pas
		//conseiller.getListeClients().remove(client);
		conseillerService.updateConseiller(conseiller);
		clientService.delete(idClient);
		
		return "listeClients";
	}
	
	public String affichageClient(int idClient) {
		client = clientService.findOne(idClient);
		return "detailsClient";
	}
	
	public String modificationClient(int idClient) {
		client = clientService.findOne(idClient);
		return "modificationClient";
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
