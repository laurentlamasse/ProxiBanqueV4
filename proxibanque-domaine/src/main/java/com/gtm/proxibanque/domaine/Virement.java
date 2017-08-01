package com.gtm.proxibanque.domaine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.gtm.proxibanque.domaine.Compte;

/**
 * Represente un virement entre deux comptes bancaires <br />
 * Un virement possede les attributs suivants : <br />
 * - int id : cle primaire utilise dans la base de donnees (generation
 * automatique)<br />
 * - Compte compteDebiteur : relation OneToOne<br />
 * - Compte compteCrediteur : relation OneToOne<br />
 * - Date date <br />
 * - String commentaire<br />
 * - double montant<br />
 */
@Entity
@Table(name = "virement")
public class Virement {

	// PROPRIETES
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idVirement;
	@OneToOne
	private Compte compteDebite;
	@OneToOne
	private Compte compteCredite;
	private Date date;
	private String message;
	private double montant;

	// CONSTRUCTEURS
	public Virement() {
	}

	public Virement(Virement virement) {
		this.compteCredite = virement.getCompteCredite();
		this.compteDebite = virement.getCompteDebite();
		this.montant = virement.getMontant();
		this.date = new Date();
		this.message = virement.getMessage();
	}

	public Virement(Compte compteDebite, Compte compteCredite, double montant, String message) {
		this.setCompteCredite(compteCredite);
		this.setCompteDebite(compteDebite);
		this.setMontant(montant);
		this.setMessage(message);
	}

	// ACCESSEURS ET MUTATEURS
	public int getIdVirement() {
		return idVirement;
	}

	public void setIdVirement(int idVirement) {
		this.idVirement = idVirement;
	}

	public Compte getCompteDebite() {
		return compteDebite;
	}

	public void setCompteDebite(Compte compteDebite) {
		this.compteDebite = compteDebite;
	}

	public Compte getCompteCredite() {
		return compteCredite;
	}

	public void setCompteCredite(Compte compteCredite) {
		this.compteCredite = compteCredite;
	}

	public Date getDate() {
		return date;
	}
	
	public String getDateReduite() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "Virement{" + "it=" + idVirement + ", compteDebiteur=" + compteDebite + ", compteCrediteur=" + compteCredite
				+ ", date=" + date + ", commentaire='" + message + '\'' + ", montant=" + montant + '}';
	}
}