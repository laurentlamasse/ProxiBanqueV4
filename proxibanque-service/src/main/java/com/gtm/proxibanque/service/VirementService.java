package com.gtm.proxibanque.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.ICompteDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.dao.IVirementDao;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;
import com.gtm.proxibanque.domaine.Virement;
import com.gtm.proxibanque.service.interfaces.IVirementService;

/**
 * Classe implementant l'interface IVirementService et heritant de
 * GenericService<Virement> Cette classe fournit les methodes du service lie a
 * la gestion des virements.
 *
 */
@Service("virementService")
public class VirementService extends GenericService<Virement> implements IVirementService {

	@Autowired
	@Qualifier("virementDao")
	private IVirementDao dao;

	@Autowired
	@Qualifier("compteDao")
	private ICompteDao compteDao;

	@Override
	public IGenericDao<Virement> getDao() {
		return dao;
	}

	public VirementService() {
		
	}
	
	public VirementService(IVirementDao daoVirement, ICompteDao daoCompte) {
		this.dao = daoVirement;
		this.compteDao = daoCompte;
	}
	
	/**
	 * Recupere via la DAO une List contenant tous les Comptes enregistres en base.
	 *
	 * @return List<Compte> - List contenant tous les Comptes
	 */
	public List<Virement> listerVirements() {
		return dao.findAll();
	}

	/**
	 * Cree un virement entre 2 comptes
	 */
	public boolean createVirement(Virement virement) {
		// TODO Si la DAO plante, annuler le virement

		// Vérif virement faisable (solde ok)
		// Extraction des données du virement
		CompteCourant compteCourant = null;
		CompteEpargne compteEpargne = null;
		double soldeCompte = 0;
		double decouvert = 0;
		// Si le compte débiteur est un compte courant : tester le solde +
		// découvert
		if (virement.getCompteDebite().getClass().isAssignableFrom(CompteCourant.class)) {
			compteCourant = (CompteCourant) virement.getCompteDebite();
			soldeCompte = compteCourant.getSolde();
			decouvert = compteCourant.getDecouvertAutorise();
			if (virement.getMontant() < (soldeCompte + decouvert)) {
				effectuerVirement(virement);
				Virement virementCopie = new Virement(virement);
				dao.save(virementCopie);
				return true;
			}
		}
		// Si le compte débiteur est un compte épargne : tester le solde
		if (virement.getCompteDebite().getClass().isAssignableFrom(CompteEpargne.class)) {
			compteEpargne = (CompteEpargne) virement.getCompteDebite();
			soldeCompte = compteEpargne.getSolde();
			if (virement.getMontant() < soldeCompte) {
				effectuerVirement(virement);
				Virement virementCopie = new Virement(virement);
				dao.save(virementCopie);
				return true;
			}
		}

		return false;
	}

	/**
	 * Effectue un virement entre deux comptes. Extrait du parametre virement les
	 * comptes debiteur, crediteur et le montant du virement. Met a jour les soldes
	 * des comptes via leurs setters respectifs.
	 *
	 * @param virement
	 *            - objet de type Virement contenant le compte debiteur, le compte
	 *            crediteur, le montant du virement et un message.
	 * @return boolean - true si le virement est effectue, false sinon.
	 */
	public boolean effectuerVirement(Virement virement) {
		double montant = virement.getMontant();
		double soldeCD = virement.getCompteDebite().getSolde();
		double soldeCC = virement.getCompteCredite().getSolde();
		virement.getCompteDebite().setSolde(soldeCD - montant);
		virement.getCompteCredite().setSolde(soldeCC + montant);
		compteDao.save(virement.getCompteDebite());
		compteDao.save(virement.getCompteCredite());

		return true;
	}

	/**
	 * Methode de traitement pour les informations du diagramme circulaire. Les
	 * valeurs sont generees en fonction des virements effectues entre la date1 et
	 * la date2
	 */
	public ArrayList<Long> getSectionPourCamembert(Date date1, Date date2) {
		
		String sDate1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
		String sDate2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);

		ArrayList<Long> listeSection;
		try
		{
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/proxibanquews/rest/json");
			Response response = target.queryParam("date1", sDate1).queryParam("date2", sDate2).request().get();
			listeSection = response.readEntity(new GenericType<ArrayList<Long>>() {});
			response.close();
		}
		catch(Exception e)
		{
			listeSection = new ArrayList();
		}
		
		return listeSection;
	}

}
