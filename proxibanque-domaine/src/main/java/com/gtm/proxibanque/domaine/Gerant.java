package com.gtm.proxibanque.domaine;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Represente un Gerant, cette classe herite de la classe Employe.<br />
 * Un Gerant possede les attributs suivants :<br />
 * - List<Gerant> listeGerants : relation OneToMany<br />
 */
@Entity
@DiscriminatorValue("gerant")
public class Gerant extends Employe {

	// PROPRIETES
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Conseiller> listeConseillers = new HashSet<Conseiller>();

	//CONSTRUCTEURS
	public Gerant() {
	}

	public Gerant(Gerant gerant) {
		this.setNom(gerant.getNom());
		this.setPrenom(gerant.getPrenom());
		this.setPassword(gerant.getPassword());
		this.setLogin(gerant.getLogin());

	}
	
	public Gerant(String nom, String prenom, String login, String motDePasse) {
		super(nom, prenom, login, motDePasse);
	}

	// ACCESSEURS ET MUTATEURS
	public Collection<Conseiller> getListeConseillers() {
		return listeConseillers;
	}

	public void setListeConseillers(Collection<Conseiller> listeConseillers) {
		this.listeConseillers = listeConseillers;
	}

}
