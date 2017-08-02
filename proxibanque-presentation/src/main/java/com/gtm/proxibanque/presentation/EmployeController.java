package com.gtm.proxibanque.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.domaine.Gerant;
import com.gtm.proxibanque.service.interfaces.IConseillerService;
import com.gtm.proxibanque.service.interfaces.IGerantService;

/**
 * Classe Bean qui sera instancie par JSF et sera initialise a partir des
 * informations founies par la page JSF (exemple : les valeurs des champs d'un
 * formulaire). Cette classe permet la gestion du binding c'est a dire le
 * branchement entre l'univers web et l'univers java.
 * 
 * EmployeController injecte 2 services utilises pour la gestion des employes :
 * - IConseillerService conseillerService - IGerantService gerantService
 */
@Controller
@Scope("session")
public class EmployeController {

	// Propriétés
	private Gerant gerant;
	private Conseiller conseiller;
	private ArrayList<Conseiller> listeConseiller;

	@Autowired
	private IConseillerService conseillerService;

	@Autowired
	private IGerantService gerantService;

	public EmployeController() {

	}

	/**
	 * Methode d'initialisation appelee automatiquement apres l'instanciation de la
	 * classe EmployeController.
	 */
	@PostConstruct
	public void init() {
		gerant = new Gerant();
		conseiller = new Conseiller();
		listeConseiller = new ArrayList<Conseiller>();
		listeConseiller = (ArrayList<Conseiller>) conseillerService.listerConseillers();
	}

	/**
	 * Enregistre un conseiller dans la base de donnees
	 * 
	 * @return
	 */
	public String ajouterConseiller() {
		Conseiller conseillerCopie = new Conseiller(conseiller);
		conseillerService.initialiserListe(conseillerCopie);
		conseillerService.creerConseiller(conseillerCopie);
		return "listeConseiller";
	}

	/**
	 * Supprime un conseiller de la base de donnees
	 * 
	 * @param id
	 *            Identifiant (Cle primaire) du conseiller a supprimer
	 * @return
	 */
	public String supprimerConseiller(int id) {
		conseiller = conseillerService.findOne(id);
		if (conseiller.getListeClients().isEmpty()) {
			conseillerService.delete(id);
		}
		return "listeConseiller";
	}

	/**
	 * Enregistre un gerant dans la base de donnee
	 * 
	 * @return
	 */
	public String ajouterGerant() {
		gerantService.initialiserListe(gerant);
		gerantService.creerGerant(gerant);
		return "gestion";
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	/**
	 * Recupere la liste des conseillers enregistres dans la base de donnees
	 * 
	 * @return
	 */
	public ArrayList<Conseiller> getListeConseiller() {
		listeConseiller = (ArrayList<Conseiller>) conseillerService.listerConseillers();
		return listeConseiller;
	}

	public void setListeConseiller(ArrayList<Conseiller> listeConseiller) {
		this.listeConseiller = listeConseiller;
	}

	public IConseillerService getConseillerService() {
		return conseillerService;
	}

	public void setConseillerService(IConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

	public IGerantService getGerantService() {
		return gerantService;
	}

	public void setGerantService(IGerantService gerantService) {
		this.gerantService = gerantService;
	}
}
