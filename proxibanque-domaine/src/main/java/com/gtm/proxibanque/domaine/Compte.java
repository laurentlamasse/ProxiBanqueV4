package com.gtm.proxibanque.domaine;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

/**
 * Represente un Compte, cette classe est abstraite. Les classes CompteCourant
 * et CompteEpargne en heritent.<br />
 * Un compte possede les attributs suivants : <br />
 * - int id : cle primaire utilisee dans la base de donnees (generation automatique)<br />
 * - String numero<br />
 * - double solde<br />
 * - Client proprietaire : relation OneToOne<br />
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COMPTE")
@DiscriminatorValue("compte")
@Table(name = "compte")
public abstract class Compte {
	//PROPRIETES
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCompte;
	private String numeroCompte;
	private double solde;
	@OneToOne
	private Client proprietaire;
	
	//CONSTRUCTEURS
	public Compte() {
	}

	public Compte(Compte compte) {
		this.setNumeroCompte(compte.getNumeroCompte());
		this.setSolde(compte.getSolde());
	}
	
	public Compte(String numeroCompte, double solde) {
		super();
		this.numeroCompte = numeroCompte;
		this.solde = solde;
	}

	//ACCESSEURS ET MUTATEURS
	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}

	@Override
	public String toString() {
		return "id=" + idCompte + ", numero=" + numeroCompte + ", solde=" + solde + ", proprietaire="/* + proprietaire */;
	}
}
