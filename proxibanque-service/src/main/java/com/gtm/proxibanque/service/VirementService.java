package com.gtm.proxibanque.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	/**
	 * Recupere via la DAO une List contenant tous les Comptes enregistres en base.
	 *
	 * @return List<Compte> - List contenant tous les Comptes
	 */
	public List<Virement> listerVirements() {
		return dao.findAll();
	}

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
				dao.save(virement);
				return true;
			}
		}
		// Si le compte débiteur est un compte épargne : tester le solde
		if (virement.getCompteDebite().getClass().isAssignableFrom(CompteEpargne.class)) {
			compteEpargne = (CompteEpargne) virement.getCompteDebite();
			soldeCompte = compteEpargne.getSolde();
			if (virement.getMontant() < soldeCompte) {
				effectuerVirement(virement);
				dao.save(virement);
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
	
	
	public ArrayList<Long> getSectionPourCamembert(Date date1, Date date2) {
		
		
		ArrayList<Long> listSection = new ArrayList<Long>();
		ArrayList<Virement> malist = (ArrayList<Virement>) dao.findAll().stream()
				.filter(c -> c.getDate().before(date2)&&c.getDate().after(date1))
				.collect(Collectors.toList());
		
		
		long section1 = malist.stream()
				.filter(c -> c.getMontant()>0 && c.getMontant()<=200)
				.count();
		listSection.add(section1);
		
		long section2 = malist.stream()
				.filter(c -> c.getMontant()>200 && c.getMontant()<=500)
				.count();
		listSection.add(section2);
		
		long section3 = malist.stream()
				.filter(c -> c.getMontant()>500 && c.getMontant()<=1000)
				.count();
		listSection.add(section3);
		
		long section4 = malist.stream()
				.filter(c -> c.getMontant()>1000 && c.getMontant()<=5000)
				.count();
		listSection.add(section4);
		
		long section5 = malist.stream()
				.filter(c -> c.getMontant()>5000)
				.count();
		listSection.add(section5);
		
		return listSection;	
		
	}

}
