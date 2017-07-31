package com.gtm.proxibanque.domaine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Represente un Compte Epargne, cette classe herite de la classe Compte.<br />
 * Un Compte Epargne possede les attributs suivants :<br />
 * - String className<br />
 * - double tauxRemuneration<br />
 */
@Entity
@DiscriminatorValue("epargne")
public class CompteEpargne extends Compte {
	// PROPRIETES
	private double tauxRemuneration = 3.0;

	// CONSTRUCTEURS
	public CompteEpargne() {
		super();
	}

	public CompteEpargne(CompteEpargne compteEpargne) {
		this.setNumeroCompte(compteEpargne.getNumeroCompte());
		this.setSolde(compteEpargne.getSolde());
		this.setTauxRemuneration(compteEpargne.getTauxRemuneration());
		this.setProprietaire(compteEpargne.getProprietaire());
	}
	
	public CompteEpargne(String numeroCompte, Double solde)
	{
		super(numeroCompte, solde);
	}

	// ACCESSEURS ET MUTATEURS
	public double getTauxRemuneration() {
		return tauxRemuneration;
	}

	public void setTauxRemuneration(double tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	public String getTypeDeCompte() {
		return "épargne";
	}

	@Override
	public String toString() {
		return "CompteEpargne{" + super.toString() + ", tauxRemuneration=" + tauxRemuneration + '}';
	}
}
