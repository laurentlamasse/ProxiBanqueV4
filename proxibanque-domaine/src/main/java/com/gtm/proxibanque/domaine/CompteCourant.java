package com.gtm.proxibanque.domaine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represente un Compte Courant, cette classe herite de la classe Compte.<br />
 * Un Compte Courant possede les attributs suivants :<br />
 * - String className<br />
 * - double decouvertAutorise<br />
 */
@Entity
@DiscriminatorValue("courant")
public class CompteCourant extends Compte {
	//PROPRIETES
	private double plafondDecouvert = 1000.0;

	//CONSTRUCTEURS
	public CompteCourant() {
	}

	public CompteCourant(CompteCourant compteCourant) {
		this.setNumeroCompte(compteCourant.getNumeroCompte());
		this.setSolde(compteCourant.getSolde());
		this.setDecouvertAutorise(compteCourant.getDecouvertAutorise());
		this.setProprietaire(compteCourant.getProprietaire());
	}
	
	public CompteCourant(String numeroCompte, double solde)
	{
		super(numeroCompte, solde);
	}
	
	public CompteCourant(String numeroCompte, double solde, double plafondDecouvert)
	{
		super(numeroCompte, solde);
		this.plafondDecouvert = plafondDecouvert;
	}

	// ACCESSEURS ET MUTATEURS
	public double getDecouvertAutorise() {
		return plafondDecouvert;
	}

	public void setDecouvertAutorise(double plafondDecouvert) {
		this.plafondDecouvert = plafondDecouvert;
	}

	public String getTypeDeCompte() {
		return "courant";
	}

	@Override
	public String toString() {
		return "CompteCourant{" + super.toString() + ", decouvertAutorise=" + plafondDecouvert + '}';
	}
}
