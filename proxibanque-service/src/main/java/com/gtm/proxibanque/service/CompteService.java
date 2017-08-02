package com.gtm.proxibanque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtm.proxibanque.dao.IClientDao;
import com.gtm.proxibanque.dao.ICompteDao;
import com.gtm.proxibanque.dao.IGenericDao;
import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.Compte;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;
import com.gtm.proxibanque.service.interfaces.ICompteService;

/**
 * Classe implementant l'interface ICompteService et heritant de
 * GenericService<Compte> Cette classe fournit les methodes du service lie a la
 * gestion des comptes.
 *
 */
@Service("compteService")
public class CompteService extends GenericService<Compte> implements ICompteService {

	@Autowired
	@Qualifier("compteDao")
	private ICompteDao compteDao;

	@Autowired
	@Qualifier("clientDao")
	private IClientDao clientDao;

	@Override
	public IGenericDao<Compte> getDao() {
		return compteDao;
	}
	
	public CompteService() {
		
	}
	
	public CompteService(ICompteDao compteDao) {
		this.compteDao = compteDao;
	}

	/**
	 * Recupere via la DAO une List contenant tous les Comptes enregistres en base.
	 *
	 * @return List<Compte> - List contenant tous les Comptes
	 */
	public List<Compte> listerComptes() {
		return compteDao.findAll();
	}

	/**
	 * Insere via la DAO un Compte Courant en base.
	 *
	 * @param compte
	 *            - objet de type CompteCourant a inserer en base
	 * @param client
	 *            - client a qui on assigne le compte
	 * 
	 */
	public void creerCompte(CompteCourant compte, Client client) {
		// TODO : modifier la ligne suivante, c'est un test
		// Client client = clientDao.findOne(1);
		compte.setProprietaire(client);
		compte.setNumeroCompte(genererNumeroCompte());
		client.setCompteCourant(compte);
		clientDao.save(client);
	}

	/**
	 * Insere via la DAO un Compte Epargne en base.
	 *
	 * @param compte
	 *            - objet de type CompteEpargne a inserer en base
	 * 
	 * @param client
	 *            - client a qui on assigne le compte
	 */
	public void creerCompte(CompteEpargne compte, Client client) {
		// TODO : modifier la ligne suivante, c'est un test
		// Client client = clientDao.findOne(1);
		compte.setProprietaire(client);
		compte.setNumeroCompte(genererNumeroCompte());
		client.setCompteEpargne(compte);
		clientDao.save(client);
	}

	/**
	 * Methode qui genere automatiquement un numero de compte de format [3000 ....]
	 */
	public String genererNumeroCompte() {

		// variable en retour
		String nSuivantString = "numerParDefaut";

		List<Compte> listeCompte = compteDao.findAll();

		// si aucun compte en base, creation du numero 3000 0000
		if (listeCompte.isEmpty()) {
			nSuivantString = "3000 0000";
		}
		// si au moins 1 compte existe
		else {

			// tri des string numeros par ordre croissant
			ArrayList<String> listeOrdonnee = (ArrayList<String>) listeCompte.stream().map(c -> c.getNumeroCompte())
					.sorted().collect(Collectors.toList());

			// recuperation de l'index du compte avec ke plus grand numero
			int lastIndex = listeOrdonnee.size() - 1;
			// troncature sur les 4 derniers caracteres et conversion
			int nMax = Integer.parseInt(listeOrdonnee.get(lastIndex).substring(5, 9));
			// increment
			nMax++;

			// conversion en string
			String nMaxString = String.valueOf(nMax);
			String troisMille = "3000";

			// concatenation en fonction de la taille de nMaxString
			switch (nMaxString.length()) {
			case 1:
				nSuivantString = troisMille.concat(" 000" + nMaxString);
				break;
			case 2:
				nSuivantString = troisMille.concat(" 00" + nMaxString);
				break;
			case 3:
				nSuivantString = troisMille.concat(" 0" + nMaxString);
				break;
			case 4:
				nSuivantString = troisMille.concat(" " + nMaxString);
				break;

			}
		}
		return nSuivantString;
	}

	/**
	 * Recupere un compte de la base de donnees a partir de son numero
	 */
	public Compte trouverCompteAvecNumero(String numero) {
		return compteDao.findByNumeroCompte(numero);
	}
}
