package com.gtm.proxibanque.domaine;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represente un client <br />
 * Un client possede les attributs suivants : <br />
 * - String nom <br />
 * - String prenom<br />
 * - String email <br />
 * - String adresse <br />
 * - int id : cle primaire utilisee dans la base de donnees (generation automatique) <br />
 * - CompteCourant compteCourant : relation OneToOne <br />
 * - CompteEpargne compteEpargne : relation OneToOne <br />
 * - Conseiller conseiller : relation OneToOne
 */
@Entity
@Table(name = "client")
public class Client {
	
	//PROPRIETES
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idClient;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	@OneToOne(cascade = CascadeType.ALL)
	private CompteCourant compteCourant;
	@OneToOne(cascade = CascadeType.ALL)
	private CompteEpargne compteEpargne;

	//CONSTRUCTEURS
	public Client() {
	}

	public Client(Client client) {
		this.setNom(client.getNom());
		this.setPrenom(client.getPrenom());
		this.setAdresse(client.getAdresse());
		this.setEmail(client.getEmail());

	}
	
	public Client(String nom, String prenom, String email, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
	}
	
	public Client(String nom, String prenom, String email, String adresse, CompteCourant compteCourant,
			CompteEpargne compteEpargne) {
		this(nom, prenom, email, adresse);
		this.compteCourant = compteCourant;
		this.compteEpargne = compteEpargne;
	}

	//ACCESSEURS ET MUTATEURS
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	@Override
	public String toString() {
		return "Client{" + "id=" + idClient + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + ", email='" + email
				+ '\'' + ", adresse='" + adresse + '\'' +
				// ", compteCourant=" + compteCourant +
				// ", compteEpargne=" + compteEpargne +
				// ", conseiller=" + conseiller +
				'}';
	}
}
